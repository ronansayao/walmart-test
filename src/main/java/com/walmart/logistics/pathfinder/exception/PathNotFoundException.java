/**
 * 
 */
package com.walmart.logistics.pathfinder.exception;

/**
 * @author ronan.sayao
 *
 */
public class PathNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
    
    public PathNotFoundException(String message) {
    	super(message);
    }
    
    //Constructor that accepts a message
    public PathNotFoundException(String origin, String destination)
    {	
    	super();
    	this.message = "Path not found between"+origin+" and "+destination;

    }

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}
