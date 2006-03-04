package com.agilejava.blammo.mojo;

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
