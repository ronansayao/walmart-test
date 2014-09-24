/**
 * 
 */
package com.walmart.logistics.pathfinder.vo;

import java.util.List;

import com.walmart.logistics.pathfinder.model.Movement;
import com.walmart.logistics.pathfinder.model.Point;

/**
 * @author ronan.sayao
 *
 */
public class MapVO {
	
	private String name;
	private List<Movement> movements;
	private Iterable<Point> points;
	
	public MapVO (){
		
	}
	
	public MapVO (String name, List<Movement> movements, Iterable<Point> points) {
		this.name = name;
		this.movements = movements;
		this.points = points;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the movements
	 */
	public List<Movement> getMovements() {
		return movements;
	}
	/**
	 * @param movements the movements to set
	 */
	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}

	/**
	 * @return the points
	 */
	public Iterable<Point> getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(Iterable<Point> points) {
		this.points = points;
	}

	

}
