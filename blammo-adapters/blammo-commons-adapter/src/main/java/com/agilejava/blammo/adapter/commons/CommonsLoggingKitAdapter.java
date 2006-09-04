package com.agilejava.blammo.adapter.commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agilejava.blammo.LoggingKitAdapter;

/**
 * A {@link LoggingKitAdapter} wrapper of a {@link Log} instance.
 * 
 * @author Wilfred Springer
 * 
 */
public class CommonsLoggingKitAdapter implements LoggingKitAdapter {

    /**
     * The {@link Log} object used for logging when this adapter is invoked.
     */
    private Log log;

    public CommonsLoggingKitAdapter(Log log) {
        this.log = log;
    }

    public void log(String level, String message) {
        if (LoggingKitAdapter.LEVEL_DEBUG.equals(level)) {
            log.debug(message);
        } else if (LoggingKitAdapter.LEVEL_ERROR.equals(level)) {
            log.error(message);
        } else if (LoggingKitAdapter.LEVEL_INFO.equals(level)) {
            log.info(message);
        } else if (LoggingKitAdapter.LEVEL_WARN.equals(level)) {
            log.warn(message);
        }
    }

    public void log(String level, String message, Throwable throwable) {
        if (LoggingKitAdapter.LEVEL_DEBUG.equals(level)) {
            log.debug(message, throwable);
        } else if (LoggingKitAdapter.LEVEL_ERROR.equals(level)) {
            log.error(message, throwable);
        } else if (LoggingKitAdapter.LEVEL_INFO.equals(level)) {
            log.info(message, throwable);
        } else if (LoggingKitAdapter.LEVEL_WARN.equals(level)) {
            log.warn(message, throwable);
        }
    }

}
