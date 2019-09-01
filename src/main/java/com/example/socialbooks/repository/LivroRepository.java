package com.example.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.socialbooks.domain.Livro;


public interface LivroRepository extends JpaRepository<Livro, Long>{

}
