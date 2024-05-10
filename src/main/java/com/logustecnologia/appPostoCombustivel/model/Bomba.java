package com.logustecnologia.appPostoCombustivel.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



/**
 * @author Petronilo Padilha
 * email: nilopadilha@gmail.com
 * Telefone: 84 99498-4982
 *
 */
@Entity
public class Bomba implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double preco;

	private Integer velocidadeAbastece;   
	
	@ManyToOne
	@JoinColumn(name = "combustivel_id")
	private Combustivel combustivel;
	
	@ManyToOne
	@JoinColumn(name = "posto_id")
	private Posto posto;

	

	/**
	 * 
	 */
	public Bomba() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * @param id
	 * @param preco
	 * @param velocidadeAbastece
	 * @param combustivel
	 * @param posto
	 */
	public Bomba(Long id, Double preco, Integer velocidadeAbastece, Combustivel combustivel, Posto posto) {
		super();
		this.id = id;
		this.preco = preco;
		this.velocidadeAbastece = velocidadeAbastece;
		this.combustivel = combustivel;
		this.posto = posto;
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
	 * @return the preco
	 */
	public Double getPreco() {
		return preco;
	}

	/**
	 * @param preco the preco to set
	 */
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	/**
	 * @return the velocidadeAbastece
	 */
	public Integer getVelocidadeAbastece() {
		return velocidadeAbastece;
	}

	/**
	 * @param velocidadeAbastece the velocidadeAbastece to set
	 */
	public void setVelocidadeAbastece(Integer velocidadeAbastece) {
		this.velocidadeAbastece = velocidadeAbastece;
	}

	/**
	 * @return the combustivel
	 */
	public Combustivel getCombustivel() {
		return combustivel;
	}

	/**
	 * @param combustivel the combustivel to set
	 */
	public void setCombustivel(Combustivel combustivel) {
		this.combustivel = combustivel;
	}

	/**
	 * @return the posto
	 */
	public Posto getPosto() {
		return posto;
	}

	/**
	 * @param posto the posto to set
	 */
	public void setPosto(Posto posto) {
		this.posto = posto;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bomba other = (Bomba) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Bomba"+ id + " " + combustivel.getTipoCombustivel().name().toLowerCase() + " - preco: R$ " + preco;
	}
	
}
