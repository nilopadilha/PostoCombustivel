/**
 * 
 */
package com.logustecnologia.appPostoCombustivel.model.enums;

/**
 * @author Petronilo Padilha 
 * email: nilopadilha@gmail.com 
 * Telefone: 84 99498-4982
 *
 */
public enum TipoCombustivel {

	GASOLINAC("Gasolina_Comun", 1),
	GASOLINAADIT("Gasolina_Aditivada", 2), 
	GASOLINAP("Gasolina_Premium",3), 
	ALCOOL("Alcool",4), 
	DIESEL("Diesel",5);

	
	private Integer opcao;
    private String nome;
    
    
	/**
	 * @param nome
	 * @param opcao
	 */
	private TipoCombustivel(String nome, Integer opcao) {
		this.opcao = opcao;
		this.nome = nome;
	}
	/**
	 * @return the opcao
	 */
	public Integer getOpcao() {
		return opcao;
	}
	/**
	 * @param opcao the opcao to set
	 */
	public void setOpcao(Integer opcao) {
		this.opcao = opcao;
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
    

}
