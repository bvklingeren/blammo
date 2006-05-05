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
 * An implementation of the {@link com.agilejava.blammo.TestLogger} interface,
 * allowing us to get hold of the LoggingKitAdapter, when required.
 * 
 * @author Wilfred Springer
 */
public class BlammoTestLoggerImpl implements BlammoLogger {

	private LoggingKitAdapter adapter;

	public void setLoggingKitAdapter(LoggingKitAdapter adapter) {
		this.adapter = adapter;
	}
	
	public LoggingKitAdapter getLoggingKitAdapter() {
		return adapter;
	}
	
}
