/**
 * 
 */
package com.logustecnologia.appPostoCombustivel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logustecnologia.appPostoCombustivel.model.Posto;
import com.logustecnologia.appPostoCombustivel.repositories.PostoRepository;
import com.logustecnologia.appPostoCombustivel.services.exception.DataIntegrityViolationException;
import com.logustecnologia.appPostoCombustivel.services.exception.ObjectNotFoundException;

/**
 * @author Petronilo Padilha email: nilopadilha@gmail.com Telefone: 84
 *         99498-4982
 *
 */
@Service
public class PostoService {

	@Autowired
	private PostoRepository repository;

	/**
	 * Realiza Busca uma Bomba por Id
	 * 
	 * @param id
	 * @return Bomba
	 */
	public Posto findById(Long id) {
		Optional<Posto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrato! Id: " + id + ", Tipo: " + Posto.class.getName()));
	}

	/**
	 * faz um Buscar todos as bombas
	 * 
	 * @return List<Bomba>
	 */
	public List<Posto> findAll() {
		return repository.findAll();
	}

	/**
	 * Cria uum novo posto de combustiveis
	 * 
	 * 
	 * @param posto
	 * @return posto
	 */
	public Posto create(Posto posto) {

		return this.repository.save(posto);
	}

	/**
	 * Atualiza um posto de combustivel
	 * 
	 * @param id  - Long
	 * @param obj - Posto
	 * @return posto
	 */
	public Posto update(Long id, Posto posto) {

		Posto obj = findById(id);
		obj.setBombas(posto.getBombas());
		obj.setNome(posto.getNome());

		return repository.save(obj);
	}

	/**
	 * Deleta um Posto
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(
					"O Posto não pode ser deletado! Pois existem bombas e Abastecimentos vinculados ao posto.");
		}
	}

}
