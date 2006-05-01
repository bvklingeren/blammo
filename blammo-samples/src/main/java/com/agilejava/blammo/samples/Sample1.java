package com.agilejava.blammo.samples;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Sample1 {

    Logger logger = Logger.getLogger(this.getClass().getName());

    public int div(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException ae) {
            if (b == 0) {
                if (logger.isLoggable(Level.WARNING)) {
                    logger.warning("Attempt to divide " + a + " by " + b
                            + "; returning 0 instead.");
                }
                return 0;
            } else {
                if (logger.isLoggable(Level.SEVERE)) {
                    logger.log(Level.SEVERE, "Failed to divide " + a + " by "
                            + b + ".");
                }
                throw ae;
            }
        }
    }
	

}
