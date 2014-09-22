/**
 * 
 */
package com.walmart.logistics.pathfinder.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.walmart.logistics.pathfinder.model.Graph;
import com.walmart.logistics.pathfinder.model.Movement;
import com.walmart.logistics.pathfinder.model.Point;

/**
 * @author ronan.sayao
 *
 */
public class DijkstraAlgorithm {
	
	
	private final List<Point> points;
	private final List<Movement> movements;
	private Set<String> settledNodes;
	private Set<String> unSettledNodes;
	private Map<String, String> predecessors;
	private Map<String, Integer> distance;

	public DijkstraAlgorithm(Graph graph) {
	    // create a copy of the array so that we can operate on this array
	    this.points = new ArrayList<Point>(graph.getPoints());
	    this.movements = new ArrayList<Movement>(graph.getMovements());
	}
	
	public void execute(String source) {
		settledNodes = new HashSet<String>();
		unSettledNodes = new HashSet<String>();
		distance = new HashMap<String, Integer>();
		predecessors = new HashMap<String, String>();
		distance.put(source, 0);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			String node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	  private void findMinimalDistances(String node) {
	    List<String> adjacentNodes = getNeighbors(node);
	    for (String target : adjacentNodes) {
	      if (getShortestDistance(target) > getShortestDistance(node)
	          + getDistance(node, target)) {
	        distance.put(target, getShortestDistance(node)
	            + getDistance(node, target));
	        predecessors.put(target, node);
	        unSettledNodes.add(target);
	      }
	    }

	  }

	  private int getDistance(String node, String target) {
	    for (Movement movement : movements) {
	      if (movement.getSource().equals(node)
	          && movement.getDestination().equals(target)) {
	        return movement.getDistance();
	      }
	    }
	    throw new RuntimeException("Should not happen");
	  }

	  private List<String> getNeighbors(String node) {
	    List<String> neighbors = new ArrayList<String>();
	    for (Movement movement : movements) {
	      if (movement.getSource().equals(node)
	          && !isSettled(movement.getDestination())) {
	        neighbors.add(movement.getDestination());
	      }
	    }
	    return neighbors;
	  }

	  private String getMinimum(Set<String> tracks) {
		String minimum = null;
	    for (String track : tracks) {
	      if (minimum == null) {
	        minimum = track;
	      } else {
	        if (getShortestDistance(track) < getShortestDistance(minimum)) {
	          minimum = track;
	        }
	      }
	    }
	    return minimum;
	  }

	  private boolean isSettled(String track) {
	    return settledNodes.contains(track);
	  }

	  public int getShortestDistance(String destination) {
	    Integer d = distance.get(destination);
	    if (d == null) {
	      return Integer.MAX_VALUE;
	    } else {
	      return d;
	    }
	  }

	  /*
	   * This method returns the path from the source to the selected target and
	   * NULL if no path exists
	   */
	  public LinkedList<String> getPath(String target) {
	    LinkedList<String> path = new LinkedList<String>();
	    String step = target;
	    // check if a path exists
	    if (predecessors.get(step) == null) {
	      return null;
	    }
	    path.add(step);
	    while (predecessors.get(step) != null) {
	      step = predecessors.get(step);
	      path.add(step);
	    }
	    // Put it into the correct order
	    Collections.reverse(path);
	    return path;
	  }

}
