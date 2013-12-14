package com.controle.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Fornecedor {
	@GeneratedValue
	@Id
	private Long idFornecedor;
	private String nome;
	private String cnpj;

	public Long getId() {
		return idFornecedor;
	}

	public void setId(Long id) {
		this.idFornecedor = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
