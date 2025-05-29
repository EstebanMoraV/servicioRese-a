package com.grupo6.servicioResena.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo6.servicioResena.model.Evento;
import com.grupo6.servicioResena.model.Resena;
import com.grupo6.servicioResena.model.Usuario;
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

    public Resena findById(Integer id) {
        return resenaRepo.findById(id).orElse(null);
    }

    public List<Resena> findByUsuario(Usuario usuario) {
        return resenaRepo.findByUsuario(usuario);
    }

    public List<Resena> findByEvento(Evento eventoId) {
        return resenaRepo.findByEvento(eventoId);
    }

    public Resena save(Resena resena) {
        return resenaRepo.save(resena);
    }

    public void deleteById(Integer id) {
        resenaRepo.deleteById(id);
    }

}
