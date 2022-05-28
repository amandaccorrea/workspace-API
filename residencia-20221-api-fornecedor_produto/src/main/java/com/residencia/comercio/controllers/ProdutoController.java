package com.residencia.comercio.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.comercio.dtos.ProdutoDTO;
import com.residencia.comercio.entities.Produto;
import com.residencia.comercio.exceptions.NoSuchElementFoundException;
import com.residencia.comercio.services.ProdutoService;

@RestController
@Validated
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAllProduto() {
		List<Produto> produtoList = produtoService.findAllProduto();
		if(produtoList.isEmpty())
			throw new NoSuchElementFoundException("Não foram encontrados Produtos");
		else
		return new ResponseEntity<>(produtoService.findAllProduto(), HttpStatus.OK);
	}

	@GetMapping("/dto/{id}")
	public ResponseEntity<ProdutoDTO> findProdutoDTOById(@PathVariable Integer id) {
		ProdutoDTO produtoDTO = produtoService.findProdutoDTOById(id);
		return new ResponseEntity<>(produtoDTO, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findProdutoById(@PathVariable Integer id) {
		Produto produto = produtoService.findProdutoById(id);
		if(null == produto)
			throw new NoSuchElementFoundException("Não foi encontrado Produto com o id " + id);
		else
			return new ResponseEntity<>(produto, HttpStatus.OK);
	}

	@GetMapping("/id")
	public ResponseEntity<Produto> findProdutoByIdRequest(@RequestParam @NotBlank Integer id) {
		Produto produto = produtoService.findProdutoById(id);
		if(null == produto)
			throw new NoSuchElementFoundException("Não foi encontrado Produto com o id " + id);
		else
			return new ResponseEntity<>(produto, HttpStatus.OK);
	}
	
	@PostMapping("/completo")
	public ResponseEntity<Produto> saveProdutoCompleto(@Valid @RequestBody Produto produto) {
		Produto novoProduto = produtoService.saveProduto(produto);
		return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
	}
	
	@PostMapping("/dto")
	public ResponseEntity<ProdutoDTO> saveProdutoDTO(@Valid @RequestBody ProdutoDTO produtoDTO) {
		ProdutoDTO novoProdutoDTO = produtoService.saveProdutoDTO(produtoDTO);
		return new ResponseEntity<>(novoProdutoDTO, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Produto> updateProduto(@Valid @RequestBody Produto produto) {
		Produto produtoAtualizado =produtoService.updateProduto(produto);
		return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> updateProduto(@PathVariable Integer id, @RequestBody Produto produto) {
		Produto produtoAtualizado = produtoService.updateProdutoId(produto, id);
		if(null == produtoAtualizado)
			return new ResponseEntity<>(produtoAtualizado,HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProdutoId(Integer id) {
		if(null == produtoService.findProdutoById(id))
			return new ResponseEntity<>("Não foi possivel deletar o produto, porque não foi encontrado nenhum produdo com esse id", HttpStatus.NOT_FOUND);
		
		produtoService.deleteProdutoId(id);
		return new ResponseEntity<>("Produto deletado com sucesso", HttpStatus.OK);
	}
	/*@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduto(Produto produto){
		produtoService.deleteProduto(produto);
		return new ResponseEntity<>("Produto deletado com sucesso", HttpStatus.OK);
	}*/
	


}
