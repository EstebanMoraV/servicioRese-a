package com.grupo6.servicioResena.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo6.servicioResena.model.Evento;
import com.grupo6.servicioResena.model.Resena;
import com.grupo6.servicioResena.model.Usuario;

@Repository
public interface ResenaRepo extends JpaRepository<Resena, Integer>{
    @SuppressWarnings("override")
    Optional<Resena> findById(Integer id);

    Optional<Resena> findByUsuario(Usuario usuario);

    Optional<Resena> findByEvento(Evento evento);
    
}
