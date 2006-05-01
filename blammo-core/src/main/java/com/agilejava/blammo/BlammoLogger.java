package com.agilejava.blammo;

/**
 * The interface to be implemented by Blammo loggers, allowing us to plug in a
 * certain kind of {@link LoggingKitAdapter} to facilitate a particular way of
 * logging.
 * 
 * @author Wilfred Springer
 * 
 */
public interface BlammoLogger {

	/**
	 * Sets the {@link LoggingKitAdapter} to be used by this logger.
	 * 
	 * @param log
	 */
	void setLoggingKitAdapter(LoggingKitAdapter log);

}
