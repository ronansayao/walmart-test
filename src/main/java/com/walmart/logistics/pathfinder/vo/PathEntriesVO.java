/**
 * 
 */
package com.walmart.logistics.pathfinder.vo;


/**
 * @author ronan.sayao
 *
 */
public class PathEntriesVO {
	
	private String mapName;
	private String origin;
	private String destination;
	private Double autonomy;
	private Double gasprice;
	
	/**
	 * @return the mapName
	 */
	public String getMapName() {
		return mapName;
	}
	/**
	 * @param mapName the mapName to set
	 */
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}
	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * @return the autonomy
	 */
	public Double getAutonomy() {
		return autonomy;
	}
	/**
	 * @param autonomy the autonomy to set
	 */
	public void setAutonomy(Double autonomy) {
		this.autonomy = autonomy;
	}
	/**
	 * @return the gasprice
	 */
	public Double getGasprice() {
		return gasprice;
	}
	/**
	 * @param gasprice the gasprice to set
	 */
	public void setGasprice(Double gasprice) {
		this.gasprice = gasprice;
	}
	

}
