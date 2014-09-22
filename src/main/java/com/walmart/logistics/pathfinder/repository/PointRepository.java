/**
 * 
 */
package com.walmart.logistics.pathfinder.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.walmart.logistics.pathfinder.model.Point;

/**
 * @author ronan.sayao
 *
 */
@Repository
public interface PointRepository  extends CrudRepository<Point,Long> {
	
	Point findByName(String name);

}
