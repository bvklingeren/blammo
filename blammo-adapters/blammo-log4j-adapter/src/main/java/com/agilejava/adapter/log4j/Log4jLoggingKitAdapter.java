package com.agilejava.adapter.log4j;

import org.apache.log4j.Logger;

import com.agilejava.blammo.LoggingKitAdapter;

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
    
    public void log(String level, String message) {
        if (LoggingKitAdapter.LEVEL_DEBUG.equals(level)) {
            logger.debug(message);
        } else if (LoggingKitAdapter.LEVEL_ERROR.equals(level)) {
            logger.error(message);
        } else if (LoggingKitAdapter.LEVEL_INFO.equals(level)) {
            logger.info(message);
        } else if (LoggingKitAdapter.LEVEL_WARN.equals(level)) {
            logger.warn(message);
        }
    }

    public void log(String level, String message, Throwable throwable) {
        if (LoggingKitAdapter.LEVEL_DEBUG.equals(level)) {
            logger.debug(message, throwable);
        } else if (LoggingKitAdapter.LEVEL_ERROR.equals(level)) {
            logger.error(message, throwable);
        } else if (LoggingKitAdapter.LEVEL_INFO.equals(level)) {
            logger.info(message, throwable);
        } else if (LoggingKitAdapter.LEVEL_WARN.equals(level)) {
            logger.warn(message, throwable);
        }
    }

}
