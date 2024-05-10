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

import com.logustecnologia.appPostoCombustivel.model.Posto;
import com.logustecnologia.appPostoCombustivel.services.PostoService;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

/**
 * @author Petronilo Padilha
 * email: nilopadilha@gmail.com
 * Telefone: 84 99498-4982
 *
 */
@RestController
@RequestMapping(value = "/api/v1/postos")
public class PostoController {
	@Autowired
	private PostoService service;

	@ApiOperation(value = "Endpoint pesquisar posto por id")
	@GetMapping("/pesquisar/{id}")
	public ResponseEntity<Posto> findById(@PathVariable Long id) {

		Posto obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
		
	@ApiOperation(value = "Endpoint listar todos postos")
	@GetMapping(value = "/listarTodos")
	public ResponseEntity<List<Posto>> findAll() {

		List<Posto> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	

	@PutMapping(value = "/{id}")
	public ResponseEntity<Posto> update(@PathVariable Long id, @Valid @RequestBody Posto posto) {

		Posto objPosto = service.update(id, posto);
		return ResponseEntity.ok().body(objPosto);
	}

	
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Posto> updatePatch(@PathVariable Long id, @Valid @RequestBody Posto posto) {

		Posto objPosto = service.update(id, posto);
		return ResponseEntity.ok().body(objPosto);
	}
	
	
	@ApiOperation(value = "Endpoint Salvar Postos")
	@PostMapping(value = "/salvaPosto")
	public ResponseEntity<Posto> create(@Valid @RequestBody Posto posto) {

		Posto objPosto = service.create(posto);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(objPosto.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	
	@ApiOperation(value = "Endpoint Deletar Postos")
	@DeleteMapping(value = "/deletarPosto")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
