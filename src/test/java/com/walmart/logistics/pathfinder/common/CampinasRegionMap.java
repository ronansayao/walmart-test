/**
 * 
 */
package com.walmart.logistics.pathfinder.common;

import java.util.ArrayList;
import java.util.List;

import com.walmart.logistics.pathfinder.model.Map;
import com.walmart.logistics.pathfinder.model.Movement;
import com.walmart.logistics.pathfinder.model.Point;

/**
 * @author ronan.sayao
 * 
 *
 */
public class CampinasRegionMap {
	
	List<Point> points;
	List<Movement> movements;
	Map map;
	
	public CampinasRegionMap() {
		createMap();		
		createPoints();
		createMovements();
	}
	
	public void createPoints() {
		points = new ArrayList<Point>();
		points.add(new Point("Limeira"));//0-
		points.add(new Point("Piracicaba"));//1
		points.add(new Point("RioClaro"));//2
		points.add(new Point("Cordeiropolis"));//3
		points.add(new Point("ArturNogueira"));//4
		points.add(new Point("Americana"));//5
		points.add(new Point("Holambra"));//6
		points.add(new Point("Jaguariuna"));//7
		points.add(new Point("Cosmopolis"));//8
		points.add(new Point("Paulinia"));//9
		points.add(new Point("Campinas"));//10
		points.add(new Point("SaoPaulo"));//11	
	}
	
	public void createMovements() {
		movements = new ArrayList<Movement>();
		
		movements.add(new Movement("Piracicaba_Limeira", points.get(1).getName(), points.get(0).getName(), 38, this.map));
		movements.add(new Movement("Piracicaba_RioClaro", points.get(1).getName(), points.get(2).getName(), 40, this.map));
		movements.add(new Movement("Piracicaba_Campinas", points.get(1).getName(), points.get(10).getName(), 72, this.map));
		movements.add(new Movement("Piracicaba_SaoPaulo", points.get(1).getName(), points.get(11).getName(), 159, this.map));
		
		movements.add(new Movement("RioClaro_Holambra", points.get(2).getName(), points.get(6).getName(), 69, this.map));
		movements.add(new Movement("RioClaro_Cordeiropolis", points.get(2).getName(), points.get(3).getName(), 18, this.map));
		
		movements.add(new Movement("Limeira_ArturNogueira", points.get(0).getName(), points.get(4).getName(), 34, this.map));
		movements.add(new Movement("Limeira_Cosmopolis", points.get(0).getName(), points.get(8).getName(), 28, this.map));
		movements.add(new Movement("Limeira_Americana", points.get(0).getName(), points.get(5).getName(), 27, this.map));
		
		movements.add(new Movement("ArturNogueira_Holambra", points.get(4).getName(), points.get(6).getName(), 17, this.map));
		movements.add(new Movement("ArturNogueira_Cosmopolis", points.get(4).getName(), points.get(8).getName(), 11, this.map));
		
		movements.add(new Movement("Holambra_Jaguariuna", points.get(6).getName(), points.get(7).getName(), 13, this.map));
		
		movements.add(new Movement("Cosmopolis_Paulinia", points.get(8).getName(), points.get(9).getName(), 18, this.map));
		
		movements.add(new Movement("Americana_Campinas", points.get(5).getName(), points.get(10).getName(), 38, this.map));
		
		movements.add(new Movement("Jaguariuna_Campinas", points.get(7).getName(), points.get(10).getName(), 35, this.map));
		movements.add(new Movement("Jaguariuna_SaoPaulo", points.get(7).getName(), points.get(11).getName(), 125, this.map));
		
		movements.add(new Movement("Paulinia_Campinas", points.get(9).getName(), points.get(10).getName(), 23, this.map));
		
		movements.add(new Movement("Campinas_SaoPaulo", points.get(10).getName(), points.get(11).getName(), 95, this.map));
		
		
	}
	
	public void createMap() {
		map = new Map("EstadoSaoPaulo");
	}

	/**
	 * @return the points
	 */
	public List<Point> getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(List<Point> points) {
		this.points = points;
	}

	/**
	 * @return the movements
	 */
	public List<Movement> getMovements() {
		return movements;
	}

	/**
	 * @param movements the movements to set
	 */
	public void setMovements(List<Movement> movements) {
		this.movements = movements;
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

}
