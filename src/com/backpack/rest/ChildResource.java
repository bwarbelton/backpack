package com.backpack.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.backpack.dataAccess.child.ChildDataService;
import com.backpack.dataAccess.child.ChildDataServiceSQL;
import com.backpack.model.Child;

//The Java class will be hosted at the URI path "rest/child"
@Path("/child")
public class ChildResource {

	private ChildDataService childDataService = new ChildDataServiceSQL();

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Child create(Child child) {
		return childDataService.insert(child);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Child update(Child child) {
		return childDataService.update(child);
	}
	
	// The Java method will process HTTP GET requests
	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	// For curl default is -H "Accept: text/plain"
	// Curl to test
	// curl -v -H "Content-Type: text/plain" -X GET 'http://localhost:8080/Backpack/rest/child'
	@Produces(MediaType.TEXT_PLAIN)
	public String getChildrenText() {
		return childDataService.fetchAllChildren().toString();
	}

	// This method is called if HTML is request
	// Curl to test
	// curl -v -H "Content-Type: text/plain" -X GET 'http://localhost:8080/Backpack/rest/child'	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getChildrenHtml() {
		return "<html> " + "<title>" + "Hello Wyatt" + "</title>"
				+ "<body><h1>" + "Hello" + "</body></h1>" + "</html> ";
	}
	
	// Curl to test
	// curl -v -H "Accept: application/json" -H "Content-Type: application/json" -X GET 'http://localhost:8080/Backpack/rest/child'
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Child> getChildrenJSON() {
		return childDataService.fetchAllChildren();
	}
	
	// Curl to test
	// curl -v -H "Accept: application/json" -H "Content-Type: application/json" -X GET 'http://localhost:8080/Backpack/rest/child/1234'
	@GET
	@Path("{childId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getChildJSON( @PathParam("childId") int childId) {
		Child child = childDataService.fetchChild(childId);
		return Response.status(200).entity(child).build();
	}
	
	public void setChildDataService(ChildDataService childDataService) {
		this.childDataService = childDataService;
	}
	
}