package com.controle.testes;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Test;

import com.controle.dao.HibernateUtil;

public class ConexaoBDTeste {
	
	Session session;

	@Test
	public void teste() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	@After
	public void after() {
		session.close();
	}
}
