package com.agilejava.blammo.adapter.standard;

import java.util.logging.Logger;

import com.agilejava.blammo.LoggingKit;
import com.agilejava.blammo.LoggingKitAdapter;

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
