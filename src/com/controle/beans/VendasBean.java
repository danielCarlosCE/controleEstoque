package com.controle.beans;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.controle.dao.ClienteDAO;
import com.controle.dao.ProdutoDAO;
import com.controle.dao.VendaDAO;
import com.controle.model.Cliente;
import com.controle.model.Produto;
import com.controle.model.Venda;

@ManagedBean(name = "vendasBean")
public class VendasBean {

	List<Venda> vendas;
	Venda vendaSelecionada;
	boolean editando;
	String nomeProduto;
	String nomeCliente;

	public VendasBean() {
		iniciar();
	}

	private String iniciar() {
		vendaSelecionada = new Venda();
		vendas = new VendaDAO().listarVendas();
		return "vendas";

	}

	public String editar() {
		editando = true;
		nomeProduto = vendaSelecionada.getProduto().getNome();
		nomeCliente = vendaSelecionada.getCliente().getNome();
		return "cadastroVenda";
	}

	public String cadastro() {
		editando = false;
		vendaSelecionada = new Venda();
		vendaSelecionada.setDataVenda(new Date());
		return "cadastroVenda";
	}


	public String submit() {

		Produto produto = new ProdutoDAO().getByName(nomeProduto);
		if (produto == null) {
			produto = new Produto();
			produto.setNome(nomeProduto);
			produto.setPreco(vendaSelecionada.getValor());
			produto.setAtivo(true);
			new ProdutoDAO().salvar(produto);
		}else{
			produto.setAtivo(true);
			new ProdutoDAO().editar(produto);
		}
		

		Cliente cliente = new ClienteDAO().getByName(nomeCliente);
		if (cliente == null) {
			cliente = new Cliente();
			cliente.setNome(nomeCliente);
			cliente.setCpf(gerarCpfRandom());
			new ClienteDAO().salvar(cliente);
		}

		vendaSelecionada.setProduto(produto);
		vendaSelecionada.setCliente(cliente);
		if (editando) {
			new VendaDAO().editar(vendaSelecionada);
		} else {
			new VendaDAO().salvar(vendaSelecionada);
		}
		return iniciar();
	}
	
	public String remover(){
		if(vendaSelecionada!=null){
			new VendaDAO().remover(vendaSelecionada);
		}
		return iniciar();
	}

	private String gerarCpfRandom() {
		return "11111111111";
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public Venda getVendaSelecionada() {
		return vendaSelecionada;
	}

	public void setVendaSelecionada(Venda vendaSelecionada) {
		this.vendaSelecionada = vendaSelecionada;
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

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

}
