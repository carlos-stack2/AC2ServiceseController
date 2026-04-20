package com.hospital.hospital.controller;

import com.hospital.hospital.model.Convenio;
import com.hospital.hospital.service.ConvenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/convenios")
public class ConvenioController {

    @Autowired
    private ConvenioService convenioService;

    @GetMapping
    public ResponseEntity<List<Convenio>> listarTodos() {
        return ResponseEntity.ok(convenioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Convenio> buscarPorId(@PathVariable Long id) {
        return convenioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Convenio> inserir(@RequestBody Convenio convenio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(convenioService.inserir(convenio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Convenio> atualizar(@PathVariable Long id, @RequestBody Convenio convenio) {
        try {
            return ResponseEntity.ok(convenioService.atualizar(id, convenio));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            convenioService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}