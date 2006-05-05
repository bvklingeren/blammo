package com.agilejava.blammo.mojo;

/* 
 * Copyright (C) 2006, Wilfred Springer
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.apache.commons.io.FileUtils;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;

/**
 * The Mojo turning Blammo annotations into resource files and implementation
 * classes.
 * 
 * @author Wilfred Springer
 * @phase generate-sources
 * @goal generate
 */
public class BlammoGeneratorMojo extends AbstractMojo {

	/**
	 * Include the message identifier.
	 * 
	 * @parameter expression="true"
	 */
	private boolean includeId;

	/**
	 * The message id offset.
	 * 
	 * @parameter expression="1"
	 */
	private int messageIdOffset;

	/**
	 * The message id prefix.
	 * 
	 * @parameter expression="E"
	 */
	private String messageIdPrefix;

	/**
	 * The directory containing your Java sources.
	 * 
	 * @parameter expression="${basedir}/src/main/java"
	 */
	private File javaSourcesDir;

	/**
	 * The directory to which the catalog will be generated.
	 * 
	 * @parameter expression="${basedir}/target/generated-sources"
	 */
	private File targetDirectory;

	/**
	 * @parameter expression="${project}"
	 */
	private MavenProject project;

	public void execute() throws MojoExecutionException, MojoFailureException {
		List loggers = null;
		try {
			loggers = new BlammoParser(messageIdPrefix, messageIdOffset)
					.parse(javaSourcesDir);
		} catch (BlammoParserException bpe) {
			throw new MojoExecutionException(bpe.getMessage() + ": line "
					+ bpe.getLineNumber() + " in "
					+ bpe.getSourceFile().getFile());
		}
		produceImplementations(loggers);
		produceResourceFiles(loggers);
		project.addCompileSourceRoot(targetDirectory.getAbsolutePath());
		Resource resource = new Resource();
		resource.setDirectory(targetDirectory.getAbsolutePath());
		ArrayList patterns = new ArrayList();
		patterns.add("**/*.properties");
		resource.setIncludes(patterns);
		project.getBuild().addResource(resource);
	}

	public void produceImplementations(List loggers)
			throws MojoExecutionException {
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream in = loader.getResourceAsStream("loggers.stg");
		Reader reader = new InputStreamReader(in);
		StringTemplateGroup group = new StringTemplateGroup(reader);
		StringTemplate template = group.getInstanceOf("logger");
		Iterator iterator = loggers.iterator();
		while (iterator.hasNext()) {
			Logger logger = (Logger) iterator.next();
			File out = new File(targetDirectory, logger
					.getImplementationFileName());
			template.setAttribute("logger", logger);
			template.setAttribute("includeId", new Boolean(includeId));
			try {
				out.getParentFile().mkdirs();
				FileUtils.writeStringToFile(out, template.toString(), null);
			} catch (IOException ioe) {
				throw new MojoExecutionException(
						"Failed to write implementation file.", ioe);
			}
			template.reset();
		}
	}

	public void produceResourceFiles(List loggers)
			throws MojoExecutionException {
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream in = loader.getResourceAsStream("resourceBundle.stg");
		Reader reader = new InputStreamReader(in);
		StringTemplateGroup group = new StringTemplateGroup(reader);
		StringTemplate template = group.getInstanceOf("logger");
		Iterator iterator = loggers.iterator();
		while (iterator.hasNext()) {
			Logger logger = (Logger) iterator.next();
			File out = new File(targetDirectory, logger.getResourceFileName());
			template.setAttribute("logger", logger);
			try {
				out.getParentFile().mkdirs();
				FileUtils.writeStringToFile(out, template.toString(), null);
			} catch (IOException ioe) {
				throw new MojoExecutionException(
						"Failed to write implementation file.", ioe);
			}
			template.reset();
		}
	}

}
