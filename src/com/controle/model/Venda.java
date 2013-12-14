package com.controle.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Venda {

	@GeneratedValue
	@Id
	private Long idVenda;
	
	@ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name="idProduto")
	private Produto produto;
	private double valor;
	private Date dataVenda;

	public Long getId() {
		return idVenda;
	}

	public void setId(Long id) {
		this.idVenda = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

}
