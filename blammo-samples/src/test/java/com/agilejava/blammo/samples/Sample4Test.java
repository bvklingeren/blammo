package com.agilejava.blammo.samples;

import org.easymock.MockControl;

import junit.framework.TestCase;

public class Sample4Test extends TestCase {

    MockControl loggerControl;

    Sample4.EventLogger logger;

    public void setUp() {
        loggerControl = MockControl.createControl(Sample4.EventLogger.class);
        logger = (Sample4.EventLogger) loggerControl.getMock();
    }

    public void testDivisionByZero() {
        Sample4 sample4 = new Sample4();
        sample4.setEventLogger(logger);
        int a = 3;
        int b = 0;
        logger.logDivisionByZero(a);
        loggerControl.replay();
        sample4.div(a, b);
        loggerControl.verify();
    }

    public void testDefaultDivisionByZero() {
        Sample4 sample4 = new Sample4();
        int a = 3;
        int b = 0;
        sample4.div(a, b);
    }

}
