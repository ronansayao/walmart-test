/**
 * 
 */
package com.walmart.logistics.pathfinder.controller;

import java.io.File;

import junit.framework.Assert;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.walmart.logistics.pathfinder.common.CampinasRegionMap;
import com.walmart.logistics.pathfinder.rest.WalmartRestURIConstants;
import com.walmart.logistics.pathfinder.vo.MapVO;
import com.walmart.logistics.pathfinder.vo.PathEntriesVO;
import com.walmart.logistics.pathfinder.vo.RouteVO;


/**
 * @author ronan.sayao
 *
 */
public class PathFinderControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(PathFinderControllerTest.class);
	
	public static final String SERVER_URI = "http://localhost:8088";
	private Server server;
	private RestTemplate restTemplate = new RestTemplate();
	private CampinasRegionMap mapCampinas = new CampinasRegionMap();
		
	/**
	 * Method that starts jetty server.
	 * @throws Exception
	 */
	@Before
	public void startServer() throws Exception {
		
		Resource jettyConfig = Resource.newSystemResource("jetty-env.xml");
		XmlConfiguration configuration = new XmlConfiguration(jettyConfig.getInputStream());
		
		File otherProject = new File("../path-finder");
	    Assert.assertTrue("Path-finder should exist", otherProject.exists());

	    // make path reference canonical (eliminate the relative path reference)
	    otherProject = otherProject.getCanonicalFile();
	    File webAppDir = new File(otherProject, "src/main/webapp");
	    Assert.assertTrue("Webapp dir should exist", webAppDir.exists());
	    File webXml = new File(webAppDir, "WEB-INF/web.xml");
	    Assert.assertTrue("web.xml should exist", webXml.exists());
		
		server = new Server(8088); 
		
        WebAppContext webAppContext = (WebAppContext)configuration.configure();
        webAppContext.setContextPath("/");
        webAppContext.setResourceBase(webAppDir.getAbsolutePath().toString());
        webAppContext.setDescriptor(webXml.getAbsolutePath());
        //If you want to start app via war file it could be possible.
        //webAppContext.setWar("./target/MovementPlanner.war");
        webAppContext.setParentLoaderPriority(true);
        
        server.setHandler(webAppContext);
        server.start();
        
        logger.info("Jetty Server has started");
    }
	/**
	 * Execute all tests in order.
	 */
	@Test
	public void executeTest () {
		MapVO mapVOCreate = testCreateMap();
		Assert.assertEquals(mapCampinas.getMap().getName(), mapVOCreate.getName());
		MapVO mapVOGet = testGetMap();
		Assert.assertEquals("EstadoSaoPaulo", mapVOGet.getName());
		RouteVO routeVO = testGetRoute();
		Assert.assertEquals("[Limeira, Americana, Campinas]", routeVO.getPath().toString());
		RouteVO routeVOEquals = testGetRouteOriginDestinationEquals();
		Assert.assertEquals("[Limeira]",routeVOEquals.getPath().toString());
		Assert.assertEquals(16.25D, routeVO.getTotalCost());
		RouteVO routeVOInvalid = testGetRouteInvalid();
		Assert.assertNotNull(routeVOInvalid.getErrorMessage());
		RouteVO routeVOInvalidNull = testGetRouteInvalidNull();
		Assert.assertNotNull(routeVOInvalidNull.getErrorMessage());
		RouteVO routeVOInvalidNumber = testGetRouteInvalidNegativeNumber();
		Assert.assertNotNull(routeVOInvalidNumber.getErrorMessage());
	}
	
	/**
	 * Test to create a regular map.
	 */
	public MapVO testCreateMap() {
		
        MapVO mapVO = new MapVO();
        mapVO.setName(mapCampinas.getMap().getName());
        mapVO.setMovements(mapCampinas.getMovements());
        MapVO responseMapVO = restTemplate.postForObject(SERVER_URI+WalmartRestURIConstants.CREATE_MAP, mapVO, MapVO.class);
        return responseMapVO;
        
    }
	
	/**
	 * Test to create a map null.
	 */
	@Test
	public void testCreateMapNull() {
		
        MapVO mapVO = new MapVO();
        MapVO responseMapVO = restTemplate.postForObject(SERVER_URI+WalmartRestURIConstants.CREATE_MAP, mapVO, MapVO.class);
        Assert.assertNotNull(responseMapVO.getErrorMessage());
        
    }
	
	/**
	 * This method get the Map that was created in the previous method.
	 */
	public MapVO testGetMap() {
         
		MapVO map = restTemplate.getForObject(SERVER_URI+"/rest/map/EstadoSaoPaulo", MapVO.class);
		return map;	
		
	}
	
	/**
	 * This method set a map of cities in Campinas region and calculates the shortest path and costs.
	 */
	public RouteVO testGetRoute() {
		
		PathEntriesVO pathEntriesVO = new PathEntriesVO();
		
		pathEntriesVO.setMapName(mapCampinas.getMap().getName());
		pathEntriesVO.setOrigin("Limeira");
		pathEntriesVO.setDestination("Campinas");
		pathEntriesVO.setAutonomy(10.0);
		pathEntriesVO.setGasprice(2.5);
        
		RouteVO routeVO = restTemplate.postForObject(SERVER_URI+WalmartRestURIConstants.GET_PLAN_COST_ROUTE, pathEntriesVO, RouteVO.class);
		
		return routeVO;	
	}
	
	/**
	 * This method set a map of cities in Campinas region and calculates the shortest path and costs.
	 * Origin and destination the same
	 */
	public RouteVO testGetRouteOriginDestinationEquals() {
		
		PathEntriesVO pathEntriesVO = new PathEntriesVO();
		
		pathEntriesVO.setMapName(mapCampinas.getMap().getName());
		pathEntriesVO.setOrigin("Limeira");
		pathEntriesVO.setDestination("Limeira");
		pathEntriesVO.setAutonomy(10.0);
		pathEntriesVO.setGasprice(2.5);
        
		RouteVO routeVO = restTemplate.postForObject(SERVER_URI+WalmartRestURIConstants.GET_PLAN_COST_ROUTE, pathEntriesVO, RouteVO.class);
		
		return routeVO;	
	}
	
	/**
	 * This method set a map of cities in Campinas region and calculates the shortest path and costs.
	 */
	public RouteVO testGetRouteInvalid() {
		
		PathEntriesVO pathEntriesVO = new PathEntriesVO();
		
		pathEntriesVO.setMapName(mapCampinas.getMap().getName());
		pathEntriesVO.setOrigin("Limeira");
		pathEntriesVO.setDestination("Sorocaba");
		pathEntriesVO.setAutonomy(10.0);
		pathEntriesVO.setGasprice(2.5);
		logger.info("Invalid Destination: "+pathEntriesVO.getDestination());
		RouteVO routeVO = restTemplate.postForObject(SERVER_URI+WalmartRestURIConstants.GET_PLAN_COST_ROUTE, pathEntriesVO, RouteVO.class);
		logger.info(routeVO.getErrorMessage());
		return routeVO;	
	}
	
	/**
	 * This method set a map of cities in Campinas region and calculates the shortest path and costs.
	 */
	public RouteVO testGetRouteInvalidNegativeNumber() {
		
		PathEntriesVO pathEntriesVO = new PathEntriesVO();
		
		pathEntriesVO.setMapName(mapCampinas.getMap().getName());
		pathEntriesVO.setOrigin("Limeira");
		pathEntriesVO.setDestination("Campinas");
		pathEntriesVO.setAutonomy(-10.0);
		pathEntriesVO.setGasprice(-2.5);
		logger.info("Invalid Destination: "+pathEntriesVO.getDestination());
		RouteVO routeVO = restTemplate.postForObject(SERVER_URI+WalmartRestURIConstants.GET_PLAN_COST_ROUTE, pathEntriesVO, RouteVO.class);
		logger.info(routeVO.getErrorMessage());
		return routeVO;	
	}
	
	/**
	 * This method set a map of cities in Campinas region and calculates the shortest path and costs.
	 */
	public RouteVO testGetRouteInvalidNull() {
		
		PathEntriesVO pathEntriesVO = new PathEntriesVO();
		RouteVO routeVO = restTemplate.postForObject(SERVER_URI+WalmartRestURIConstants.GET_PLAN_COST_ROUTE, pathEntriesVO, RouteVO.class);
		logger.info(routeVO.getErrorMessage());
		return routeVO;	
	}
	
	/**
	 * Shutdown jetty server. 
	 * @throws Exception
	 */
	@After
	public void shutdownServer() throws Exception {
		server.stop();
		logger.info("Jetty Server has stopped");
    }


}
