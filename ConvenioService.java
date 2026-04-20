package com.hospital.hospital.service;

import com.hospital.hospital.model.Convenio;
import com.hospital.hospital.repository.ConvenioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConvenioService {

    @Autowired
    private ConvenioRepository convenioRepository;

    public List<Convenio> listarTodos() {
        return convenioRepository.findAll();
    }

    public Optional<Convenio> buscarPorId(Long id) {
        return convenioRepository.findById(id);
    }

    public Convenio inserir(Convenio convenio) {
        return convenioRepository.save(convenio);
    }

    public Convenio atualizar(Long id, Convenio convenioAtualizado) {
        return convenioRepository.findById(id).map(convenio -> {
            convenio.setNome(convenioAtualizado.getNome());
            convenio.setNumeroRegistro(convenioAtualizado.getNumeroRegistro());
            convenio.setTelefone(convenioAtualizado.getTelefone());
            return convenioRepository.save(convenio);
        }).orElseThrow(() -> new RuntimeException("Convênio não encontrado com ID: " + id));
    }

    public void excluir(Long id) {
        if (!convenioRepository.existsById(id)) {
            throw new RuntimeException("Convênio não encontrado com ID: " + id);
        }
        convenioRepository.deleteById(id);
    }
}