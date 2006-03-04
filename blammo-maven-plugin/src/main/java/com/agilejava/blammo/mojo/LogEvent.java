package com.agilejava.blammo.mojo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import com.thoughtworks.qdox.model.Type;

/**
 * The class representing an event that can be logged.
 * 
 * @author Wilfred Springer
 */
public class LogEvent {

    /**
     * Comments associated with the event.
     */
    private String comments;

    /**
     * The message identifier.
     */
    private String identifier;

    /**
     * The log level.
     */
    private String level;

    /**
     * The <code>JavaMethod</code> allowing you to log the event.
     */
    private JavaMethod javaMethod;

    /**
     * The different parts of the message. (Exists of {@link LiteralPart}s and
     * {@link ReferencePart}s.
     */
    private List messageParts;

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return Returns the comments.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the <code>JavaMethod</code> allowing you to log the event.
     * 
     * @param javaMethod
     *            The <code>JavaMethod</code> allowing you to log the event.
     */
    public void setJavaMethod(JavaMethod javaMethod) {
        this.javaMethod = javaMethod;
    }

    /**
     * Returns the <code>JavaMethod</code> allowing you to log the event.
     * 
     * @return Returns the <code>JavaMethod</code> allowing you to log the
     *         event.
     */
    public JavaMethod getJavaMethod() {
        return javaMethod;
    }

    /**
     * Parses the messages and turns it into a <code>List</code> of message
     * parts.
     * 
     * @param message
     *            Parses the message specified by the
     *            {@link BlammoGeneratorMojo#TAG_MESSAGE} annotation.
     * @throws LogMessageFormatException
     *             If the message format is a problem.
     */
    public void parseMessage(String message) throws LogMessageFormatException {
        int prev = 0;
        int cur = 0;
        messageParts = new ArrayList();
        while ((cur = message.indexOf('{', prev)) != -1) {
            if (cur > prev) {
                messageParts.add(new LiteralPart(message.substring(prev, cur)));
            }
            prev = cur;
            cur = message.indexOf('}', prev);
            if (cur < 0) {
                throw new LogMessageFormatException(message, prev,
                        "Expecting '}'.");
            } else {
                String reference = message.substring(prev + 1, cur);
                if (javaMethod.getParameterByName(reference) == null) {
                    throw new LogMessageFormatException(message, prev,
                            "Parameter " + reference + " does not exist.");
                } else {
                    messageParts.add(new ReferencePart(message.substring(
                            prev + 1, cur)) {
                        public JavaParameter getJavaParameter() {
                            return javaMethod
                                    .getParameterByName(getParameterName());
                        }
                    });
                }
            }
            prev = cur + 1;
        }
        if (prev < message.length()) {
            messageParts.add(new LiteralPart(message.substring(prev)));
        }
    }

    /**
     * Sets the <code>List</code> of message parts.
     * 
     * @param messageParts
     *            The messageParts to set.
     */
    public void setMessageParts(List messageParts) {
        this.messageParts = messageParts;
    }

    /**
     * Returns the <code>List</code> of message parts.
     * 
     * @return Returns the messageParts.
     */
    public List getMessageParts() {
        return messageParts;
    }

    /**
     * @param level
     *            The level to set.
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return Returns the level.
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param identifier
     *            The identifier to set.
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * @return Returns the identifier.
     */
    public String getIdentifier() {
        if (identifier == null) {
            return javaMethod.getName().substring(3);
        } else {
            return identifier;
        }
    }

    public boolean isPassingThrowable() {
        return getThrowable() != null;
    }

    /**
     * Returns the last <code>Throwable</code> parameter, if there is any.
     * 
     * @return The last <code>Throwable</code> parameter, if there is any.
     */
    public JavaParameter getThrowable() {
        JavaParameter[] parameters = javaMethod.getParameters();
        Type throwable = new Type(Throwable.class.getName());
        for (int i = parameters.length - 1; i >= 0; i--) {
            if (parameters[i].getType().isA(throwable)) {
                return parameters[i];
            }
        }
        return null;
    }

    public String getMessage() {
        StringBuffer buffer = new StringBuffer();
        Iterator iterator = messageParts.iterator();
        while (iterator.hasNext()) {
            LogMessagePart part = (LogMessagePart) iterator.next();
            if (part instanceof ReferencePart) {
                buffer.append("...");
            } else {
                buffer.append(part.toString());
            }
        }
        return buffer.toString();
    }

}
