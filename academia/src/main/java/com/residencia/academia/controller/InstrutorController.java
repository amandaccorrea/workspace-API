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

import com.residencia.academia.dto.InstrutorDTO;
import com.residencia.academia.entity.Atividade;
import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.exception.NoSuchElementFoundException;
import com.residencia.academia.service.InstrutorService;

@RestController
@RequestMapping("/instrutor")
public class InstrutorController {
	 @Autowired
	    private InstrutorService instrutorService;

	    @GetMapping
	    public ResponseEntity<List<Instrutor>> findAll(){
	    	List<Instrutor> instrutorList = instrutorService.findAll();
	        return new ResponseEntity<>(instrutorList, HttpStatus.OK);
	    }

	    @GetMapping("/dto/{id_instrutor}")
		public ResponseEntity<InstrutorDTO> findDTOById(@PathVariable(value = "id_instrutor") Integer id) {
			InstrutorDTO instrutorDTO = instrutorService.findDTOById(id);
			if (null == instrutorDTO) 
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			else
			return new ResponseEntity<>(instrutorDTO, HttpStatus.OK);
		}
	    
	    @GetMapping("/{id_instrutor}")
		public ResponseEntity<Instrutor> findById(@PathVariable(value = "id_instrutor") Integer id) {
			//return new ResponseEntity<>(instrutorService.findInstrutorById(id), HttpStatus.OK);
			Instrutor instrutor = instrutorService.findById(id);
			if (null == instrutor) 
				throw new NoSuchElementFoundException("Não foi encontrado Instrutor com o id " + id);
			else
			return new ResponseEntity<>(instrutor, HttpStatus.OK);
		}
	    
	    @PostMapping
	    public ResponseEntity<Instrutor> save(@RequestBody Instrutor instrutor){
	    	Instrutor novoInstrutor = instrutorService.save(instrutor);
	    	return new ResponseEntity<>(novoInstrutor, HttpStatus.CREATED);
	    }
	    
	    @PostMapping("/dto")
	    public ResponseEntity<InstrutorDTO> saveDTO(@RequestBody InstrutorDTO instrutorDTO){
	    	InstrutorDTO novoInstrutorDTO = instrutorService.saveDTO(instrutorDTO);
	    	return new ResponseEntity<>(novoInstrutorDTO, HttpStatus.CREATED);
	    }
	    

	    @PutMapping("/{id_instrutor}")
	    public ResponseEntity<Instrutor> update(@RequestBody Instrutor instrutor) {
	    	Instrutor novoInstrutor = instrutorService.update(instrutor);
	    	return new ResponseEntity<>(novoInstrutor, HttpStatus.OK);
	    }
	    
	    @DeleteMapping("/{id_instrutor}")
	    public ResponseEntity<String> delete(@PathVariable (value = "id_instrutor") Integer idInstrutor){
	    	Instrutor instrutor = instrutorService.findById(idInstrutor);
	    	if (null == instrutor) 
	    		throw new NoSuchElementFoundException("Não foi possivel deletar, pois o Instrutor com o id " + idInstrutor + " não foi encontrada");
	    	
	    	instrutorService.delete(idInstrutor);
	    	return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
	    }
}
