package com.controle.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.controle.model.Fornecedor;

public class FornecedorDAO {
	@SuppressWarnings("unchecked")
	public List<Fornecedor> listaFornecedor() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Fornecedor> fornecedores = session.createQuery("FROM Fornecedor")
				.list();
		session.close();
		return fornecedores;
	}

	public void editar(Fornecedor Fornecedor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(Fornecedor);
		transaction.commit();
		session.close();

	}

	public void salvar(Fornecedor Fornecedor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(Fornecedor);
		transaction.commit();
		session.close();

	}

	public Fornecedor getByName(String nome) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Fornecedor Fornecedor = (Fornecedor) session
				.createQuery("from Fornecedor c " + "where c.nome = :nome")
				.setParameter("nome", nome).uniqueResult();
		session.close();
		return Fornecedor;
	}
}
