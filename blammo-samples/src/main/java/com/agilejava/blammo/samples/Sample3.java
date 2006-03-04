package com.agilejava.blammo.samples;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sample3 {

	Logger logger = Logger.getLogger(this.getClass().getName());

	public int div(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException ae) {
            if (b == 0) {
                if (logger.isLoggable(Level.WARNING)) {
                    String message = MessageFormat
                            .format(
                                    "Attempt to divide {0} by {1}; returning 0 instead.",
                                    new Object[] { new Integer(a),
                                            new Integer(b) });
                    logger.warning(message);
                }
                return 0;
            } else {
                if (logger.isLoggable(Level.SEVERE)) {
                    String message = MessageFormat.format(
                            "Failed to divide {0} by {1}.", new Object[] {
                                    new Integer(a), new Integer(b) });
                    logger.log(Level.SEVERE, message);
                }
                throw ae;
            }
        }
	}

}
