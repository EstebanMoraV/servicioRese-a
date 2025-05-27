package com.grupo6.servicioResena.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo6.servicioResena.model.Resena;
import com.grupo6.servicioResena.service.ResenaService;

@RestController
@RequestMapping("/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public ResponseEntity<List<Resena>> getAllResenas() {
        List<Resena> resenas = resenaService.findAll();
        return new ResponseEntity<>(resenas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> getResenaById(@PathVariable Integer id) {
        return resenaService.findById(id)
                .map(resena -> new ResponseEntity<>(resena, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Resena> getResenaByUsuario(@PathVariable Integer usuarioId) {
        return resenaService.findById(usuarioId)
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<Resena> getResenaByEvento(@PathVariable Integer eventoId) {
        return resenaService.findByEvento(eventoId)
                .map(resena -> new ResponseEntity<>(resena, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Resena> createResena(@RequestParam Resena resena) {
        Resena savedResena = resenaService.save(resena);
        return new ResponseEntity<>(savedResena, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resena> updateResena(@PathVariable Integer id, @RequestBody Resena resena) {
        return resenaService.findById(id)
                .map(existingResena -> {
                    resena.setId(id);
                    Resena updatedResena = resenaService.save(resena);
                    return new ResponseEntity<>(updatedResena, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResena(@PathVariable Integer id) {
        if (resenaService.findById(id).isPresent()) {
            resenaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
