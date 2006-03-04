package com.agilejava.blammo.mojo;

public abstract class LogMessagePart {

    public abstract boolean isReference();

    public boolean isLiteral() {
        return !isReference();
    }

}
