package com.hospital.hospital.service;

import com.hospital.hospital.model.Medico;
import com.hospital.hospital.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> buscarPorId(Long id) {
        return medicoRepository.findById(id);
    }

    public Medico inserir(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Medico atualizar(Long id, Medico medicoAtualizado) {
        return medicoRepository.findById(id).map(medico -> {
            medico.setNome(medicoAtualizado.getNome());
            medico.setCrm(medicoAtualizado.getCrm());
            medico.setEspecialidade(medicoAtualizado.getEspecialidade());
            medico.setTelefone(medicoAtualizado.getTelefone());
            return medicoRepository.save(medico);
        }).orElseThrow(() -> new RuntimeException("Médico não encontrado com ID: " + id));
    }

    public void excluir(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new RuntimeException("Médico não encontrado com ID: " + id);
        }
        medicoRepository.deleteById(id);
    }
}