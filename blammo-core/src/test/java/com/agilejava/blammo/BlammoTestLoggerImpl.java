package com.agilejava.blammo;

/**
 * An implementation of the {@link com.agilejava.blammo.TestLogger} interface,
 * allowing us to get hold of the LoggingKitAdapter, when required.
 * 
 * @author Wilfred Springer
 */
public class BlammoTestLoggerImpl implements BlammoLogger {

	private LoggingKitAdapter adapter;

	public void setLoggingKitAdapter(LoggingKitAdapter adapter) {
		this.adapter = adapter;
	}
	
	public LoggingKitAdapter getLoggingKitAdapter() {
		return adapter;
	}
	
}
