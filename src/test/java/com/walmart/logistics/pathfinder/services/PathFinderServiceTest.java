/**
 * 
 */
package com.walmart.logistics.pathfinder.services;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.walmart.logistics.pathfinder.common.CampinasRegionMap;
import com.walmart.logistics.pathfinder.exception.PathNotFoundException;
import com.walmart.logistics.pathfinder.vo.MapVO;
import com.walmart.logistics.pathfinder.vo.PathEntriesVO;

/**
 * @author ronan.sayao
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/META-INF/test-spring.xml")



public class PathFinderServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(PathFinderServiceTest.class);
	
	@Autowired
	PathFinderServices pathFinderServices;
	
	CampinasRegionMap mapCampinas = new CampinasRegionMap();

	public void testCreateMap() {
		MapVO map = new MapVO(mapCampinas.getMap().getName(), mapCampinas.getMovements());
		pathFinderServices.saveMap(map);
    }
	
	public void testFindMap() {		
		MapVO mapVerify = pathFinderServices.findMapByName(mapCampinas.getMap().getName());
		Assert.assertNotNull("Verify returned object.", mapVerify);
    }
	
	public void testGetPathAndCosts() {
		
		PathEntriesVO pathEntriesVO = new PathEntriesVO();
		pathEntriesVO.setMapName(mapCampinas.getMap().getName());
		pathEntriesVO.setOrigin("Limeira");
		pathEntriesVO.setDestination("Campinas");
		pathEntriesVO.setAutonomy(10.0);
		pathEntriesVO.setGasprice(2.5);
		
		try {
			pathFinderServices.getPathAndCosts(pathEntriesVO);
		} catch (PathNotFoundException e) {		
			logger.error(e.getMessage());
		}	
		
		
	}
	
	@Test
	public void executeTests() {
		
		testCreateMap();
		testFindMap();
		testGetPathAndCosts();
		
	}
	
	

}
