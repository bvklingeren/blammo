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
 * The interface to be implemented by Blammo loggers, allowing us to plug in a
 * certain kind of {@link LoggingKitAdapter} to facilitate a particular way of
 * logging.
 * 
 * @author Wilfred Springer
 * 
 */
public interface BlammoLogger {

    /**
     * Sets the {@link LoggingKitAdapter} to be used by this logger.
     * 
     * @param log
     */
    void setLoggingKitAdapter(LoggingKitAdapter log);

    /**
     * Sets an interceptor for the events. Whereas {@link LoggingKitAdapter}s
     * are expected to <em>always</em> log the event, interceptors can decide
     * wether the event should be logged at all. Events are identified by id.
     */
    void setInterceptor(Interceptor interceptor);

    public interface Interceptor {

        /**
         * Intercepts the event, and decides if it should be processed any
         * further.
         * 
         * @param eventId The identifier of the event, if it exists. (Could be
         *        <code>null</code>.)
         * @param cl The class generating the event.
         * @param method The method generating the event.
         * @return A boolean indicating if this event should be accepted for
         *         further processing.
         */
        boolean accepts(String eventId, Class cl, String method);

    }
    
    public class DefaultInterceptor implements Interceptor {

        public boolean accepts(String eventId, Class cl, String method) {
            return true;
        }
        
    }

}
