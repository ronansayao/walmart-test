/**
 * 
 */
package com.walmart.logistics.pathfinder.repository;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.walmart.logistics.pathfinder.model.Movement;

/**
 * @author ronan.sayao
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/META-INF/test-spring.xml")


public class MovementRepositoryTest {
	
	private static final Logger logger = LoggerFactory.getLogger(MovementRepositoryTest.class);
	
	@Resource
	private MovementRepository movementRepository;
	
	@Autowired
	private AbstractApplicationContext context;
	
	private Movement movement;
	
	@Test
	public void saveMovement() {
		
		movement = new Movement("Movement_0", "Limeira", "São Paulo", 85);
		try
		{
			movementRepository.save(movement);
		} catch (Exception exception) {	
			logger.error(exception.getStackTrace().toString());	
		} finally {
			Movement movementVerify = movementRepository.findByName("Movement_0");
			Assert.assertNull("Verify returned object.", movementVerify);	
		}		
		
	}

}
