package com.logustecnologia.appPostoCombustivel.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * @author Petronilo Padilha
 * email: nilopadilha@gmail.com
 * Telefone: 84 99498-4982
 *
 */
public class Posto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String nome;
	
	@OneToMany(mappedBy = "posto")
	private List<Bomba> bombas;	
	
	
	
	

	/**
	 * 
	 */
	public Posto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @param id
	 * @param nome
	 * @param bombas
	 */
	public Posto(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
		
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
	 * @return the bombas
	 */
	public List<Bomba> getBombas() {
		return bombas;
	}

	/**
	 * @param bombas the bombas to set
	 */
	public void setBombas(List<Bomba> bombas) {
		this.bombas = bombas;
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
		Posto other = (Posto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Posto [nome=" + nome + "]";
	}



	
	

}
