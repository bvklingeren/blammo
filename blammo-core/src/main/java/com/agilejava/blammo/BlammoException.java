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
 * A general purpose but Blammo specific RuntimeException based Exception class.
 * May be specialized in the future.
 * 
 * @author Wilfred Springer
 */
public class BlammoException extends RuntimeException {

	/**
	 * The serialVersionUID required by the serialization specification.
	 */
	private static final long serialVersionUID = -8652799936435441312L;

	/**
	 * Constructs a new instance wrapping a {@link ClassNotFoundException}.
	 * 
	 * @param cnfe
	 *            The <code>ClassNotFoundException</code> to be wrapped by a
	 *            new instance.
	 */
	public BlammoException(ClassNotFoundException cnfe) {
		super(cnfe);
	}

	/**
	 * Constructs a new instance wrapping a {@link InstantiationException}.
	 * 
	 * @param ie
	 *            The <code>InstantiationException</code> to be wrapped by a
	 *            new instance.
	 */
	public BlammoException(InstantiationException ie) {
		super(ie);
	}

	/**
	 * Constructs a new instance wrapping a {@link IllegalAccessException}.
	 * 
	 * 
	 * @param iae
	 *            The <code>IllegalAccessException</code> to be wrapped by a
	 *            new instance of this class.
	 */
	public BlammoException(IllegalAccessException iae) {
		super(iae);
	}

}
