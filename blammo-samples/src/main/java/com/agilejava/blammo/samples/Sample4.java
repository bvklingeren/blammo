package com.agilejava.blammo.samples;

/* 
 * Copyright (C) 2006, Wilfred Springer
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

import com.agilejava.blammo.BlammoLoggerFactory;

/**
 * A simple example with a logger that can be changed.
 * 
 * @author Wilfred Springer
 */
public class Sample4 {

    private EventLogger eventLogger = (EventLogger) BlammoLoggerFactory
            .create(EventLogger.class);

    public void setEventLogger(EventLogger eventLogger) {
        this.eventLogger = eventLogger;
    }

    public EventLogger getEventLogger() {
        return eventLogger;
    }

    public int div(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException ae) {
            if (b == 0) {
                eventLogger.logDivisionByZero(a);
                return 0;
            } else {
                eventLogger.logUnexpectedDivisionException(a, b, ae);
                throw ae;
            }
        }
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
         * @blammo.message Attempt to divide {a} by 0; returning 0 instead.
         */
        void logDivisionByZero(int a);

        /**
         * @blammo.level error
         * @blammo.message Failed to divide {a} by {b}.
         */
        void logUnexpectedDivisionException(int a, int b, ArithmeticException ae);

    }

}
