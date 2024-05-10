/**
 * 
 */
package com.logustecnologia.appPostoCombustivel.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.logustecnologia.appPostoCombustivel.model.Veiculo;
import com.logustecnologia.appPostoCombustivel.services.VeiculoService;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

/**
 * @author Petronilo Padilha
 * email: nilopadilha@gmail.com
 * Telefone: 84 99498-4982
 *
 */

@RestController
@RequestMapping(value = "/api/v1/veiculos")
public class VeiculoController {
	
	@Autowired
	private VeiculoService service;
	
	@ApiOperation(value = "Endpoint pesquisar veiculo por id")
	@GetMapping("/pesquisar/{id}")
	public ResponseEntity<Veiculo> findById(@PathVariable Long id) {	
		
		Veiculo obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value = "Endpoint listar todos veiculos")
	@GetMapping(value = "/listarTodos")
	public ResponseEntity< List<Veiculo> > findAll( ) {
		
		List<Veiculo> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
	@ApiOperation(value = "Endpoint listar todos veiculos não abastecidos")
	@GetMapping(value = "/naoAbastecidos")
	public ResponseEntity< List<Veiculo> > buscarTodosNaoAbastecidos( ) {
		
		List<Veiculo> list = service.buscarTodosNaoAbastecidos();
		return ResponseEntity.ok().body(list);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Veiculo> update( @PathVariable Long id, @Valid @RequestBody Veiculo obj ) {
		
		Veiculo newObj = service.update(id, obj);
		return ResponseEntity.ok().body( newObj );
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Veiculo> updatePatch( @PathVariable Long id, @Valid @RequestBody Veiculo obj ) {
		
		Veiculo newObj = service.update(id, obj);
		return ResponseEntity.ok().body( newObj );
	}
	
	@ApiOperation(value = "Endpoint Salvar veiculo")
	@PostMapping(value = "/salvaVeiculo")
	public ResponseEntity<Veiculo> create ( @Valid @RequestBody Veiculo obj ) {
		
		Veiculo newObj = service.create( obj );
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
				  .buildAndExpand( newObj.getId() ).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Endpoint Deletar veiculo")
	@DeleteMapping(value = "/deletarVeiculo")
	public ResponseEntity<Void> delete( @PathVariable Long id ){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
