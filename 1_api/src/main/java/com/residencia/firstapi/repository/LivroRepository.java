package com.residencia.firstapi.repository;

import com.residencia.firstapi.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
}