package com.agilejava.blammo.mojo;

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


public class LogMessageFormatException extends Exception {

    /**
     * The serial version UID used to identify this version.
     */
    private static final long serialVersionUID = -7126838803184065596L;

    private String source;

    private int position;

    private String expectation;

    public LogMessageFormatException(String source, int position) {
        this.source = source;
        this.position = position;
    }

    public LogMessageFormatException(String source, int position,
            String expectation) {
        this(source, position);
        this.expectation = expectation;
    }

    public String getMessage() {
        return "Failed to parse message at position " + position
                + (expectation != null ? ": " + expectation : ".");
    }

}
