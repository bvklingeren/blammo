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


import org.easymock.MockControl;

import junit.framework.TestCase;

/**
 * Tests the basic operations defined on BlammoLoggerFactory.
 * 
 * @author Wilfred Springer
 */
public class BlammoLoggerFactoryTest extends TestCase {

	/**
	 * Tests the default construction mechanism.
	 * 
	 * @see BlammoLoggerFactory#create(Class)
	 */
	public void testDefaultInstantiation() {
		Object logger = BlammoLoggerFactory.create(TestLogger.class);
		assertTrue(logger instanceof BlammoTestLoggerImpl);
		assertNotNull(((BlammoTestLoggerImpl) logger).getLoggingKitAdapter());
	}

	/**
	 * Tests the construction mechanism in which we pass in a
	 * {@link LoggingKitAdapter} ourselves.
	 * 
	 * @see BlammoLoggerFactory#create(Class, LoggingKitAdapter)
	 */
	public void testInstantionWithLoggingKitAdapter() {
		MockControl adapterControl = MockControl
				.createControl(LoggingKitAdapter.class);
		LoggingKitAdapter adapter = (LoggingKitAdapter) adapterControl
				.getMock();
		Object logger = BlammoLoggerFactory.create(TestLogger.class, adapter);
		assertTrue(logger instanceof BlammoTestLoggerImpl);
		BlammoTestLoggerImpl impl = (BlammoTestLoggerImpl) logger;
		assertEquals(adapter, impl.getLoggingKitAdapter());
	}

	/**
	 * Tests the construction mechanism in which we pass in a {@link LoggingKit}.
	 *
	 * @see BlammoLoggerFactory#create(Class, LoggingKit)
	 */
	public void testInstantionWithLoggingKit() {
		MockControl adapterControl = MockControl
				.createControl(LoggingKitAdapter.class);
		MockControl kitControl = MockControl.createControl(LoggingKit.class);
		LoggingKitAdapter adapter = (LoggingKitAdapter) adapterControl
				.getMock();
		LoggingKit kit = (LoggingKit) kitControl.getMock();
		kit.createLogKitAdapter(TestLogger.class);
		kitControl.setReturnValue(adapter);
		kitControl.replay();
		adapterControl.replay();
		Object logger = BlammoLoggerFactory.create(TestLogger.class, kit);
		assertTrue(logger instanceof BlammoTestLoggerImpl);
		BlammoTestLoggerImpl impl = (BlammoTestLoggerImpl) logger;
		assertEquals(adapter, impl.getLoggingKitAdapter());
		adapterControl.verify();
		kitControl.verify();
	}

}
