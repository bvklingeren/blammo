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


import java.util.logging.Logger;

/**
 * An implementation of the {@link LoggingKit} interface, constructing
 * {@link LoggingKitAdapter} instances that wrap JDK loggers.
 * 
 * @author Wilfred Springer
 */
public class JdkLoggingKit implements LoggingKit {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agilejava.blammo.BlammoLogFactory#createBlammoLog(java.lang.Class)
	 */
	public LoggingKitAdapter createLogKitAdapter(Class cl) {
		Logger logger = Logger.getLogger(cl.getName());
		return new JdkLoggingKitAdapter(logger);
	}

}
