package com.residencia.firstapi.controller;

import com.residencia.firstapi.entity.Editora;
import com.residencia.firstapi.service.EditoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editora")
public class EditoraController {
    @Autowired
    private EditoraService editoraService;

    @GetMapping
    public ResponseEntity<List<Editora>> findAll(){
        return ResponseEntity.ok().body(editoraService.findAll());
    }

    @GetMapping("/{id}")
    public Editora findById(@PathVariable(value = "id") Integer id){
        return editoraService.findById(id);
    }

    @PostMapping
    public Editora save(@RequestBody Editora editora){
        return editoraService.save(editora);
    }

    @PutMapping("/{id}")
    public Editora update(@PathVariable Integer editoraId, @RequestBody Editora editora) {
        return editoraService.update(editora, editoraId);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer editoraId){
        editoraService.delete(editoraId);
    }
}