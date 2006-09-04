package com.agilejava.adapter.log4j;

import com.agilejava.blammo.LoggingKit;
import com.agilejava.blammo.LoggingKitAdapter;

/**
 * A {@link LoggingKit} that will create wrappers of Log4j loggers. 
 * 
 * @author Wilfred Springer
 *
 */
public class Log4jLoggingKit implements LoggingKit {

    public LoggingKitAdapter createLogKitAdapter(Class cl) {
        return new Log4jLoggingKitAdapter(cl);
    }

}
