package com.hospital.hospital.controller;

import com.hospital.hospital.model.Medico;
import com.hospital.hospital.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> listarTodos() {
        return ResponseEntity.ok(medicoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarPorId(@PathVariable Long id) {
        return medicoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Medico> inserir(@RequestBody Medico medico) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.inserir(medico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizar(@PathVariable Long id, @RequestBody Medico medico) {
        try {
            return ResponseEntity.ok(medicoService.atualizar(id, medico));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            medicoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}