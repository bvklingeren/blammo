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

/**
 * The interface to be implemented by all logging toolkit adapters. (Think of it
 * as a rudimentary interface similar to Commons Logging, without the
 * bootstrapping mechanism.)
 * 
 * @author Wilfred Springer
 */
public interface LoggingKitAdapter {

	/**
	 * The INFO log level.
	 */
	final String LEVEL_INFO = "info";

	/**
	 * The DEBUG log level.
	 */
	final String LEVEL_DEBUG = "debug";

	/**
	 * The ERROR log level.
	 */
	final String LEVEL_ERROR = "error";

	/**
	 * The WARN log level.
	 */
	final String LEVEL_WARN = "warn";

	/**
	 * Logs a message, passing in the log level and the message.
	 * 
	 * @param level
	 *            The log level. (Either {@link #LEVEL_DEBUG},
	 *            {@link #LEVEL_ERROR}, {@link #LEVEL_INFO} or
	 *            {@link #LEVEL_WARN}).
	 * @param producer
	 *            The {@link MessageProducer} producing the message.
	 */
	void log(String level, MessageProducer producer);

	/**
	 * Logs the message, passing in the log level, the message and the
	 * <code>Throwable</code>.
	 * 
	 * @param level
	 *            The log level. (Either {@link #LEVEL_DEBUG},
	 *            {@link #LEVEL_ERROR}, {@link #LEVEL_INFO} or
	 *            {@link #LEVEL_WARN}).
	 * @param message
	 *            The {@link MessageProducer} producing the message.
	 * @param throwable
	 *            The associated exception.
	 */
	void log(String level, MessageProducer producer, Throwable throwable);

}
