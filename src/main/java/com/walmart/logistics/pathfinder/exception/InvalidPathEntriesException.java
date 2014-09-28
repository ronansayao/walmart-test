/**
 * 
 */
package com.walmart.logistics.pathfinder.exception;

/**
 * @author ronan.sayao
 *
 */
public class InvalidPathEntriesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	 public InvalidPathEntriesException(String message) {
    	super(message);
	 }
	 
	//Constructor that accepts a message
    public InvalidPathEntriesException(String field, String value)
    {	
    	super();
    	this.message = "The field "+field+" value(s) "+value+" is invalid.";

    }
    
    /**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}
