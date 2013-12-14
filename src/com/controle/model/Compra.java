package com.controle.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Compra {
	@GeneratedValue
	@Id
	private Long idCompra;
	@ManyToOne
	@JoinColumn(name="idFornecedor")
	private Fornecedor fornecedor;
	@ManyToOne
	@JoinColumn(name="idProduto")
	private Produto produto;
	private double valor;
	private Date dataCompra;

	public Long getId() {
		return idCompra;
	}

	public void setId(Long id) {
		this.idCompra = id;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
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

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

}
