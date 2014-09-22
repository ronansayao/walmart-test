/**
 * 
 */
package com.walmart.logistics.pathfinder.services;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.walmart.logistics.pathfinder.common.CampinasRegionMap;
import com.walmart.logistics.pathfinder.vo.MapVO;

/**
 * @author ronan.sayao
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/META-INF/test-spring.xml")

public class PathFinderServiceTest {
	
	@Autowired
	PathFinderServices pathFinderServices;
	
	CampinasRegionMap mapCampinas = new CampinasRegionMap();
	
	@Test
	public void testCreateMap() {
		MapVO map = new MapVO(mapCampinas.getMap().getName(), mapCampinas.getMovements());
		pathFinderServices.saveMap(map);
    }
	
	@Test
	public void testFindMap() {		
		MapVO mapVerify = pathFinderServices.findMapByName(mapCampinas.getMap().getName());
		Assert.assertNotNull("Verify returned object.", mapVerify);
    }
	

}
