/**
 * 
 */
package com.walmart.logistics.pathfinder.model;

import java.io.Serializable;
import java.util.List;


/**
 * @author ronan.sayao
 *
 */
public class Graph implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<Point> points;
	private final List<Movement> movements;
	
	public Graph(List<Point> points, List<Movement> movements) {
	    this.points = points;
	    this.movements = movements;
	}
	

	/**
	 * @return the tracks
	 */
	public List<Point> getPoints() {
		return points;
	}

	/**
	 * @return the movements
	 */
	public List<Movement> getMovements() {
		return movements;
	}
	
	

}
