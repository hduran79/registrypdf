package business.test;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import config.BusinessException;
import config.ExceptionMessage;
import dto.test.TestDAO;
import repository.test.TestRespository;

@ApplicationScoped
public class TestBusiness {
	
	@Inject
	private TestRespository respository;
	
	/**
	 * 
	 * @return
	 */
	public TestDAO testGet() {
		return respository.testGet();
	}

	
	public String testGetParam(String id) {
		return respository.testGetParam(id);
	}
	
	public List<TestDAO> testPost(TestDAO id) {
		return respository.testPost(id);
	}
	
	public String exception() {
		String[] parameters = {"13720922"};
		throw new BusinessException(ExceptionMessage.GENERAL_EXCEPTION, parameters); 
	}
}

