/**
 * 
 */
package com.walmart.logistics.pathfinder.strategy;

import org.graphstream.graph.Graph;

import com.walmart.logistics.pathfinder.exception.PathNotFoundException;
import com.walmart.logistics.pathfinder.vo.PathEntriesVO;
import com.walmart.logistics.pathfinder.vo.RouteVO;

/**
 * @author ronan.sayao
 *
 */
public interface AlgorithmStrategy {
	
	RouteVO pathFinder(Graph graph, PathEntriesVO pathEntriesVO) throws PathNotFoundException;

}
