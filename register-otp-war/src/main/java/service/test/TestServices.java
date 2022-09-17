package service.test;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import business.test.TestBusiness;
import dto.test.TestDAO;

@Path("test")
@Produces(MediaType.APPLICATION_JSON)
public class TestServices {

	@Inject
	private TestBusiness business;

	/**
	 * Método GET
	 * 
	 * @return
	 */
	@GET
	@Path("test-Get")
	public TestDAO testGet() {
		return business.testGet();
	}

	/**
	 * Método GET
	 * 
	 * @return
	 */
	@GET
	@Path("test-Get-Parama/{id}")
	public String testGetParam(@PathParam("id") String id) {
		return business.testGetParam(id);
	}

	@POST
	@Path("test-Post")
	public List<TestDAO> testPost(TestDAO dao) {
		return business.testPost(dao);
	}

	@GET
	@Path("exception")
	public String exception() {
		return business.exception();
	}
}
