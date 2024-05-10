/**
 * 
 */
package com.logustecnologia.appPostoCombustivel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logustecnologia.appPostoCombustivel.model.Bomba;
import com.logustecnologia.appPostoCombustivel.model.Combustivel;
import com.logustecnologia.appPostoCombustivel.model.Posto;
import com.logustecnologia.appPostoCombustivel.repositories.BombaRepository;
import com.logustecnologia.appPostoCombustivel.services.exception.DataIntegrityViolationException;
import com.logustecnologia.appPostoCombustivel.services.exception.ObjectNotFoundException;

/**
 * @author Petronilo Padilha
 * email: nilopadilha@gmail.com
 * Telefone: 84 99498-4982
 *
 */
@Service
public class BombaService {

	@Autowired
	private BombaRepository repository;
	
	/**
	 * Realiza Busca uma Bomba por Id
	 * 
	 * @param id
	 * @return Bomba
	 */
	public Bomba findById( Long id )  {	
		Optional<Bomba> obj = repository.findById(id);	
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto não encontrato! Id: " + id + ", Tipo: " + Bomba.class.getName()) );
	}
	
	/**
	 * faz um Buscar todos as bombas
	 * 
	 * @return List<Bomba> 
	 */
	public List<Bomba> findAll() {
		return repository.findAll();
	}
	
	/**
	 * Cria uma nova Bomba
	 * 
	 * @param bomba
	 * @param conbustivel
	 * @param posto
	 * @return
	 */
	public Bomba create( Bomba bomba, Combustivel conbustivel, Posto posto  ) {
		bomba.setCombustivel(conbustivel);
		bomba.setPosto(posto);
		return this.repository.save(bomba);
	}
	
	/**
	 * Atualiza a Bomba de combustivel
	 * 
	 * @param id - Long
	 * @param obj - Bomba
	 * @return Bomba
	 */
	public Bomba update( Bomba obj ) {
		Bomba newObj = findById(obj.getId());
		return repository.save(newObj);
	}
	
	/**
	 * Deleta uma bomba de um Posto de combustiveis
	 * 
	 * @param id
	 */
	public void delete( Long id ) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch ( DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException
			( "A Bomba não pode ser deletado! Pois existem Abastecimentos vinculados." );
		}
	}
}
