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


import com.thoughtworks.qdox.model.JavaParameter;

public abstract class ReferencePart extends LogMessagePart {

    private String parameterName;

    public ReferencePart(String parameterName) {
        this.parameterName = parameterName;
    }

    public boolean isReference() {
        return true;
    }

    public String getParameterName() {
        return parameterName;
    }

    public abstract JavaParameter getJavaParameter();

    public int getParameterPosition() {
        JavaParameter parameter = getJavaParameter();
        JavaParameter[] parameters = parameter.getParentMethod()
                .getParameters();
        for (int i = 0; i < parameters.length; i++) {
            if (parameter.getName().equals(parameters[i].getName())) {
                return i;
            }
        }
        return -1;
    }

    public boolean isThrowable() {
        return getJavaParameter().getType().getClass().isAssignableFrom(
                Throwable.class);
    }
    
    public String toString() {
        return "{" + parameterName + "}";
    }

}
