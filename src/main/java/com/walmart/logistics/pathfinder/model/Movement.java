package com.walmart.logistics.pathfinder.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "movements")
@NamedQuery(name = "Movement.findAll", query = "SELECT m FROM Movement m")

public class Movement implements Serializable {

	/**
	 * The Constant serialVersionUID. 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The id.
	 */
	@Id
	@SequenceGenerator(name = "MovementSequence", sequenceName = "SEQ_MOVEMENT_PK", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MovementSequence")
	private Long id;
	
	private String name;		
	private String source;
	private String destination;
	private int distance;
	
	@ManyToOne
	@JoinColumn(name="mapid", nullable=false)
	private Map map;
	
	public Movement() {
		
	}
	
	public Movement(String name, String source, String destination, int distance) {
		this.name = name;
	    this.source = source;
	    this.destination = destination;
	    this.distance = distance;
	}
	
	public Movement(String name, String source, String destination, int distance, Map map) {
		this.name = name;
	    this.source = source;
	    this.destination = destination;
	    this.distance = distance;
	    this.map = map;
	}
	
	
	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
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
	 * @return the source
	 */
	public String getSource() {
		return source;
	}


	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}


	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}


	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
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


	/**
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return source + " " + destination;
	}

}
