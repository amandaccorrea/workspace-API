package com.residencia.comercio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.comercio.dtos.EstoqueProdutoDTO;
import com.residencia.comercio.entities.Estoque;

import com.residencia.comercio.exceptions.NoSuchElementFoundException;
import com.residencia.comercio.services.EstoqueService;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
	@Autowired
	private EstoqueService estoqueService;
	
	@GetMapping
	public ResponseEntity<List<Estoque>> findAllEstoque() {
	List<Estoque> estoqueList = estoqueService.findAllEstoque();
	return new ResponseEntity<>(estoqueList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estoque> findEstoqueById(@PathVariable Integer id) {
		Estoque estoque = estoqueService.findEstoqueById(id);
		if(null == estoque)
			throw new NoSuchElementFoundException("Não foi encontrado Estoque com o id " + id);
		else
			return new ResponseEntity<>(estoque, HttpStatus.OK);
	}
	
	/*@GetMapping("/estoque-produto")
	public ResponseEntity<List<Estoque>> findEstoqueProduto() {
		List<EstoqueProdutoDTO> listEstProd = estoqueService.findEstoqueProduto();
		if(!listEstProd.isEmpty())
		return new ResponseEntity<>(listEstProd, HttpStatus.OK);
		else
			throw new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}*/
}
