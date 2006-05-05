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

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple example using the default logging kit found in the JDK. Note that
 * eight out of 19 lines are for logging two messages, and to prevent messages
 * from being generated if the log levels have been set to other levels.
 * 
 * @author Wilfred Springer
 */
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
