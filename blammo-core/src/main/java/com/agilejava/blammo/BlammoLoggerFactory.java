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
 * The class for creating a logger. Note that a logger is not required to
 * implement a certain interface. The only requirement is that it implements an
 * interface, and that this interface has <code>logXXXXXX</code> operations
 * that have blammo annotations.
 * 
 * @author Wilfred Springer
 */
public class BlammoLoggerFactory {

	/**
	 * Returns the Blammo generated implementation class of the Blammo logger
	 * interface defined by the user. Note that the class returned is expected
	 * to implement the {@link BlammoLogger} interface. Future versions of this
	 * operation might delegate the naming strategy to another object.
	 * 
	 * @param intf
	 *            The interface for which we need the implementation class.
	 * @return The Blammo generated implementation class of the logger interface
	 *         defined by the user.
	 * @throws BlammoException
	 *             If the operation fails to load the implementation class from
	 *             the class path.
	 */
	private static Class getImplementationClass(Class intf)
			throws BlammoException {
		String fqn = intf.getName().replace('$', '_');
		StringBuffer buffer = new StringBuffer();
		buffer.append(intf.getPackage().getName());
		buffer.append('.');
		buffer.append("Blammo");
		buffer.append(fqn.substring(fqn.lastIndexOf('.') + 1));
		buffer.append("Impl");
		try {
			// Maybe change this in the future to use the ClassLoader used to
			// load the intf class.
			return Class.forName(buffer.toString());
		} catch (ClassNotFoundException cnfe) {
			throw new BlammoException(cnfe);
		}
	}

	/**
	 * Creates a new logger for the interface passed in.
	 * 
	 * @param intf
	 *            The interface specifying the logger interface.
	 * @return An implementation of this interface.
	 * @throws BlammoException
	 *             If this operation fails to create an implementation of this
	 *             interface.
	 */
	public static Object create(Class intf) throws BlammoException {
		return create(intf, new StdErrLoggingKitAdapter());
	}

	/**
	 * Creates a new logger for the interface passed in, using the
	 * {@link LoggingKit} to create a low level logging kit specific version.
	 * 
	 * @param intf
	 *            The interface for which we need to construct a new logger.
	 * @param kit
	 *            An object representing a particular low level logging kit.
	 * @return A new logger, implementing the <code>intf</code> interface.
	 * @throws BlammoException
	 *             If this operation fails to create an implementation of this
	 *             interface.
	 */
	public static Object create(Class intf, LoggingKit kit)
			throws BlammoException {
		LoggingKitAdapter log = kit.createLogKitAdapter(intf);
		return create(intf, log);
	}

	/**
	 * Creates a new logger for the logging interface passed in, using the
	 * {@link LoggingKitAdapter} to create a low level logging kit specific
	 * version.
	 * 
	 * @param intf
	 *            The interface for which we need to construct a new logger.
	 * @param adapter
	 *            An object representing a particular low level logging logger.
	 * @return A new logger, implementing the <code>intf</code> interface.
	 * @throws BlammoException
	 *             If this operation fails to create an implementation of this
	 *             interface.
	 */
	public static Object create(Class intf, LoggingKitAdapter adapter)
			throws BlammoException {
		Class implClass = getImplementationClass(intf);
		try {
			BlammoLogger logger = (BlammoLogger) implClass.newInstance();
			logger.setLoggingKitAdapter(adapter);
			return logger;
		} catch (InstantiationException ie) {
			throw new BlammoException(ie);
		} catch (IllegalAccessException iae) {
			throw new BlammoException(iae);
		}
	}
}
