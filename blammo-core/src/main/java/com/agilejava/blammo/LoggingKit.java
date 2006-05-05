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
 * The interface for abstractions representing a low-level logging toolkit. The
 * most important responsibility of implementations of this interface is to
 * create {@link LogKitAdapter}s, allowing Blammo to work against a
 * canonicalized logging interface behind the scenes.
 * 
 * Implement this interface if you want your code to use a non-supported
 * low-level logging toolkit, i.e. application server specific logging
 * mechanisms.
 * 
 * @author Wilfred Springer
 */
public interface LoggingKit {

	/**
	 * Creates a <code>LogKitAdapter</code> for the class passed in.
	 * 
	 * @param cl
	 *            The class is just passed in as a courtesy to low level
	 *            frameworks, who will normally have a smart way of connecting
	 *            log messages to a class and then allow filters to be specified
	 *            on the log messages generated. (Not <code>null</code>.)
	 * @return A <code>LoggingKitAdapter</code>, used internally by Blammo to
	 *         log messages.
	 */
	LoggingKitAdapter createLogKitAdapter(Class cl);

}
