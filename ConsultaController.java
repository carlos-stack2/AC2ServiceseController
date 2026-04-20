package com.hospital.hospital.controller;

import com.hospital.hospital.model.Consulta;
import com.hospital.hospital.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<List<Consulta>> listarTodos() {
        return ResponseEntity.ok(consultaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Long id) {
        return consultaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Consulta> inserir(@RequestBody Consulta consulta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.inserir(consulta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizar(@PathVariable Long id, @RequestBody Consulta consulta) {
        try {
            return ResponseEntity.ok(consultaService.atualizar(id, consulta));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            consultaService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}