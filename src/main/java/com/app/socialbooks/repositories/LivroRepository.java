package com.app.socialbooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.socialbooks.domains.Livro;


public interface LivroRepository extends JpaRepository<Livro, Long>{

}
