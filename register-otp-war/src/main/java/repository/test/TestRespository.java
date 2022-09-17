package repository.test;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import dto.test.TestDAO;

@ApplicationScoped
public class TestRespository {

	public TestDAO testGet() {

		TestDAO dao = new TestDAO();
		dao.setAge("38");
		dao.setCountry("Medellin");
		dao.setName("Hugo Duran");
		return dao;
	}

	public String testGetParam(String id) {
		return id;
	}
	
	public List<TestDAO> testPost(TestDAO data) {
		
		List<TestDAO> list = new ArrayList<TestDAO>();
		TestDAO dao = new TestDAO();
		
		dao.setAge(data.getAge());
		dao.setCountry(data.getCountry());
		dao.setName("Hugo Duran");
		list.add(dao);
		
		return list;
	}
}
