/**
 * 
 */
package com.walmart.logistics.pathfinder.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.logistics.pathfinder.model.Map;
import com.walmart.logistics.pathfinder.model.Movement;
import com.walmart.logistics.pathfinder.model.Point;
import com.walmart.logistics.pathfinder.repository.MapRepository;
import com.walmart.logistics.pathfinder.repository.MovementRepository;
import com.walmart.logistics.pathfinder.repository.PointRepository;
import com.walmart.logistics.pathfinder.vo.MapVO;

/**
 * @author ronan.sayao
 *
 */
@Service
public class PathFinderServicesImpl implements PathFinderServices {
	
	@Autowired
	private MovementRepository movementRepository;
	
	@Autowired
	private PointRepository pointRepository;
	
	@Autowired
	private MapRepository mapRepository;
	
	@Transactional
	public void save(Movement movement) {
		movementRepository.save(movement);
	}

	/* (non-Javadoc)
	 * @see com.walmart.logistics.pathfinder.services.PathFinderServices#savePoints(java.util.List)
	 */
	@Transactional
	public void savePoints(List<Point> listPoints) {
		pointRepository.save(listPoints);	
	}

	/* (non-Javadoc)
	 * @see com.walmart.logistics.pathfinder.services.PathFinderServices#saveMap(com.walmart.logistics.pathfinder.model.Map)
	 */

	@Transactional
	public void saveMap(MapVO mapVO) {
		
		Map mapPersistence = new Map(mapVO.getName());
		mapRepository.save(mapPersistence);
		
		for (Movement movement : mapVO.getMovements()) {
			movement.setMap(mapPersistence);
			movementRepository.save(movement);
		}
		
	}
	
	
	public MapVO findMapByName(String nameMap) {
		
		Map map = mapRepository.findByName(nameMap);
		MapVO mapVO = new MapVO(map.getName(), movementRepository.findByMap(map));
		
		return mapVO;
	}


}
