/**
 * 
 */
package com.walmart.logistics.pathfinder.common;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import com.walmart.logistics.pathfinder.model.Point;


/**
 * @author ronan.sayao
 *
 */
public class PointTest {
	
	@Test
	public void testHashCode() {
		
		
		Set<Point> points = new HashSet<Point>();
		
		System.out.println(new Point("Limeira").hashCode());
		System.out.println(new Point("Limeira").hashCode());
		System.out.println(new Point("Limeira").hashCode());
		System.out.println(new Point("Limeira").hashCode());
		System.out.println(new Point("Limeira").hashCode());
		System.out.println(new Point("Limeira").hashCode());
		
		points.add(new Point("Limeira"));
		points.add(new Point("Limeira"));
		Assert.assertEquals(1, points.size());
	}

}
