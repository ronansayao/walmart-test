/**
 * 
 */
package com.walmart.logistics.pathfinder.repository;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.walmart.logistics.pathfinder.model.Map;

/**
 * @author ronan.sayao
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/META-INF/test-spring.xml")

public class MapRepositoryTest {
	@Resource
	private MapRepository mapRepository;
	
	@Autowired
	private AbstractApplicationContext context;
	
	Map map = new Map();
	
	@Test
	public void save() {
		
		map = new Map("Limeira");
			
		mapRepository.save(map);
		
		Map mapVerify = mapRepository.findByName("Limeira");
		Assert.assertNotNull("Verify returned object.", mapVerify);
		
		mapRepository.delete(map);	
	}

}
