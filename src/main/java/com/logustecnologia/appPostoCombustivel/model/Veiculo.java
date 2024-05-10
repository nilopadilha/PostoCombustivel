package com.logustecnologia.appPostoCombustivel.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/**
 * @author Petronilo Padilha
 * email: nilopadilha@gmail.com
 * Telefone: 84 99498-4982
 *
 */

@Entity

public class Veiculo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String modelo;
		
	private String nome;
		
	private String placa;
	
	private Double capacidadeTanque;
	
	private Double qtdCombustivel;

	/**
	 * 
	 */
	public Veiculo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param placa
	 * @param nome
	 * @param capacidadeTanque
	 * @param modelo
	 * @param qtdCombustivel
	 * @param id
	 */
	public Veiculo(String placa, String nome, Double capacidadeTanque, String modelo, Double qtdCombustivel, Long id) {
		super();
		this.placa = placa;
		this.nome = nome;
		this.capacidadeTanque = capacidadeTanque;
		this.modelo = modelo;
		this.qtdCombustivel = qtdCombustivel;
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * @return the capacidadeTanque
	 */
	public Double getCapacidadeTanque() {
		return capacidadeTanque;
	}

	/**
	 * @param capacidadeTanque the capacidadeTanque to set
	 */
	public void setCapacidadeTanque(Double capacidadeTanque) {
		this.capacidadeTanque = capacidadeTanque;
	}

	/**
	 * @return the qtdCombustivel
	 */
	public Double getQtdCombustivel() {
		return qtdCombustivel;
	}

	/**
	 * @param qtdCombustivel the qtdCombustivel to set
	 */
	public void setQtdCombustivel(Double qtdCombustivel) {
		this.qtdCombustivel = qtdCombustivel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Veiculo [modelo=" + modelo + ", nome=" + nome + ", placa=" + placa + "]";
	}
	
	
	
}
