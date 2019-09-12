package com.app.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.socialbooks.domain.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

}
