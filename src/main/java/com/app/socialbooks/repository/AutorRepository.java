package com.app.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.socialbooks.domain.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
