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
import org.springframework.web.bind.annotation.RestController;

import com.grupo6.servicioResena.model.Evento;
import com.grupo6.servicioResena.model.Resena;
import com.grupo6.servicioResena.model.Usuario;
import com.grupo6.servicioResena.service.ResenaService;

@RestController
@RequestMapping("/api/v1/resenas")
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
        Resena resena = resenaService.findById(id);
        if (resena != null) {
            return new ResponseEntity<>(resena, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Resena>> getResenasByUsuario(@PathVariable Integer usuarioId) {
        List<Resena> resenas = resenaService.findByUsuario(new Usuario(usuarioId, null, null, null, null, null));
        if (resenas != null && !resenas.isEmpty()) {
            return new ResponseEntity<>(resenas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<Resena>> getResenasByEvento(@PathVariable Integer eventoId) {
        List<Resena> resenas = resenaService.findByEvento(new Evento(eventoId, null, null, null, null, null, null, null));
        if (resenas != null && !resenas.isEmpty()) {
            return new ResponseEntity<>(resenas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Resena> createResena(@RequestBody Resena resena) {
        Resena createdResena = resenaService.save(resena);
        return new ResponseEntity<>(createdResena, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resena> updateResena(@PathVariable Integer id, @RequestBody Resena resena) {
        Resena existingResena = resenaService.findById(id);
        if (existingResena != null) {
            resena.setId(id); // Ensure the ID is set for the update
            Resena updatedResena = resenaService.save(resena);
            return new ResponseEntity<>(updatedResena, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResena(@PathVariable Integer id) {
        Resena existingResena = resenaService.findById(id);
        if (existingResena != null) {
            resenaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
