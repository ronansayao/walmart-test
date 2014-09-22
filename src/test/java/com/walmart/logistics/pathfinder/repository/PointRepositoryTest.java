/**
 * 
 */
package com.walmart.logistics.pathfinder.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.walmart.logistics.pathfinder.model.Point;

/**
 * @author ronan.sayao
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/META-INF/test-spring.xml")

public class PointRepositoryTest {
	
	@Resource
	private PointRepository pointRepository;
	
	@Autowired
	private AbstractApplicationContext context;
	
	List<Point> pointList = new ArrayList<Point>();
	
	@Test
	public void save() {
		
		pointList.add(new Point("Limeira"));
		pointList.add(new Point("Campinas"));
		pointList.add(new Point("São Paulo"));
		pointList.add(new Point("Jundiai"));
		pointList.add(new Point("Artur Nogueira"));
		pointList.add(new Point("Holambra"));
		
		pointRepository.save(pointList);
		
		Point pointVerify = pointRepository.findByName("Limeira");
		Assert.assertNotNull("Verify returned object.", pointVerify);
		
		pointRepository.delete(pointList);
	
		
	}


}
