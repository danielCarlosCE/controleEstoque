package com.controle.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.controle.model.Produto;

public class ProdutoDAO {

	@SuppressWarnings("unchecked")
	public List<Produto> listarProdutosAtivos() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Produto> produtos = session.createQuery(
				"FROM Produto where ativo=true").list();
		session.close();
		return produtos;
	}

	public void editar(Produto produto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(produto);
		transaction.commit();
		session.close();

	}

	public void salvar(Produto produto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(produto);
		transaction.commit();
		session.close();
	}

	public Produto getById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Produto produto = (Produto) session.get(Produto.class, id);
		session.close();
		return produto;
	}

	public Produto getByName(String nome) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Produto produto = (Produto) session
				.createQuery("from Produto p " + "where p.nome = :nome")
				.setParameter("nome", nome).uniqueResult();
		session.close();
		return produto;
	}

	public List<Produto> listarTodos() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Produto> produtos = session.createQuery("FROM Produto").list();
		session.close();
		return produtos;
	}

}
