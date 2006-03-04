package com.agilejava.blammo.samples;

import java.util.logging.Logger;

import com.agilejava.blammo.BlammoJdkLog;
import com.agilejava.blammo.BlammoLoggerFactory;

public class Sample2 {

    private Logger logger = Logger.getLogger("Sample2");

    private EventLogger eventLogger = (EventLogger) BlammoLoggerFactory.create(
            EventLogger.class, new BlammoJdkLog(logger));

    public int div(int a, int b) {
        try {
            logger.fine("About to do something nasty.");
            return a / b;
        } catch (ArithmeticException ae) {
            if (b == 0) {
                eventLogger.logDivisionByZero(a, b);
                return 0;
            } else {
                eventLogger.logUnexpectedDivisionException(a, b, ae);
                throw ae;
            }
        }
    }

    /**
     * @param logger
     *            The logger to set.
     */
    public void setLogger(EventLogger logger) {
        this.eventLogger = logger;
    }

    /**
     * @return Returns the logger.
     */
    public EventLogger getLogger() {
        return eventLogger;
    }

    /**
     * @blammo.logger
     */
    public interface EventLogger {

        /**
         * The application has attempted to divide something by zero, which is
         * impossible. This is a bug.
         * 
         * @blammo.level warn
         * @blammo.message Attempt to divide {a} by {b}; returning 0 instead.
         */
        void logDivisionByZero(int a, int b);

        /**
         * @blammo.level error
         * @blammo.message Failed to divide {a} by {b}.
         */
        void logUnexpectedDivisionException(int a, int b, ArithmeticException ae);

    }

}
