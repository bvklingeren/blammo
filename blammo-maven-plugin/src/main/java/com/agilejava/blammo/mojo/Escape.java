package com.agilejava.blammo.mojo;

/* 
 * Copyright (C) 2007 Erwin Bolwidt
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.qdox.model.JavaClass;

/**
 * Various escaping functions that can be used by code that is called from a
 * StringTemplate.
 * 
 * @author Erwin Bolwidt
 */
public class Escape {
    private static final String STRING_ESCAPE_BACKSLASH = "\"\\";

    public static String stringEscape(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder b = new StringBuilder(str.length());
        for (int i = 0, l = str.length(); i < l; i++) {
            char ch = str.charAt(i);
            if (STRING_ESCAPE_BACKSLASH.indexOf(ch) != -1) {
                b.append('\\');
                b.append(ch);
            } else if (ch > 126) {
                b.append("\\u");
                b.append(hexDigit((ch >>> 12) & 0x0f));
                b.append(hexDigit((ch >>> 8) & 0x0f));
                b.append(hexDigit((ch >>> 4) & 0x0f));
                b.append(hexDigit(ch & 0x0f));
            } else {
                b.append(ch);
            }
        }
        return b.toString();
    }

    private static char hexDigit(int d) {
        if (d < 10) {
            return (char) (d + '0');
        } else {
            return (char) (d + ('A' - 10));
        }
    }
}
