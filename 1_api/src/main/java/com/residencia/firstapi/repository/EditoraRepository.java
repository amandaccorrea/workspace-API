package com.residencia.firstapi.repository;

import com.residencia.firstapi.entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditoraRepository extends JpaRepository<Editora, Integer> {
}