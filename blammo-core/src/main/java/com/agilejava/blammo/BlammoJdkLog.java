package com.agilejava.blammo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A <code>BlammoLog</code> implementation
 * 
 * @author Wilfred Springer
 */
public class BlammoJdkLog implements LoggingKitAdapter {

	private Logger logger;

	public BlammoJdkLog(Logger logger) {
		this.logger = logger;
	}

	private Level getLevel(String level) {
		if (LoggingKitAdapter.LEVEL_DEBUG.equals(level)) {
			return Level.FINE;
		} else if (LoggingKitAdapter.LEVEL_ERROR.equals(level)) {
			return Level.SEVERE;
		} else if (LoggingKitAdapter.LEVEL_INFO.equals(level)) {
			return Level.INFO;
		} else if (LoggingKitAdapter.LEVEL_WARN.equals(level)) {
			return Level.WARNING;
		} else {
			return Level.FINEST;
		}
	}

	public void log(String level, MessageProducer producer) {
		Level logLevel = getLevel(level);
		if (logger.isLoggable(logLevel)) {
			logger.log(logLevel, producer.getMessage());
		}
	}

	public void log(String level, MessageProducer producer, Throwable throwable) {
		Level logLevel = getLevel(level);
		if (logger.isLoggable(logLevel)) {
			logger.log(logLevel, producer.getMessage(), throwable);
		}
	}

}
