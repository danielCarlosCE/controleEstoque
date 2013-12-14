package com.controle.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.controle.model.Cliente;
import com.controle.model.Compra;
import com.controle.model.Fornecedor;
import com.controle.model.Produto;
import com.controle.model.Venda;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure()
					.addAnnotatedClass(Produto.class)
					.addAnnotatedClass(Cliente.class)
					.addAnnotatedClass(Compra.class)
					.addAnnotatedClass(Fornecedor.class)
					.addAnnotatedClass(Venda.class).buildSessionFactory();

		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}
}
