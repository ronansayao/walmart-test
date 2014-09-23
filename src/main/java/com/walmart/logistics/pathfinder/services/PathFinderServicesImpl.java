/**
 * 
 */
package com.walmart.logistics.pathfinder.services;

import java.util.HashSet;
import java.util.Set;

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
import com.walmart.logistics.pathfinder.vo.PathEntriesVO;
import com.walmart.logistics.pathfinder.vo.RouteVO;

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
	 * @see com.walmart.logistics.pathfinder.services.PathFinderServices#saveMap(com.walmart.logistics.pathfinder.model.Map)
	 */

	@Transactional
	public void saveMap(MapVO mapVO) {
		
		Set<String> points = new HashSet<String>();
		
		Map mapPersistence = new Map(mapVO.getName());
		mapRepository.save(mapPersistence);
		
		for (Movement movement : mapVO.getMovements()) {
			points.add(movement.getSource());
			points.add(movement.getDestination());
			movement.setMap(mapPersistence);
			movementRepository.save(movement);
		}
		
		for (String stringPoint : points) {
			Point pointExists = pointRepository.findByName(stringPoint);
			if (pointExists != null) {
				continue;
			} else {
				Point point = new Point(stringPoint);
				pointRepository.save(point);
			}
			
				
		}
		
		
	}
	
	
	public MapVO findMapByName(String nameMap) {
		
		Map map = mapRepository.findByName(nameMap);
		MapVO mapVO = new MapVO(map.getName(), movementRepository.findByMap(map));
		
		return mapVO;
	}

	/* (non-Javadoc)
	 * @see com.walmart.logistics.pathfinder.services.PathFinderServices#getPathAndCosts(com.walmart.logistics.pathfinder.vo.PathEntriesVO)
	 */
	@Override
	public RouteVO getPathAndCosts(PathEntriesVO pathEntriesVO) {
		
		// TODO Auto-generated method stub
		return null;
	}


}
