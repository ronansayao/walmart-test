/**
 * 
 */
package com.walmart.logistics.pathfinder.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author ronan.sayao
 *
 */
@Entity
@Table(name = "maps")
@NamedQuery(name = "Map.findAll", query = "SELECT m FROM Map m")
public class Map {
	
	@Id
	@SequenceGenerator(name = "MapSequence", sequenceName = "SEQ_MAP_PK", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MapSequence")
	private Long id;
	private String name;

	public Map() {
		
	}
	
	public Map(String name) {
		this.name = name;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
