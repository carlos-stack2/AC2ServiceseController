package com.hospital.hospital.service;

import com.hospital.hospital.model.Consulta;
import com.hospital.hospital.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> listarTodos() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> buscarPorId(Long id) {
        return consultaRepository.findById(id);
    }

    public Consulta inserir(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public Consulta atualizar(Long id, Consulta consultaAtualizada) {
        return consultaRepository.findById(id).map(consulta -> {
            consulta.setDataHora(consultaAtualizada.getDataHora());
            consulta.setMedico(consultaAtualizada.getMedico());
            consulta.setPaciente(consultaAtualizada.getPaciente());
            consulta.setObservacoes(consultaAtualizada.getObservacoes());
            return consultaRepository.save(consulta);
        }).orElseThrow(() -> new RuntimeException("Consulta não encontrada com ID: " + id));
    }

    public void excluir(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new RuntimeException("Consulta não encontrada com ID: " + id);
        }
        consultaRepository.deleteById(id);
    }
}