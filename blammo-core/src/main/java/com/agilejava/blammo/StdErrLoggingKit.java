package com.agilejava.blammo;

/**
 * An implementation of the {@link LoggingKit} interface, constructing
 * {@link LoggingKitAdapter} instances that print their output to standard err.
 * 
 * @author Wilfred Springer
 */
public class StdErrLoggingKit implements LoggingKit {

	public LoggingKitAdapter createLogKitAdapter(Class cl) {
		return new StdErrLoggingKitAdapter();
	}

}
