/**
 * 
 */
package com.walmart.logistics.pathfinder.exception;

/**
 * @author ronan.sayao
 *
 */
public class InvalidMapEntriesException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	 public InvalidMapEntriesException(String message) {
    	super(message);
	 }
	 
	//Constructor that accepts a message
    public InvalidMapEntriesException(String field, String value)
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
