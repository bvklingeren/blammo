package com.agilejava.blammo;

/**
 * The interface used for constructing the message. Allows construction of the
 * messages to be delayed until the {@link LoggingKit} deems it appropriate.
 * 
 * @author Wilfred Springer
 * 
 */
public interface MessageProducer {

	/**
	 * Returns the message.
	 * 
	 * @return The message.
	 */
	String getMessage();
	
}
