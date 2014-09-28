/**
 * 
 */
package com.walmart.logistics.pathfinder.algorithm;

import java.util.ArrayList;
import java.util.Iterator;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.Dijkstra.Element;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.walmart.logistics.pathfinder.common.Constants;
import com.walmart.logistics.pathfinder.exception.PathNotFoundException;
import com.walmart.logistics.pathfinder.strategy.AlgorithmStrategy;
import com.walmart.logistics.pathfinder.vo.PathEntriesVO;
import com.walmart.logistics.pathfinder.vo.RouteVO;

/**
 * @author ronan.sayao
 *
 */
public class DijkstraAlgorithmImpl implements AlgorithmStrategy {
	
	private static final Logger logger = LoggerFactory.getLogger(DijkstraAlgorithmImpl.class);

	/* (non-Javadoc)
	 * @see com.walmart.logistics.pathfinder.strategy.AlgorithmStrategy#pathFinder(org.graphstream.graph.Graph, com.walmart.logistics.pathfinder.vo.PathEntriesVO)
	 */
	@Override
	public RouteVO pathFinder(Graph graph, PathEntriesVO pathEntriesVO) throws PathNotFoundException {
		
		RouteVO routeVO = new RouteVO();
		
		Long startMilisDijkstra = System.currentTimeMillis();
		logger.info("pathFinder() - Method has started: "+startMilisDijkstra);
	
		Dijkstra dijkstra = new Dijkstra(Element.EDGE, "", Constants.FIELD_WEIGHT);
		dijkstra.setSource(graph.getNode(pathEntriesVO.getOrigin()));
        dijkstra.init(graph);
        dijkstra.compute();
        Node nodeDestination = graph.getNode(pathEntriesVO.getDestination());
        
        if (nodeDestination == null) {
        	throw new PathNotFoundException(pathEntriesVO.getOrigin(), pathEntriesVO.getDestination());
        }
        
        Path path = dijkstra.getPath(nodeDestination);
        
        if (path != null) {
	        Integer shortestDistance = 0;
	        
	        for (Iterator<Edge> iterator = path.getEdgeIterator(); iterator.hasNext();) {
				Edge edgeElement = (Edge) iterator.next();
				shortestDistance = shortestDistance + (Integer) edgeElement.getAttribute(Constants.FIELD_WEIGHT);
				
			}
	        
	        routeVO.setPath(new ArrayList<String>());
	        
	        for (Node node : path.getNodePath()) {
	        	String nodeName = node.getId();
	        	routeVO.getPath().add(nodeName);
	        }
	        
	        routeVO.setTotalCost((shortestDistance/pathEntriesVO.getAutonomy())*pathEntriesVO.getGasprice());
	
	        logger.info("Path Dijkstra: "+path.getNodePath().toString());
	        logger.info("Distance Dijkstra: "+shortestDistance);
			logger.info("pathFinder() - Method has spent: "+(System.currentTimeMillis()-startMilisDijkstra)+" ms");
        } else {
        	throw new PathNotFoundException(pathEntriesVO.getOrigin(), pathEntriesVO.getDestination());
        }		
		return routeVO;
	}

}
