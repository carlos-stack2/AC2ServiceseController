package com.hospital.hospital.controller;

import com.hospital.hospital.model.Prontuario;
import com.hospital.hospital.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    @GetMapping
    public ResponseEntity<List<Prontuario>> listarTodos() {
        return ResponseEntity.ok(prontuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prontuario> buscarPorId(@PathVariable Long id) {
        return prontuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Prontuario> inserir(@RequestBody Prontuario prontuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prontuarioService.inserir(prontuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prontuario> atualizar(@PathVariable Long id, @RequestBody Prontuario prontuario) {
        try {
            return ResponseEntity.ok(prontuarioService.atualizar(id, prontuario));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            prontuarioService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}