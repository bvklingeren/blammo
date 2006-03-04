package com.agilejava.blammo.mojo;

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
