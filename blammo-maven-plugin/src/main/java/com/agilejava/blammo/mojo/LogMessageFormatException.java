package com.agilejava.blammo.mojo;

public class LogMessageFormatException extends Exception {

    /**
     * The serial version UID used to identify this version.
     */
    private static final long serialVersionUID = -7126838803184065596L;

    private String source;

    private int position;

    private String expectation;

    public LogMessageFormatException(String source, int position) {
        this.source = source;
        this.position = position;
    }

    public LogMessageFormatException(String source, int position,
            String expectation) {
        this(source, position);
        this.expectation = expectation;
    }

    public String getMessage() {
        return "Failed to parse message at position " + position
                + (expectation != null ? ": " + expectation : ".");
    }

}
