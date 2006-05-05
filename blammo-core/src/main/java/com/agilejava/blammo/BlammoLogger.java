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
 * The interface to be implemented by Blammo loggers, allowing us to plug in a
 * certain kind of {@link LoggingKitAdapter} to facilitate a particular way of
 * logging.
 * 
 * @author Wilfred Springer
 * 
 */
public interface BlammoLogger {

	/**
	 * Sets the {@link LoggingKitAdapter} to be used by this logger.
	 * 
	 * @param log
	 */
	void setLoggingKitAdapter(LoggingKitAdapter log);

}
