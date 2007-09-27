package com.agilejava.adapter.log4j;

import org.apache.log4j.Logger;

import com.agilejava.blammo.LoggingKitAdapter;
import com.agilejava.blammo.MessageProducer;

/**
 * A {@link LoggingKitAdapter} that wraps a Log4j {@link Logger}.
 * 
 * @author Wilfred Springer
 * 
 */
public class Log4jLoggingKitAdapter implements LoggingKitAdapter {

	private Logger logger;

	public Log4jLoggingKitAdapter(Class cl) {
		this.logger = Logger.getLogger(cl);
	}

	public void log(String level, MessageProducer producer) {
		if (LoggingKitAdapter.LEVEL_DEBUG.equals(level)) {
			logger.debug(producer.getMessage());
		} else if (LoggingKitAdapter.LEVEL_ERROR.equals(level)) {
			logger.error(producer.getMessage());
		} else if (LoggingKitAdapter.LEVEL_INFO.equals(level)) {
			logger.info(producer.getMessage());
		} else if (LoggingKitAdapter.LEVEL_WARN.equals(level)) {
			logger.warn(producer.getMessage());
		}
	}

	public void log(String level, MessageProducer producer, Throwable throwable) {
		if (LoggingKitAdapter.LEVEL_DEBUG.equals(level)) {
			logger.debug(producer.getMessage(), throwable);
		} else if (LoggingKitAdapter.LEVEL_ERROR.equals(level)) {
			logger.error(producer.getMessage(), throwable);
		} else if (LoggingKitAdapter.LEVEL_INFO.equals(level)) {
			logger.info(producer.getMessage(), throwable);
		} else if (LoggingKitAdapter.LEVEL_WARN.equals(level)) {
			logger.warn(producer.getMessage(), throwable);
		}
	}

}
