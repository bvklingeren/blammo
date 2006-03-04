package com.agilejava.blammo;

/**
 * An implementation of the {@link LoggingKitAdapter} interface, logging all messages to
 * standard err.
 * 
 * @author Wilfred Springer
 */
public class BlammoStdErrLog implements LoggingKitAdapter {

	public void log(String level, String message) {
		System.err.println(level + ": " + message);
    }

	public void log(String level, String message, Throwable throwable) {
	    System.err.println(level + ": " + message);
	    throwable.printStackTrace(System.err);
    }

}
