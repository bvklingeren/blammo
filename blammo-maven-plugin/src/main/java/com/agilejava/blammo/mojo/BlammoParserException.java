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
import java.net.URL;

/**
 * The exception thrown by the {@link BlammoParser} if it fails to reconstruct
 * logger information from the sources, because of coding failures.
 * 
 * @author Wilfred Springer
 */
public class BlammoParserException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -6337945271763899735L;
    
    /**
     * The line number on which the error occurs.
     */
    private int lineNumber;
    
    /**
     * The source file in which the error occurs.
     */
    private URL sourceFile;
    
    /**
     * 
     * 
     */
    public BlammoParserException(String error, int lineNumber, URL sourceFile) {
        super(error);
        this.lineNumber = lineNumber;
        this.sourceFile = sourceFile;
    }

    /**
     * @param lineNumber The lineNumber to set.
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * @return Returns the lineNumber.
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * @param sourceFile The sourceFile to set.
     */
    public void setSourceFile(URL sourceFile) {
        this.sourceFile = sourceFile;
    }

    /**
     * @return Returns the sourceFile.
     */
    public URL getSourceFile() {
        return sourceFile;
    }

}
