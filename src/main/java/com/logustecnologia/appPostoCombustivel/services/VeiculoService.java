/**
 * 
 */
package com.logustecnologia.appPostoCombustivel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logustecnologia.appPostoCombustivel.model.Veiculo;
import com.logustecnologia.appPostoCombustivel.repositories.VeiculoRepository;
import com.logustecnologia.appPostoCombustivel.services.exception.DataIntegrityViolationException;
import com.logustecnologia.appPostoCombustivel.services.exception.ObjectNotFoundException;

/**
 * @author Petronilo Padilha
 * email: nilopadilha@gmail.com
 * Telefone: 84 99498-4982
 *
 */
@Service
public class VeiculoService {

	
	@Autowired
	private VeiculoRepository repository;
	
	/**
	 * realiza a busca de um Veiculo por Id
	 * 
	 * @param id
	 * @return Veiculo
	 */
	public Veiculo findById( Long id )  {	
		Optional<Veiculo> obj = repository.findById(id);	
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto não encontrato! Id: " + id + ", Tipo: " + Veiculo.class.getName()) );
	}
	
	/**
	 * Busca todos os Veiculos
	 * 
	 * @return List<Veiculo> 
	 */
	public List<Veiculo> findAll() {
		return repository.findAll();
	}
	
	/**
	 * Busca todos os veiculos ainda não abastecidos
	 * 
	 * @return List<Veiculo> 
	 */
	public List<Veiculo> buscarTodosNaoAbastecidos() {
		return repository.buscarTodosNaoAbastecidos();
	}
	
	/**
	 * Cria um veiculo
	 * 
	 * @param veiculo
	 * @return Veiculo
	 */
	public Veiculo create( Veiculo veiculo ) {
		return this.repository.save(veiculo);
	}
	
	/**
	 * Atualiza um veiculo
	 * 
	 * @param id
	 * @param veiculo
	 * @return Veiculo
	 */
	public Veiculo update(Long id, Veiculo veiculo) {
		
		Veiculo obj = findById(id);
		obj.setModelo( veiculo.getModelo() );
		obj.setNome( veiculo.getNome() );
		obj.setPlaca( veiculo.getPlaca() );
		obj.setCapacidadeTanque( veiculo.getCapacidadeTanque() );
		obj.setQtdCombustivel( veiculo.getQtdCombustivel() );
		
		return repository.save(obj);
	}
	
	/**
	 * Deleta um Posto
	 * 
	 * @param id
	 */
	public void delete( Long id ) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch ( DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException
			( "O veiculo não pode ser deletado! Já esta vinculado ao posto de abastecimento" );
		}
	}
}
