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
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.qdox.model.JavaClass;

/**
 * The class representing a logger.
 * 
 * @author Wilfred Springer
 */
public class Logger {

    /**
     * The interface used for representing the logger.
     */
    private JavaClass javaClass;

    /**
     * The events that we're able to generate using this logger.
     */
    private List events;

    /**
     * Constructs a new instance.
     */
    public Logger() {
        this.setEvents(new ArrayList());
    }

    /**
     * Sets a reference to the {@link JavaClass} providing metadata about the
     * interface that represents this logger.
     * 
     * @param javaClass
     *            The <code>JavaClass</code> providing metadata about the
     *            interface that represents this logger.
     */
    public void setJavaClass(JavaClass javaClass) {
        this.javaClass = javaClass;
    }

    /**
     * Returns the <code>JavaClass</code> providing metadata about the
     * interface representing this logger.
     * 
     * @return Returns the javaClass providing metadata about the interface
     *         representing this logger.
     */
    public JavaClass getJavaClass() {
        return javaClass;
    }

    /**
     * Adds a {@link LogEvent} to the Logger.
     * 
     * @param event
     *            The {@link LogEvent} to add to the list of events.
     */
    public void addLogEvent(LogEvent event) {
        events.add(event);
    }

    /**
     * Sets the <code>List</code> of <code>LogEvent</code>s.
     * 
     * @param events
     *            The events to set.
     */
    public void setEvents(List events) {
        this.events = events;
    }

    /**
     * Returns the <code>List</code> of <code>LogEvent</code>s.
     * 
     * @return Returns the events.
     */
    public List getEvents() {
        return events;
    }

    public String getLoggerPackage() {
        return javaClass.getPackage();
    }

    /**
     * Returns the directory in which the logger artifacts should live.
     * 
     * @return The directory in which the logger artifacts should live.
     */
    public String getLoggerDir() {
        return javaClass.getPackage().replace('.', File.separatorChar);
    }

    public String getBaseName() {
        String fqn = javaClass.getFullyQualifiedName().replace('$', '_');
        String base = fqn.substring(fqn.lastIndexOf('.') + 1);
        return "Blammo" + base;
    }

    public String getImplementationName() {
        return getBaseName() + "Impl";
    }

    public String getImplementationFileName() {
        return getLoggerDir() + File.separatorChar + getImplementationName()
                + ".java";
    }

    public String getResourceFileBase() {
        return getLoggerDir() + File.separatorChar + getBaseName();
    }

    public String getResourceFileName() {
        return getResourceFileBase() + ".properties";
    }

    public String getImplementingClass() {
        return javaClass.getFullyQualifiedName().replace('$', '.');
    }

}
