/**
 * 
 */
package com.walmart.logistics.pathfinder.services;

import com.walmart.logistics.pathfinder.exception.InvalidMapEntriesException;
import com.walmart.logistics.pathfinder.exception.InvalidPathEntriesException;
import com.walmart.logistics.pathfinder.exception.PathNotFoundException;
import com.walmart.logistics.pathfinder.model.Movement;
import com.walmart.logistics.pathfinder.vo.MapVO;
import com.walmart.logistics.pathfinder.vo.PathEntriesVO;
import com.walmart.logistics.pathfinder.vo.RouteVO;

/**
 * @author ronan.sayao
 *
 */
public interface PathFinderServices {
	
	public void save(Movement movement);
	
	public void saveMap(MapVO map);
	
	public MapVO findMapByName(String nameMap);
	
	public RouteVO getPathAndCosts(PathEntriesVO pathEntriesVO) throws PathNotFoundException;
	
	public void validateMapEntries(MapVO mapVO) throws InvalidMapEntriesException;
	
	public void validatePathEntries(PathEntriesVO pathEntriesVO) throws InvalidPathEntriesException;
	

}
