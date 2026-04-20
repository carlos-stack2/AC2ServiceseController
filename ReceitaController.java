package com.hospital.hospital.controller;

import com.hospital.hospital.model.Receita;
import com.hospital.hospital.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping
    public ResponseEntity<List<Receita>> listarTodos() {
        return ResponseEntity.ok(receitaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> buscarPorId(@PathVariable Long id) {
        return receitaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Receita> inserir(@RequestBody Receita receita) {
        return ResponseEntity.status(HttpStatus.CREATED).body(receitaService.inserir(receita));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> atualizar(@PathVariable Long id, @RequestBody Receita receita) {
        try {
            return ResponseEntity.ok(receitaService.atualizar(id, receita));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            receitaService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}