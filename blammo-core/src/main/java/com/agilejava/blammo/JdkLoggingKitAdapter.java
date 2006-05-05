package com.agilejava.blammo;

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
 * A <code>BlammoLog</code> implementation
 * 
 * @author Wilfred Springer
 */
public class JdkLoggingKitAdapter implements LoggingKitAdapter {

	private Logger logger;

	public JdkLoggingKitAdapter(Logger logger) {
		this.logger = logger;
	}

	private Level getLevel(String level) {
		if (LoggingKitAdapter.LEVEL_DEBUG.equals(level)) {
			return Level.FINE;
		} else if (LoggingKitAdapter.LEVEL_ERROR.equals(level)) {
			return Level.SEVERE;
		} else if (LoggingKitAdapter.LEVEL_INFO.equals(level)) {
			return Level.INFO;
		} else if (LoggingKitAdapter.LEVEL_WARN.equals(level)) {
			return Level.WARNING;
		} else {
			return Level.FINEST;
		}
	}

	public void log(String level, String message) {
		Level logLevel = getLevel(level);
		if (logger.isLoggable(logLevel)) {
			logger.log(logLevel, message);
		}
	}

	public void log(String level, String message, Throwable throwable) {
		Level logLevel = getLevel(level);
		if (logger.isLoggable(logLevel)) {
			logger.log(logLevel, message, throwable);
		}
	}

}
