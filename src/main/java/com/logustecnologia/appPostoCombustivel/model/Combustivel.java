package com.logustecnologia.appPostoCombustivel.model;

import com.logustecnologia.appPostoCombustivel.model.enums.TipoCombustivel;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * @author Petronilo Padilha email: nilopadilha@gmail.com Telefone: 84
 *         99498-4982
 *
 */
public class Combustivel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private TipoCombustivel tipoCombustivel;

	/**
	 * 
	 */
	public Combustivel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param tipoCombustivel
	 */
	public Combustivel(Long id, TipoCombustivel tipoCombustivel) {
		super();
		this.id = id;
		this.tipoCombustivel = tipoCombustivel;
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
	 * @return the tipoCombustivel
	 */
	public TipoCombustivel getTipoCombustivel() {
		return tipoCombustivel;
	}

	/**
	 * @param tipoCombustivel the tipoCombustivel to set
	 */
	public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

}
