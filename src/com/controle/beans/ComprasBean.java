package com.controle.beans;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.controle.dao.CompraDAO;
import com.controle.dao.FornecedorDAO;
import com.controle.dao.ProdutoDAO;
import com.controle.model.Compra;
import com.controle.model.Fornecedor;
import com.controle.model.Produto;

@ManagedBean(name = "comprasBean")
public class ComprasBean {
	List<Compra> compras;
	Compra compraSelecionada;
	String nomeProduto;
	String nomeFornecedor;
	boolean editando;

	public ComprasBean() {
		iniciar();
	}

	public String iniciar() {
		compras = new CompraDAO().listarCompras();
		compraSelecionada = new Compra();
		return "compras";
	}

	public String editar() {
		editando = true;
		nomeProduto = compraSelecionada.getProduto().getNome();
		nomeFornecedor = compraSelecionada.getFornecedor().getNome();
		return "cadastroCompra";
	}

	public String cadastro() {
		editando = false;
		compraSelecionada = new Compra();
		compraSelecionada.setDataCompra(new Date());
		return "cadastroCompra";
	}

	public String submit() {
		Produto produto = new ProdutoDAO().getByName(nomeProduto);
		if (produto == null) {
			produto = new Produto();
			produto.setNome(nomeProduto);
			produto.setPreco(compraSelecionada.getValor());
			produto.setAtivo(true);
			new ProdutoDAO().salvar(produto);
		} else {
			produto.setAtivo(true);
			new ProdutoDAO().editar(produto);
		}

		Fornecedor fornecedor = new FornecedorDAO().getByName(nomeFornecedor);
		if (fornecedor == null) {
			fornecedor = new Fornecedor();
			fornecedor.setNome(nomeFornecedor);
			fornecedor.setCnpj("111111111111");
			new FornecedorDAO().salvar(fornecedor);
		}

		compraSelecionada.setProduto(produto);
		compraSelecionada.setFornecedor(fornecedor);

		if (editando) {
			new CompraDAO().editar(compraSelecionada);
		} else {
			new CompraDAO().salvar(compraSelecionada);
		}
		return iniciar();
	}

	public String remover() {
		return iniciar();
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra getCompraSelecionada() {
		return compraSelecionada;
	}

	public void setCompraSelecionada(Compra compraSelecionada) {
		this.compraSelecionada = compraSelecionada;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

}
