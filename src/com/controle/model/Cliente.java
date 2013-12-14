package com.controle.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cliente {
	@GeneratedValue
	@Id
	private Long idCliente;
	private String nome;
	private String cpf;

	public Long getId() {
		return idCliente;
	}

	public void setId(Long id) {
		this.idCliente = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
