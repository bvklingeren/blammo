package com.agilejava.blammo.adapter.standard;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.agilejava.blammo.LoggingKitAdapter;
import com.agilejava.blammo.MessageProducer;

/**
 * A <code>BlammoLog</code> implementation wrapping the standard Java logging
 * mechanism.
 * 
 * @author Wilfred Springer
 */
public class JdkLoggingKitAdapter implements LoggingKitAdapter {

    private Logger logger;

    public JdkLoggingKitAdapter(Logger logger) {
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
