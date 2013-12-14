package com.controle.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.controle.model.Compra;

public class CompraDAO {
	@SuppressWarnings("unchecked")
	public List<Compra> listarCompras() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Compra> compras = session.createQuery("FROM Compra").list();
		session.close();
		return compras;
	}

	public void editar(Compra compra) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(compra);
		transaction.commit();
		session.close();
	}

	public void salvar(Compra compra) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(compra);
		transaction.commit();
		session.close();

	}

	public void remover(Compra compra) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(compra);
		transaction.commit();
		session.close();

	}

	public List<Compra> buscarPorDatas(String dataInicial, String dataFinal) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Date dataIninicalParam = null;
		Date dataFinalParam = null;
		try {
			DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
			dataIninicalParam = sourceFormat.parse(dataInicial);
			dataFinalParam = sourceFormat.parse(dataFinal);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		@SuppressWarnings("unchecked")
		List<Compra> Compras = (List<Compra>) session
				.createCriteria(Compra.class, "c")
				.add(Restrictions.between("dataCompra", dataIninicalParam,
						dataFinalParam)).list();
		session.close();
		return Compras;
	}

}
