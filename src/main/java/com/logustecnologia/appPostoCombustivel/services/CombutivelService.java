/**
 * 
 */
package com.logustecnologia.appPostoCombustivel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logustecnologia.appPostoCombustivel.model.Combustivel;
import com.logustecnologia.appPostoCombustivel.repositories.CombustivelRepository;
import com.logustecnologia.appPostoCombustivel.services.exception.DataIntegrityViolationException;
import com.logustecnologia.appPostoCombustivel.services.exception.ObjectNotFoundException;

/**
 * @author Petronilo Padilha
 * email: nilopadilha@gmail.com
 * Telefone: 84 99498-4982
 *
 */
@Service
public class CombutivelService {

	
	@Autowired
	private CombustivelRepository repository;
	
	/**
	 * Realiza busca de um Combustivel por Id
	 * 
	 * @param id - Long
	 * @return Combustivel
	 */
	public Combustivel findById( Long id )  {	
		Optional<Combustivel> obj = repository.findById(id);	
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto não encontrato! Id: " + id + ", Tipo: " + Combustivel.class.getName()) );
	}
	
	/**
	 * Busca todos os combustivel
	 * 
	 * @return List<Combustivel> 
	 */
	public List<Combustivel> findAll() {
		return repository.findAll();
	}
	
	/**
	 * Cria um combustivel
	 * 
	 * @param combustivel - Combustivel
	 * @return Combustivel
	 */
	public Combustivel create( Combustivel combustivel ) {
		return this.repository.save(combustivel);
	}
	
	/**
	 * Atualiza um combustível
	 * 
	 * @param id - Long
	 * @param obj - Combustivel
	 * @return Combustivel
	 */
	public Combustivel update(Long id, Combustivel obj) {
		Combustivel newObj = findById(id);
		return repository.save(newObj);
	}
	
	/**
	 * Deleta um combustivel do Posto
	 * 
	 * @param id
	 */
	public void delete( Long id ) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch ( DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException
			( "O combustivel não pode ser deletado! existem bombas vinculadas ao tipo de Combustivel." );
		}
	}

}
