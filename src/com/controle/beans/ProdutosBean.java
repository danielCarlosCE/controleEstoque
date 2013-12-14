package com.controle.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.controle.dao.ProdutoDAO;
import com.controle.model.Produto;

@ManagedBean(name = "produtosBean")
@RequestScoped
public class ProdutosBean {

	List<Produto> produtos;
	Produto produtoSelecionado;
	boolean editando;

	public ProdutosBean() {
		iniciar();
	}

	public String iniciar() {
		produtoSelecionado = new Produto();
		produtos = new ProdutoDAO().listarProdutosAtivos();
		return "produtos";
	}

	public String editar() {
		editando = true;
		return "cadastroProduto";
	}

	public String cadastro() {
		editando = false;
		produtoSelecionado = new Produto();
		return "cadastroProduto";
	}

	public String submit() {
		if (editando) {
			new ProdutoDAO().editar(produtoSelecionado);
		} else {
			new ProdutoDAO().salvar(produtoSelecionado);
		}
		return iniciar();
	}

	public String remover() {
		if (produtoSelecionado != null) {
			produtoSelecionado.setAtivo(false);
			new ProdutoDAO().editar(produtoSelecionado);
		}
		return iniciar();
	}

	public List<Produto> getProdutos() {

		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

}
