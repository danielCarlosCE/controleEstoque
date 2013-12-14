package com.controle.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.controle.dao.ProdutoDAO;
import com.controle.model.Produto;

public class ProdutosTeste {
	private ProdutoDAO produtoDAO;

	@Before
	public void beforeTest() {
		produtoDAO = new ProdutoDAO();
	}

	@Test
	public void listarAtivosTeste() {
		List<Produto> produtosAtivos = produtoDAO.listarProdutosAtivos();
		for (Produto produto : produtosAtivos) {
			Assert.assertTrue(produto.isAtivo());
		}
	}

	@Test
	public void adicionarTeste() {
		Produto p = new Produto();
		p.setNome("testes unit‡rios");
		p.setPreco(22.4);
		produtoDAO.salvar(p);
	}

	@Test
	public void editarTeste() {
		Produto p = produtoDAO.getById(1L);
		p.setNome(p.getNome() + "testes unit‡rios");
		produtoDAO.editar(p);
	}

	@Test
	public void byIdTeste() {
		Produto p = produtoDAO.getById(1L);
		Assert.assertNull(p);
	}

}
