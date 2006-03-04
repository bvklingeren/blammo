package com.agilejava.blammo;

/**
 * The interface to be implemented by all logging toolkit adapters. (Think of it
 * as a rudimentary interface similar to Commons Logging, without the
 * bootstrapping mechanism.)
 * 
 * @author Wilfred Springer
 */
public interface LoggingKitAdapter {
	
	/**
	 * The INFO log level.
	 */
	final String LEVEL_INFO = "info";

	/**
	 * The DEBUG log level.
	 */
	final String LEVEL_DEBUG = "debug";

	/**
	 * The ERROR log level.
	 */
	final String LEVEL_ERROR = "error";

	/**
	 * The WARN log level.
	 */
	final String LEVEL_WARN = "warn";

	/**
	 * Logs a message, passing in the log level and the message.
	 * 
	 * @param level
	 *            The log level. (Either {@link #LEVEL_DEBUG},
	 *            {@link #LEVEL_ERROR}, {@link #LEVEL_INFO} or
	 *            {@link #LEVEL_WARN}).
	 * @param message
	 *            The message to be logged.
	 */
	void log(String level, String message);

	/**
	 * Logs the message, passing in the log level, the message and the
	 * <code>Throwable</code>.
	 * 
	 * @param level
	 *            The log level. (Either {@link #LEVEL_DEBUG},
	 *            {@link #LEVEL_ERROR}, {@link #LEVEL_INFO} or
	 *            {@link #LEVEL_WARN}).
	 * @param message
	 *            The message to be logged.
	 * @param throwable
	 *            The associated exception.
	 */
	void log(String level, String message, Throwable throwable);

}
