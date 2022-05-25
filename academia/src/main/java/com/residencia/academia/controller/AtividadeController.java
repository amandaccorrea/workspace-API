package com.residencia.academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.academia.entity.Atividade;
import com.residencia.academia.exception.NoSuchElementFoundException;
import com.residencia.academia.service.AtividadeService;


@RestController
@RequestMapping("/atividade")
public class AtividadeController {
	 @Autowired
	    private AtividadeService atividadeService;
	 @GetMapping
	    public ResponseEntity<List<Atividade>> findAll(){
	    	List<Atividade> atividadeList = atividadeService.findAll();
	        return new ResponseEntity<>(atividadeList, HttpStatus.OK);
	    }
	 
	    @GetMapping("/{id_atividade}")
		public ResponseEntity<Atividade> findById(@PathVariable(value = "id_atividade") Integer id) {
	    	Atividade atividade = atividadeService.findById(id);
			if (null == atividade) 
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			else
			return new ResponseEntity<>(atividade, HttpStatus.OK);
		}
	    
	    @PostMapping
	    public ResponseEntity<Atividade> save(@RequestBody Atividade atividade){
	    	Atividade novAtividade = atividadeService.save(atividade);
	    	return new ResponseEntity<>(novAtividade, HttpStatus.CREATED);
	    }
	    

	    @PutMapping("/{id_atividade}")
	    public ResponseEntity<Atividade> update(@RequestBody Atividade atividade) {
	    	Atividade novAtividade = atividadeService.update(atividade);
	    	return new ResponseEntity<>(novAtividade, HttpStatus.OK);
	    }
	    
	    @DeleteMapping("/{id_atividade}")
	    public ResponseEntity<String> delete(@PathVariable (value = "id_atividade") Integer idAtividade){
	    	Atividade atividade = atividadeService.findById(idAtividade);
	    	if (null == atividade) 
	    		throw new NoSuchElementFoundException("Não foi possivel deletar, pois a Atividade com o id " + idAtividade + " não foi encontrada");
	    	
	    	atividadeService.delete(idAtividade);
	    	return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
	    }
	    
}
