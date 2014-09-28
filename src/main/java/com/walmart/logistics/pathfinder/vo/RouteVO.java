/**
 * 
 */
package com.walmart.logistics.pathfinder.vo;

import java.util.List;

/**
 * @author ronan.sayao
 *
 */
public class RouteVO {
	
	private List<String> path;
	private Double totalCost;
	private String errorMessage;
	/**
	 * @return the path
	 */
	public List<String> getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(List<String> path) {
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
	@Override
	public String toString() {
		return this.path.toString()+" - "+totalCost.toString();
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
