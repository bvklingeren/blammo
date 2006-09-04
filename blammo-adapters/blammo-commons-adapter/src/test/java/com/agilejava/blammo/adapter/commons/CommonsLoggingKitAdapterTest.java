package com.agilejava.blammo.adapter.commons;

import org.apache.commons.logging.Log;
import org.easymock.EasyMock;

import com.agilejava.blammo.LoggingKitAdapter;

import junit.framework.TestCase;

public class CommonsLoggingKitAdapterTest extends TestCase {

    private Log log;

    private LoggingKitAdapter adapter;

    public void setUp() {
        log = (Log) EasyMock.createMock(Log.class);
        adapter = new CommonsLoggingKitAdapter(log);
    }

    public void testLogMessageOnly() {
        adapter.log(LoggingKitAdapter.LEVEL_DEBUG, "test1");
        adapter.log(LoggingKitAdapter.LEVEL_ERROR, "test2");
        adapter.log(LoggingKitAdapter.LEVEL_INFO, "test3");
        adapter.log(LoggingKitAdapter.LEVEL_WARN, "test4");
        EasyMock.replay(log);
        log.debug("test1");
        log.error("test2");
        log.info("test3");
        log.warn("test4");
        EasyMock.verify(log);
    }

    public void testLogMessageAndThrowable() {
        Throwable throwable1 = new Throwable("test1");
        Throwable throwable2 = new Throwable("test2");
        Throwable throwable3 = new Throwable("test3");
        Throwable throwable4 = new Throwable("test4");
        adapter.log(LoggingKitAdapter.LEVEL_DEBUG, "test1", throwable1);
        adapter.log(LoggingKitAdapter.LEVEL_ERROR, "test2", throwable2);
        adapter.log(LoggingKitAdapter.LEVEL_INFO, "test3", throwable3);
        adapter.log(LoggingKitAdapter.LEVEL_WARN, "test4", throwable4);
        EasyMock.replay(log);
        log.debug("test1", throwable1);
        log.error("test2", throwable2);
        log.info("test3", throwable3);
        log.warn("test4", throwable4);
        EasyMock.verify(log);
    }

}
