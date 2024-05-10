/**
 * 
 */
package com.logustecnologia.appPostoCombustivel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logustecnologia.appPostoCombustivel.model.Posto;

/**
 * @author Petronilo Padilha
 * email: nilopadilha@gmail.com
 * Telefone: 84 99498-4982
 *
 */
@Repository
public interface PostoRepository extends JpaRepository<Posto, Long> {

}
