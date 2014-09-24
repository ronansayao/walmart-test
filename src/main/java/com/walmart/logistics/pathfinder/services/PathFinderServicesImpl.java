/**
 * 
 */
package com.walmart.logistics.pathfinder.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.logistics.pathfinder.algorithm.DijkstraAlgorithm;
import com.walmart.logistics.pathfinder.algorithm.DijkstraAlgorithmTest;
import com.walmart.logistics.pathfinder.model.Graph;
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
	
	private static final Logger logger = LoggerFactory.getLogger(PathFinderServicesImpl.class);
	
	@Autowired
	private MovementRepository movementRepository;
	
	@Autowired
	private PointRepository pointRepository;
	
	@Autowired
	private MapRepository mapRepository;
	
	private List<Point> points;
	private List<Movement> movements;
	
	@Transactional
	public void save(Movement movement) {
		movementRepository.save(movement);
	}

	/* (non-Javadoc)
	 * @see com.walmart.logistics.pathfinder.services.PathFinderServices#saveMap(com.walmart.logistics.pathfinder.model.Map)
	 */

	@Transactional
	public void saveMap(MapVO mapVO) {
		
		Set<Point> points = new HashSet<Point>();
		
		Map mapPersistence = new Map(mapVO.getName());
		mapRepository.save(mapPersistence);
		
		for (Movement movement : mapVO.getMovements()) {
			points.add(new Point(movement.getSource()));
			points.add(new Point(movement.getDestination()));
			movement.setMap(mapPersistence);
			movementRepository.save(movement);
		}
		
		pointRepository.save(points);		
		
	}
	
	
	public MapVO findMapByName(String nameMap) {
		
		Map map = mapRepository.findByName(nameMap);
		MapVO mapVO = new MapVO(map.getName(), movementRepository.findByMap(map), pointRepository.findAll());
		
		return mapVO;
	}

	/* (non-Javadoc)
	 * @see com.walmart.logistics.pathfinder.services.PathFinderServices#getPathAndCosts(com.walmart.logistics.pathfinder.vo.PathEntriesVO)
	 */
	@Override
	public RouteVO getPathAndCosts(PathEntriesVO pathEntriesVO) {
		
		MapVO mapVO = findMapByName(pathEntriesVO.getMapName());
		RouteVO routeVO = new RouteVO();
		points = (List<Point>) mapVO.getPoints();
		movements = mapVO.getMovements();
		
		
		Long startMilis = System.currentTimeMillis();
		logger.debug("Method has started: "+startMilis);

		Graph graph = new Graph(points, movements);
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		dijkstra.execute(pathEntriesVO.getOrigin());
		
		routeVO.setPath(dijkstra.getPath(pathEntriesVO.getDestination()));
		
		//routeVO.setTotalCost(totalCost);
		LinkedList<String> path = dijkstra.getPath(pathEntriesVO.getDestination());
		int distance = dijkstra.getShortestDistance(pathEntriesVO.getDestination());
		assertNotNull(path);
		assertTrue(path.size() > 0);
		
		for (String track : path) {
		  System.out.println(track);
		}
		System.out.println("Distance: "+distance);
		logger.debug("Method has finished: "+(System.currentTimeMillis()-startMilis)+" ms");
		
		// TODO Auto-generated method stub
		return routeVO;
	}


}
