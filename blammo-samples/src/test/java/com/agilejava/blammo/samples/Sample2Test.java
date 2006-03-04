package com.agilejava.blammo.samples;

import org.easymock.MockControl;

import junit.framework.TestCase;

public class Sample2Test extends TestCase {

    MockControl loggerControl;

    Sample2.EventLogger logger;

    public void setUp() {
        loggerControl = MockControl.createControl(Sample2.EventLogger.class);
        logger = (Sample2.EventLogger) loggerControl.getMock();
    }

    public void testDivisionByZero() {
        Sample2 sample2 = new Sample2();
        sample2.setLogger(logger);
        int a = 3;
        int b = 0;
        logger.logDivisionByZero(a, b);
        loggerControl.replay();
        sample2.div(a, b);
        loggerControl.verify();
    }

    public void testDefaultDivisionByZero() {
        Sample2 sampl2 = new Sample2();
        int a = 3;
        int b = 0;
        sampl2.div(a, b);
    }

}
