package com.grupo6.servicioResena.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo6.servicioResena.model.Resena;
import com.grupo6.servicioResena.repository.ResenaRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ResenaService {

    @Autowired
    private ResenaRepo resenaRepo;

    public List<Resena> findAll() {
        return resenaRepo.findAll();
    }

    public Optional<Resena> findById(Integer id) {
        return resenaRepo.findById(id);
    }

    public Optional<Resena> findByUsuario(Integer usuarioId) {
        return resenaRepo.findById(usuarioId);
    }

    public Optional<Resena> findByEvento(Integer eventoId) {
        return resenaRepo.findById(eventoId);
    }

    public Resena save(Resena resena) {
        return resenaRepo.save(resena);
    }

    public void deleteById(Integer id) {
        resenaRepo.deleteById(id);
    }

}
