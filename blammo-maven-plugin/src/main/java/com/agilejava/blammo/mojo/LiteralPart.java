package com.agilejava.blammo.mojo;

public class LiteralPart extends LogMessagePart {

    private String literal;

    public LiteralPart(String literal) {
        this.literal = literal;
    }

    public boolean isReference() {
        return false;
    }

    public String toString() {
        return literal;
    }

}
