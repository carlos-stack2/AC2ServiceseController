package com.hospital.hospital.service;

import com.hospital.hospital.model.Receita;
import com.hospital.hospital.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    public List<Receita> listarTodos() {
        return receitaRepository.findAll();
    }

    public Optional<Receita> buscarPorId(Long id) {
        return receitaRepository.findById(id);
    }

    public Receita inserir(Receita receita) {
        return receitaRepository.save(receita);
    }

    public Receita atualizar(Long id, Receita receitaAtualizada) {
        return receitaRepository.findById(id).map(receita -> {
            receita.setMedicamento(receitaAtualizada.getMedicamento());
            receita.setPosologia(receitaAtualizada.getPosologia());
            receita.setConsulta(receitaAtualizada.getConsulta());
            receita.setDataEmissao(receitaAtualizada.getDataEmissao());
            return receitaRepository.save(receita);
        }).orElseThrow(() -> new RuntimeException("Receita não encontrada com ID: " + id));
    }

    public void excluir(Long id) {
        if (!receitaRepository.existsById(id)) {
            throw new RuntimeException("Receita não encontrada com ID: " + id);
        }
        receitaRepository.deleteById(id);
    }
}