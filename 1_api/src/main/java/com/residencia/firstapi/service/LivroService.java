package com.residencia.firstapi.service;

import com.residencia.firstapi.entity.Livro;
import com.residencia.firstapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> findAll(){
        return livroRepository.findAll();
    }
    
    public Livro findById(Integer id){
        return livroRepository.findById(id).get();
    }
    
    public Livro save(Livro livro){
        return livroRepository.save(livro);
    }
    
    public Livro update(Livro livro, Integer id){
        return livroRepository.save(livro);
    }
    
    public void delete(Integer id){
        Livro livro = livroRepository.findById(id).get();
        livroRepository.delete(livro);
    }
}
