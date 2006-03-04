package com.agilejava.blammo;

/**
 * The interface for abstractions representing a low-level logging toolkit. The
 * most important responsibility of implementations of this interface is to
 * create {@link LogKitAdapter}s, allowing Blammo to work against a
 * canonicalized logging interface behind the scenes.
 * 
 * Implement this interface if you want your code to use a non-supported
 * low-level logging toolkit, i.e. application server specific logging
 * mechanisms.
 * 
 * @author Wilfred Springer
 */
public interface LoggingKit {

	/**
	 * Creates a <code>LogKitAdapter</code> for the class passed in.
	 * 
	 * @param cl
	 *            The class is just passed in as a courtesy to low level
	 *            frameworks, who will normally have a smart way of connecting
	 *            log messages to a class and then allow filters to be specified
	 *            on the log messages generated. (Not <code>null</code>.)
	 * @return A <code>LoggingKitAdapter</code>, used internally by Blammo to
	 *         log messages.
	 */
	LoggingKitAdapter createLogKitAdapter(Class cl);

}
