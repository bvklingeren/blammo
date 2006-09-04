package com.agilejava.blammo.adapter.commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agilejava.blammo.LoggingKit;
import com.agilejava.blammo.LoggingKitAdapter;

/**
 * The entrance into the Commons Logging logging kit.
 * 
 * 
 * @author Wilfred Springer
 *
 */
public class CommonsLoggingKit implements LoggingKit {

    public LoggingKitAdapter createLogKitAdapter(Class cl) {
        Log log = LogFactory.getLog(cl);
        return new CommonsLoggingKitAdapter(log);
    }

}
