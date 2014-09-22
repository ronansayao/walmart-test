/**
 * 
 */
package com.walmart.logistics.pathfinder.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.walmart.logistics.pathfinder.model.Map;
import com.walmart.logistics.pathfinder.model.Movement;

/**
 * @author ronan.sayao
 *
 */
@Repository
public interface MovementRepository extends CrudRepository<Movement,Long> {
	
	Movement findByName(String name);
	List<Movement> findByMap(Map map);

}
