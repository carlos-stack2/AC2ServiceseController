package com.hospital.hospital.service;

import com.hospital.hospital.model.Prontuario;
import com.hospital.hospital.repository.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public List<Prontuario> listarTodos() {
        return prontuarioRepository.findAll();
    }

    public Optional<Prontuario> buscarPorId(Long id) {
        return prontuarioRepository.findById(id);
    }

    public Prontuario inserir(Prontuario prontuario) {
        return prontuarioRepository.save(prontuario);
    }

    public Prontuario atualizar(Long id, Prontuario prontuarioAtualizado) {
        return prontuarioRepository.findById(id).map(prontuario -> {
            prontuario.setDescricao(prontuarioAtualizado.getDescricao());
            prontuario.setDiagnostico(prontuarioAtualizado.getDiagnostico());
            prontuario.setPaciente(prontuarioAtualizado.getPaciente());
            prontuario.setDataCriacao(prontuarioAtualizado.getDataCriacao());
            return prontuarioRepository.save(prontuario);
        }).orElseThrow(() -> new RuntimeException("Prontuário não encontrado com ID: " + id));
    }

    public void excluir(Long id) {
        if (!prontuarioRepository.existsById(id)) {
            throw new RuntimeException("Prontuário não encontrado com ID: " + id);
        }
        prontuarioRepository.deleteById(id);
    }
}