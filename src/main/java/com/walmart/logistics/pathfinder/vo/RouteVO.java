/**
 * 
 */
package com.walmart.logistics.pathfinder.vo;

import java.util.LinkedList;

/**
 * @author ronan.sayao
 *
 */
public class RouteVO {
	
	private LinkedList<String> path;
	private Double totalCost;
	/**
	 * @return the path
	 */
	public LinkedList<String> getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(LinkedList<String> path) {
		this.path = path;
	}
	/**
	 * @return the totalCost
	 */
	public Double getTotalCost() {
		return totalCost;
	}
	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

}
