package com.agilejava.blammo.samples;

import org.easymock.MockControl;

import junit.framework.TestCase;

public class Sample5Test extends TestCase {

    MockControl loggerControl;

    Sample5.EventLogger logger;

    public void setUp() {
        loggerControl = MockControl.createControl(Sample5.EventLogger.class);
        logger = (Sample5.EventLogger) loggerControl.getMock();
    }

    public void testDivisionByZero() {
        Sample5 sample5 = new Sample5();
        sample5.setLogger(logger);
        int a = 3;
        int b = 0;
        logger.logDivisionByZero(a);
        loggerControl.replay();
        sample5.div(a, b);
        loggerControl.verify();
    }

    public void testDefaultDivisionByZero() {
        Sample5 sampl5 = new Sample5();
        int a = 3;
        int b = 0;
        sampl5.div(a, b);
    }

}
