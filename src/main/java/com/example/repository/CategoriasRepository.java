package com.example.repository;

import com.example.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriasRepository extends CrudRepository<Categoria, Integer> {
}
