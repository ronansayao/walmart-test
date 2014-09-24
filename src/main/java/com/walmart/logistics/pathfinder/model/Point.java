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
@Table(name = "points")
@NamedQuery(name = "Point.findAll", query = "SELECT p FROM Point p")
public class Point {
	
	@Id
	@SequenceGenerator(name = "PointSequence", sequenceName = "SEQ_POINT_PK", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PointSequence")
	private Long id;
	
	private String name;
	
	public Point() {
		
	}
	
	public Point(String name) {
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
	
	@Override
	public String toString() {
		return this.name;
	}
	/**
	 * 
	 */

	@Override
	public boolean equals( Object obj )	{
		
		if (!(obj instanceof Point))
	            return false;
        if (this.name == ((Point) obj).getName())
	            return true;
		return false;	
	}
	
	@Override
	public int hashCode(){
		return this.name.hashCode();
	}

}
