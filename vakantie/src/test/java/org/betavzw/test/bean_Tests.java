package org.betavzw.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.betavzw.entity.Werknemer;
import org.betavzw.view.bean.WerknemerBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class bean_Tests {

	EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("unitName");
	
	EntityManager manager = emf.createEntityManager();
	
	WerknemerBean werknemerBean = new WerknemerBean();
	
	@Before
	public void setup() {
		werknemerBean.setEntityManager(manager);
	} 
	
//	@After
//	public void setdown() {
//		emf.close();
//		manager.close();
//	} 
	
	@Test
	public void test() {
		assertTrue(manager.isOpen());
	}
	

	@Test
	public void WerknemerUpdate_Test() {
		Werknemer wn = new Werknemer();
		wn.setNaam("sqdfqsdf");
		werknemerBean.update(wn);
//		werknemerBean.
		List<Werknemer> list = werknemerBean.get();
		System.out.println(list.size());
		assertTrue("failure - Werknemer not added", list.contains(wn));
	}
	
}
