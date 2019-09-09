package com.app.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.socialbooks.domain.Livro;


public interface LivroRepository extends JpaRepository<Livro, Long>{

}
