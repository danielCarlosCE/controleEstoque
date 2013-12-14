package com.controle.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.controle.model.Venda;

public class VendaDAO {
	@SuppressWarnings("unchecked")
	public List<Venda> listarVendas() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Venda> vendas = session.createQuery("FROM Venda").list();
		session.close();
		return vendas;
	}

	public void editar(Venda venda) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(venda);
		transaction.commit();
		session.close();
	}

	public void salvar(Venda venda) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(venda);
		transaction.commit();
		session.close();

	}

	public void remover(Venda venda) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(venda);
		transaction.commit();
		session.close();

	}

	public List<Venda> buscarPorDatas(String dataInicial, String dataFinal) {
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
		List<Venda> vendas = (List<Venda>) session
				.createCriteria(Venda.class, "v")
				.add(Restrictions.between("dataVenda", dataIninicalParam,
						dataFinalParam)).list();
		session.close();
		return vendas;
	}

	

}
