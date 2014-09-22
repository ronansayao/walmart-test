/**
 * 
 */
package com.walmart.logistics.pathfinder.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.walmart.logistics.pathfinder.model.Movement;
import com.walmart.logistics.pathfinder.rest.WalmartRestURIConstants;

/**
 * @author ronan.sayao
 *
 */
public class PathFinderControllerTest {
	
	public static final String SERVER_URI = "http://localhost:8088";
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Test
	@Ignore
	public void testCreateMovement() {
        RestTemplate restTemplate = new RestTemplate();
        Movement movement = getMovement("Movement_0", "A", "B", 85);
        Movement response = restTemplate.postForObject(SERVER_URI+WalmartRestURIConstants.CREATE_MOVEMENTS, movement, Movement.class);
        printMovementData(response);
    }
	
	@Test
	public void testGetMap() {
		List<LinkedHashMap> map = restTemplate.getForObject(SERVER_URI+WalmartRestURIConstants.GET_MAP, List.class);
		System.out.println("Size: "+map.size());
		for(LinkedHashMap mapVO : map){
		    System.out.println("ID="+mapVO.get("id")+",Name="+mapVO.get("name"));
		}
	}
	
	
	/**
	 * 
	 * @param movementId
	 * @param sourceLocNo
	 * @param destLocNo
	 * @param duration
	 */

	private Movement getMovement(String movementName, String sourceLocNo, String destLocNo,
		int duration) {
		Movement movement = new Movement(movementName, sourceLocNo, destLocNo, duration);
		return movement;
	}
	
	 public void printMovementData(Movement movement){
        System.out.println("Id = "+movement.getId()+" ,Name="+movement.getName()+" ,Source="+movement.getSource()
        		+" ,Destination="+movement.getDestination()+" ,Distance="+movement.getDistance());
    }

}