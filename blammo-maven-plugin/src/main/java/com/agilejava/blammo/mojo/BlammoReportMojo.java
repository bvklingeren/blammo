package com.agilejava.blammo.mojo;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.maven.project.MavenProject;
import org.apache.maven.reporting.AbstractMavenReport;
import org.apache.maven.reporting.MavenReportException;
import org.codehaus.doxia.sink.Sink;
import org.codehaus.doxia.site.renderer.SiteRenderer;

import com.agilejava.blammo.BlammoLogger;

/**
 * Generates a report including all messages, and additional comments.
 * 
 * @goal report
 * @author Wilfred Springer
 */
public class BlammoReportMojo extends AbstractMavenReport {

    /**
     * @parameter expression="true"
     */
    private boolean includeComments;

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

    /**
     * Doxia site renderer.
     * 
     * @parameter expression="${component.org.codehaus.doxia.site.renderer.SiteRenderer}"
     * @required
     * @readonly
     */
    private SiteRenderer siteRenderer;

    /**
     * Location where generated html will be created.
     * 
     * @parameter expression="${project.build.directory}/site "
     */
    private String outputDirectory;

    protected SiteRenderer getSiteRenderer() {
        return siteRenderer;
    }

    protected String getOutputDirectory() {
        return outputDirectory;
    }

    protected MavenProject getProject() {
        return project;
    }

    protected void executeReport(Locale arg0) throws MavenReportException {
        List loggers = null;
        try {
            loggers = new BlammoParser(messageIdPrefix, messageIdOffset)
                    .parse(javaSourcesDir);
        } catch (BlammoParserException bpe) {
            throw new MavenReportException(bpe.getMessage() + ": line "
                    + bpe.getLineNumber() + " in "
                    + bpe.getSourceFile().getFile());
        }
        generate(loggers, getSink());
    }

    private void generate(List loggers, Sink sink) {
        sink.head();
        sink.text("Message Catalog");
        sink.head_();
        sink.body();
        sink.sectionTitle1();
        sink.text("Message Catalog");
        sink.sectionTitle1_();
        sink.table();
        sink.tableRow();
        sink.tableHeaderCell();
        sink.text("Type");
        sink.tableHeaderCell_();
        sink.tableHeaderCell();
        sink.text("Identifier");
        sink.tableHeaderCell_();
        sink.tableHeaderCell();
        sink.text("Message");
        sink.tableHeaderCell_();
        sink.tableRow_();
        Iterator iterator = loggers.iterator();
        while (iterator.hasNext()) {
            generate((Logger) iterator.next(), sink);
        }
        sink.table_();
        sink.lineBreak();
        sink.body_();
        sink.flush();
    }

    private void generate(Logger logger, Sink sink) {
        Iterator iterator = logger.getEvents().iterator();
        while (iterator.hasNext()) {
            generate((LogEvent) iterator.next(), sink);
        }
    }

    private void generate(LogEvent event, Sink sink) {
        sink.tableRow();
        sink.tableCell();
        sink.figure();
        if ("warn".equals(event.getLevel())) {
            sink.figureGraphics("images/icon_warning_sml.gif");
        } else if ("error".equals(event.getLevel())) {
            sink.figureGraphics("images/icon_error_sml.gif");
        } else if ("info".equals(event.getLevel())) {
            sink.figureGraphics("images/icon_success_sml.gif");
        }
        sink.figure_();
        sink.tableCell_();
        sink.tableCell();
        sink.text(event.getIdentifier());
        sink.tableCell_();
        sink.tableCell();
        if (includeComments && event.getComments() != null
                && event.getComments().trim().length() > 0) {
            sink.text(event.getComments());
            sink.lineBreak();
            sink.lineBreak();
        }
        sink.italic();
        sink.text(event.getMessage());
        sink.italic_();
        sink.lineBreak();
        sink.lineBreak();
        sink.tableCell_();
        sink.tableRow_();
    }

    public String getOutputName() {
        return "blammo-report";
    }

    public String getName(Locale locale) {
        return getBundle(locale).getString("report.blammo.name");
    }

    public String getDescription(Locale locale) {
        return getBundle(locale).getString("report.blammo.description");
    }

    private ResourceBundle getBundle(Locale locale) {
        return ResourceBundle.getBundle("blammo-report", locale, this
                .getClass().getClassLoader());
    }

}
