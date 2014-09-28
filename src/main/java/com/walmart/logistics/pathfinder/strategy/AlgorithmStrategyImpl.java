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
public class AlgorithmStrategyImpl {
	
	private AlgorithmStrategy algorithmStrategy;
	
	public AlgorithmStrategyImpl(AlgorithmStrategy strategy) {
        this.algorithmStrategy = strategy;
    }
    public RouteVO executeAlgorithmStrategy(Graph graph, PathEntriesVO pathEntriesVO) throws PathNotFoundException {
        return this.algorithmStrategy.pathFinder(graph, pathEntriesVO);
    }

}
