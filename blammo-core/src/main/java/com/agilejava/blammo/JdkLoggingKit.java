package com.agilejava.blammo;

import java.util.logging.Logger;

/**
 * An implementation of the {@link LoggingKit} interface, constructing
 * {@link LoggingKitAdapter} instances that wrap JDK loggers.
 * 
 * @author Wilfred Springer
 */
public class JdkLoggingKit implements LoggingKit {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agilejava.blammo.BlammoLogFactory#createBlammoLog(java.lang.Class)
	 */
	public LoggingKitAdapter createLogKitAdapter(Class cl) {
		Logger logger = Logger.getLogger(cl.getName());
		return new JdkLoggingKitAdapter(logger);
	}

}
