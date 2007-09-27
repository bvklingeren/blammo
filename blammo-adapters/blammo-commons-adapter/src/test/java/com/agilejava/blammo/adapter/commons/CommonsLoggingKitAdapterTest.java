package com.agilejava.blammo.adapter.commons;

import org.apache.commons.logging.Log;
import org.easymock.EasyMock;

import com.agilejava.blammo.LoggingKitAdapter;
import com.agilejava.blammo.MessageProducer;

import junit.framework.TestCase;

public class CommonsLoggingKitAdapterTest extends TestCase {

    private Log log;

    private LoggingKitAdapter adapter;
    
    private MessageProducer producer;

    public void setUp() {
        log = (Log) EasyMock.createMock(Log.class);
        adapter = new CommonsLoggingKitAdapter(log);
        producer = (MessageProducer) EasyMock.createMock(MessageProducer.class);
    }

    public void testLogMessageOnly() {
        log.debug("message");
        log.error("message");
        log.info("message");
        log.warn("message");
        EasyMock.expect(producer.getMessage()).andReturn("message").times(4);
        EasyMock.replay(log);
        EasyMock.replay(producer);
        adapter.log(LoggingKitAdapter.LEVEL_DEBUG, producer);
        adapter.log(LoggingKitAdapter.LEVEL_ERROR, producer);
        adapter.log(LoggingKitAdapter.LEVEL_INFO, producer);
        adapter.log(LoggingKitAdapter.LEVEL_WARN, producer);
        EasyMock.verify(log);
        EasyMock.verify(producer);
    }

    public void testLogMessageAndThrowable() {
    	Throwable throwable = new Throwable("whatever");
        log.debug("message", throwable);
        log.error("message", throwable);
        log.info("message", throwable);
        log.warn("message", throwable);
        EasyMock.expect(producer.getMessage()).andReturn("message").times(4);
        EasyMock.replay(log);
        EasyMock.replay(producer);
        adapter.log(LoggingKitAdapter.LEVEL_DEBUG, producer, throwable);
        adapter.log(LoggingKitAdapter.LEVEL_ERROR, producer, throwable);
        adapter.log(LoggingKitAdapter.LEVEL_INFO, producer, throwable);
        adapter.log(LoggingKitAdapter.LEVEL_WARN, producer, throwable);
        EasyMock.verify(log);
        EasyMock.verify(producer);
    }

}
