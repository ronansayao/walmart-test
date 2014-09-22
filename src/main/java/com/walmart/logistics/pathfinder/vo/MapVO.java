/**
 * 
 */
package com.walmart.logistics.pathfinder.vo;

import java.util.List;

import com.walmart.logistics.pathfinder.model.Movement;

/**
 * @author ronan.sayao
 *
 */
public class MapVO {
	
	private String name;
	private List<Movement> movements;
	
	public MapVO (){
		
	}
	
	public MapVO (String name, List<Movement> movements) {
		this.name = name;
		this.movements = movements;
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

	

}
