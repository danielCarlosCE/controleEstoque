package com.controle.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.controle.dao.VendaDAO;
import com.controle.model.Venda;

public class VendasTeste {
	private VendaDAO vendaDAO;

	@Before
	public void beforeTest() {
		vendaDAO = new VendaDAO();
	}

	@Test
	public void listarTeste() {

		List<Venda> vendas = vendaDAO.listarVendas();

		Assert.assertNotNull(vendas);

	}

	@Test
	public void editarTeste() {
		Venda v = vendaDAO.buscarPorDatas("01/01/2013", "10/10/2014").get(0);
		v.setValor(40.50);
		vendaDAO.editar(v);
	}

}
