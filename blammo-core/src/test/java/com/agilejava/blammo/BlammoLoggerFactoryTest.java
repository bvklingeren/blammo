package com.agilejava.blammo;

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
