/**
 * 
 */
package com.walmart.logistics.pathfinder.repository;

import org.springframework.data.repository.CrudRepository;

import com.walmart.logistics.pathfinder.model.Map;

/**
 * @author ronan.sayao
 *
 */
public interface MapRepository extends CrudRepository<Map,Long> {	
	Map findByName(String name);
}
