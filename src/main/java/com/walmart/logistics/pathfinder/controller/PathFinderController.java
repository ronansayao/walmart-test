/**
 * 
 */
package com.walmart.logistics.pathfinder.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.walmart.logistics.pathfinder.exception.PathNotFoundException;
import com.walmart.logistics.pathfinder.model.Movement;
import com.walmart.logistics.pathfinder.rest.WalmartRestURIConstants;
import com.walmart.logistics.pathfinder.services.PathFinderServices;
import com.walmart.logistics.pathfinder.vo.MapVO;
import com.walmart.logistics.pathfinder.vo.PathEntriesVO;
import com.walmart.logistics.pathfinder.vo.RouteVO;

/**
 * @author ronan.sayao
 *
 */

@Controller
public class PathFinderController {
	
	@Autowired
	PathFinderServices pathFinderServices;
	
	private static final Logger logger = LoggerFactory.getLogger(PathFinderController.class);
	
	//Map to store movements, ideally we should use database
    Map<Long, Movement> movementData = new HashMap<Long, Movement>();
    
    /**
     * 
     * @param movement
     * @return
     */
    @RequestMapping(value = WalmartRestURIConstants.CREATE_MOVEMENTS, method = RequestMethod.POST)
    public @ResponseBody Movement createMovement(@RequestBody Movement movement) {
        logger.info("Start createMovement.");
        pathFinderServices.save(movement);
        return movement;
    }
    
    /**
     * 
     * @param mapVO
     * @return
     */
    @RequestMapping(value = WalmartRestURIConstants.CREATE_MAP, method = RequestMethod.POST)
    public @ResponseBody MapVO createMap(@RequestBody MapVO mapVO) {
        logger.info("Start createMap.");
        pathFinderServices.saveMap(mapVO);
        logger.info("Map has created.");
        return mapVO;
    }
    
    /**
     * Method that get map by name.
     * @param nameMap name of the map
     * @return object Map.
     */
    @RequestMapping(value = WalmartRestURIConstants.GET_MAP, method = RequestMethod.GET)
    public @ResponseBody MapVO getMapByName(@PathVariable("name") String nameMap) {
        logger.info("Start getMap"); 
        MapVO map = pathFinderServices.findMapByName(nameMap);
        return map;
    }
    
    /**
     * 
     * @param pathEntriesVO
     * @return
     */
    @RequestMapping(value = WalmartRestURIConstants.GET_PLAN_COST_ROUTE, method = RequestMethod.POST)
    public @ResponseBody RouteVO planRoute(@RequestBody PathEntriesVO pathEntriesVO) {
        logger.info("Start planRoute");
        RouteVO routeVO = new RouteVO();
		try {
			routeVO = pathFinderServices.getPathAndCosts(pathEntriesVO);
		} catch (PathNotFoundException e) {
			routeVO.setErrorMessage(e.getMessage());
			return routeVO;
		}
        return routeVO;
    }
    
    /**
     * Method dummy just to test communication.
     * @return String dummy.
     */
    @RequestMapping(value = WalmartRestURIConstants.DUMMY_STRING, method = RequestMethod.GET)
    public @ResponseBody String getMapByName() {
        logger.info("Start dummy method"); 
        String testString = "Test of call webservices";
        return testString;
    }

}
