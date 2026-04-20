package com.hospital.hospital.service;

import com.hospital.hospital.model.Paciente;
import com.hospital.hospital.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente inserir(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente atualizar(Long id, Paciente pacienteAtualizado) {
        return pacienteRepository.findById(id).map(paciente -> {
            paciente.setNome(pacienteAtualizado.getNome());
            paciente.setCpf(pacienteAtualizado.getCpf());
            paciente.setDataNascimento(pacienteAtualizado.getDataNascimento());
            paciente.setTelefone(pacienteAtualizado.getTelefone());
            paciente.setEndereco(pacienteAtualizado.getEndereco());
            return pacienteRepository.save(paciente);
        }).orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID: " + id));
    }

    public void excluir(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado com ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }
}