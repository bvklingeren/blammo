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

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An example of using the standard JDK logging kit, using the
 * {@link java.text.MessageFormat MessageFormat} class to make it a little
 * easier to format the message. This could be further refactored by using a
 * <code>ResourceBundle</code>, but it's questionable if that would make it
 * any more readable or maintainable.
 * 
 * @author Wilfred Springer
 */
public class Sample3 {

	Logger logger = Logger.getLogger(this.getClass().getName());

	public int div(int a, int b) {
		try {
			return a / b;
		} catch (ArithmeticException ae) {
			if (b == 0) {
				if (logger.isLoggable(Level.WARNING)) {
					String message = MessageFormat.format(
							"Attempt to divide {0} by 0; returning 0 instead.",
							new Object[] { new Integer(a), new Integer(b) });
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
