/**
 * 
 */
package com.logustecnologia.appPostoCombustivel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.logustecnologia.appPostoCombustivel.model.Veiculo;

/**
 * @author Petronilo Padilha
 * email: nilopadilha@gmail.com
 * Telefone: 84 99498-4982
 *
 */
@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

	
	@Query("select v from Veiculo v where v.qtdCombustivel < v.capacidadeTanque")
    List<Veiculo> buscarTodosNaoAbastecidos();
}
