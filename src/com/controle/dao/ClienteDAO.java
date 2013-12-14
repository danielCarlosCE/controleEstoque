package com.controle.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.controle.model.Cliente;

public class ClienteDAO {
	@SuppressWarnings("unchecked")
	public List<Cliente> listarClientes() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Cliente> clientes = session.createQuery("FROM Cliente").list();
		session.close();
		return clientes;
	}

	public void editar(Cliente cliente) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(cliente);
		transaction.commit();
		session.close();

	}

	public void salvar(Cliente cliente) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(cliente);
		transaction.commit();
		session.close();

	}

	public Cliente getByName(String nome) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Cliente cliente = (Cliente) session
				.createQuery("from Cliente c " + "where c.nome = :nome")
				.setParameter("nome", nome).uniqueResult();
		session.close();
		return cliente;
	}
}
