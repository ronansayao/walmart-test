/**
 * 
 */
package com.walmart.logistics.pathfinder.services;

import java.util.List;

import com.walmart.logistics.pathfinder.model.Movement;
import com.walmart.logistics.pathfinder.model.Point;
import com.walmart.logistics.pathfinder.vo.MapVO;

/**
 * @author ronan.sayao
 *
 */
public interface PathFinderServices {
	
	public void save(Movement movement);
	
	public void savePoints(List<Point> listPoints);
	
	public void saveMap(MapVO map);
	
	public MapVO findMapByName(String nameMap);
	

}
