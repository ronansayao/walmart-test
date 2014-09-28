/**
 * 
 */
package com.walmart.logistics.pathfinder.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.transaction.Transactional;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.logistics.pathfinder.algorithm.AStarAlgorithmImpl;
import com.walmart.logistics.pathfinder.algorithm.DijkstraAlgorithmImpl;
import com.walmart.logistics.pathfinder.common.Constants;
import com.walmart.logistics.pathfinder.exception.PathNotFoundException;
import com.walmart.logistics.pathfinder.model.Map;
import com.walmart.logistics.pathfinder.model.Movement;
import com.walmart.logistics.pathfinder.model.Point;
import com.walmart.logistics.pathfinder.repository.MapRepository;
import com.walmart.logistics.pathfinder.repository.MovementRepository;
import com.walmart.logistics.pathfinder.repository.PointRepository;
import com.walmart.logistics.pathfinder.strategy.AlgorithmStrategyImpl;
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
	
	/**
	 * Mathod to save movement.
	 */
	@Transactional
	public void save(Movement movement) {
		movementRepository.save(movement);
	}


	/**
	 * Method responsible to save Map.
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
	
	/**
	 * 
	 */
	public MapVO findMapByName(String nameMap) {
		
		Map map = mapRepository.findByName(nameMap);
		MapVO mapVO = new MapVO(map.getName(), movementRepository.findByMap(map));
		
		return mapVO;
	}

	/* (non-Javadoc)
	 * @see com.walmart.logistics.pathfinder.services.PathFinderServices#getPathAndCosts(com.walmart.logistics.pathfinder.vo.PathEntriesVO)
	 */
	@Override
	public RouteVO getPathAndCosts(PathEntriesVO pathEntriesVO) throws PathNotFoundException {
		
		MapVO mapVO = findMapByName(pathEntriesVO.getMapName());
		RouteVO routeVO = new RouteVO();
		points = (List<Point>) pointRepository.findAll();
		movements = mapVO.getMovements();
		
		validateEntries(pathEntriesVO);
		
		Properties prop = new Properties();
		
		Long startMilis = System.currentTimeMillis();
		logger.debug("Method has started: "+startMilis);
		
		prop = getProperties(prop);
		
		Graph graph = populateGraph(pathEntriesVO.getMapName());
		routeVO = pathFinder(prop, graph, pathEntriesVO);
		
		return routeVO;
	}

	/**
	 * Method that choose the properly algorithm (AStar is default).
	 * @param prop
	 * @param graph
	 * @param pathEntriesVO
	 * @throws PathNotFoundException 
	 */
	private RouteVO pathFinder(Properties prop, Graph graph, PathEntriesVO pathEntriesVO) throws PathNotFoundException {
		
		AlgorithmStrategyImpl algorithmStrategy = new AlgorithmStrategyImpl(new AStarAlgorithmImpl());
		String defaultAlgorithm = prop.getProperty(Constants.DEFAULT_ALGORITHM);
		
		if (defaultAlgorithm.equalsIgnoreCase(Constants.ASTAR_ALGORITHM)) {
			algorithmStrategy = new AlgorithmStrategyImpl(new AStarAlgorithmImpl());
		} else if (defaultAlgorithm.equalsIgnoreCase(Constants.DIJKSTRA_ALGORITHM)) {	
			algorithmStrategy = new AlgorithmStrategyImpl(new DijkstraAlgorithmImpl());
		}
		RouteVO routeVO = algorithmStrategy.executeAlgorithmStrategy(graph, pathEntriesVO);
		logger.debug("Default algorithm: "+defaultAlgorithm);
		
		return routeVO;
	}

	/**
	 * Method to get properties from file config.properties.
	 * @param prop Get all the properties from the file.
	 */
	private Properties getProperties(Properties prop) {
		InputStream input;
		try {
			//URL url =getClass().getResourceAsStream(Constants.PROPERTIES_FILE);
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			input = classLoader.getResourceAsStream(Constants.PROPERTIES_FILE);
			prop.load(input);
		} catch (FileNotFoundException e) {
			logger.error("File not found (config.propeties). "+e.getStackTrace().toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Impossible to access configuration file (config.propeties). "+e.getStackTrace().toString());
		}
		return prop;
	}
	
	/**
	 * Method responsible to populate the Graph object.
	 * @param nameMap name of the map
	 * @return Graph instance to find a path.
	 */
	private Graph populateGraph(String nameMap) {
		
		Graph graph = new SingleGraph(nameMap);
		
		for (Point point : points) {
			graph.addNode(point.getName());
		}
		graph.setAutoCreate(true);
		for (Movement movement : movements) { 
			graph.addEdge(movement.getSource()+movement.getDestination(), movement.getSource(), movement.getDestination()).setAttribute(Constants.FIELD_WEIGHT, movement.getDistance());
		}
					
		return graph;
		
	}
	/**
	 * 
	 * @param pathEntriesVO
	 */
	private void validateEntries(PathEntriesVO pathEntriesVO) {
		
		if(pathEntriesVO.getOrigin().equalsIgnoreCase(pathEntriesVO.getDestination())) {
			
		}
		
	}


}
