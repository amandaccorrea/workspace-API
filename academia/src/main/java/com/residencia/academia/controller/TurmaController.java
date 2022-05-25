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

import com.residencia.academia.dto.TurmaDTO;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.exception.NoSuchElementFoundException;
import com.residencia.academia.service.TurmaService;

@RestController
@RequestMapping("/turma")
public class TurmaController {
	 @Autowired
	    private TurmaService turmaService;

	    @GetMapping
	    public ResponseEntity<List<Turma>> findAll(){
	        return ResponseEntity.ok().body(turmaService.findAll());
	    }

	    @GetMapping("/dto/{id_turma}")
		public ResponseEntity<TurmaDTO> findDTOById(@PathVariable(value = "id_turma") Integer id) {
	    	TurmaDTO turmaDTO = turmaService.findDTOById(id);
			if (null == turmaDTO) 
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			else
			return new ResponseEntity<>(turmaDTO, HttpStatus.OK);
		}
	    
	    @GetMapping("/{id_turma}")
	    public ResponseEntity<Turma> findById(@PathVariable(value = "id_turma") Integer id){ 
	    	Turma turma = turmaService.findById(id);
			if(null == turma)
				throw new NoSuchElementFoundException("Não foi encontrada Turma com o id " + id);
			else
				return new ResponseEntity<>(turmaService.findById(id), HttpStatus.OK);
		}
	 
	    @PostMapping
	    public ResponseEntity<Turma> save(@RequestBody Turma turma){
			return new ResponseEntity<>(turmaService.save(turma), HttpStatus.CREATED);
	    }
	    
	    @PostMapping("/dto")
	    public ResponseEntity<TurmaDTO> saveDTO(@RequestBody TurmaDTO turmaDTO){
	    	TurmaDTO novaTurmaDTO = turmaService.saveDTO(turmaDTO);
	    	return new ResponseEntity<>(novaTurmaDTO, HttpStatus.CREATED);
	    }

	    @PutMapping("/{id_turma}")
	    public ResponseEntity<Turma> update(@RequestBody Turma turma) {
			return new ResponseEntity<>(turmaService.update(turma), HttpStatus.OK);
	    }
	    
	    @DeleteMapping("/{id_turma}")
	    public ResponseEntity<String> delete(@PathVariable (value = "id_turma") Integer idTurma){
	    	Turma turma = turmaService.findById(idTurma);
	    	if (null == turma) 
	    		throw new NoSuchElementFoundException("Não foi possivel deletar, pois a Turma com o id " + idTurma + " não foi encontrada");
	    	
	    	turmaService.delete(idTurma);
	    	return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
	    }
	    /*@DeleteMapping("/delete/{id_turma}")
	    public ResponseEntity<String> delete(@PathVariable (value = "id_turma") Integer idTurma){
	    	Boolean verificacao = turmaService.delete(idTurma);
	    	if (verificacao) 
	    		 return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
	    		
	    	else
	    		throw new NoSuchElementFoundException("Não foi possivel deletar, pois a Turma com o id " + idTurma + " não foi encontrada");
	    }*/

}
