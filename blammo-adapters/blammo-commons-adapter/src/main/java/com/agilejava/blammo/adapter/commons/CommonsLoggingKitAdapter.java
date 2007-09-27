package com.agilejava.blammo.adapter.commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agilejava.blammo.LoggingKitAdapter;
import com.agilejava.blammo.MessageProducer;

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

    public void log(String level, MessageProducer producer) {
        if (LoggingKitAdapter.LEVEL_DEBUG.equals(level)) {
            log.debug(producer.getMessage());
        } else if (LoggingKitAdapter.LEVEL_ERROR.equals(level)) {
            log.error(producer.getMessage());
        } else if (LoggingKitAdapter.LEVEL_INFO.equals(level)) {
            log.info(producer.getMessage());
        } else if (LoggingKitAdapter.LEVEL_WARN.equals(level)) {
            log.warn(producer.getMessage());
        }
    }

    public void log(String level, MessageProducer producer, Throwable throwable) {
        if (LoggingKitAdapter.LEVEL_DEBUG.equals(level)) {
            log.debug(producer.getMessage(), throwable);
        } else if (LoggingKitAdapter.LEVEL_ERROR.equals(level)) {
            log.error(producer.getMessage(), throwable);
        } else if (LoggingKitAdapter.LEVEL_INFO.equals(level)) {
            log.info(producer.getMessage(), throwable);
        } else if (LoggingKitAdapter.LEVEL_WARN.equals(level)) {
            log.warn(producer.getMessage(), throwable);
        }
    }

}
