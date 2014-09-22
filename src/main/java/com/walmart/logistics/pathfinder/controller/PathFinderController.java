/**
 * 
 */
package com.walmart.logistics.pathfinder.controller;

import java.util.Date;
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

import com.walmart.logistics.pathfinder.model.Movement;
import com.walmart.logistics.pathfinder.rest.WalmartRestURIConstants;
import com.walmart.logistics.pathfinder.services.PathFinderServices;
import com.walmart.logistics.pathfinder.vo.MapVO;

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
    
    @RequestMapping(value = WalmartRestURIConstants.CREATE_MOVEMENTS, method = RequestMethod.POST)
    public @ResponseBody Movement createMovement(@RequestBody Movement movement) {
        logger.info("Start createMovement.");
        pathFinderServices.save(movement);
        return movement;
    }
    
    @RequestMapping(value = WalmartRestURIConstants.CREATE_MAP, method = RequestMethod.POST)
    public @ResponseBody MapVO createMap(@RequestBody MapVO mapVO) {
        logger.info("Start createMap.");
        pathFinderServices.saveMap(mapVO);
        return mapVO;
    }
    
    @RequestMapping(value = WalmartRestURIConstants.GET_MAP, method = RequestMethod.GET)
    public @ResponseBody MapVO getMapByName(@PathVariable("name") String nameMap) {
        logger.info("Start getMap"); 
        MapVO map = pathFinderServices.findMapByName(nameMap);
        return map;
    }

}
