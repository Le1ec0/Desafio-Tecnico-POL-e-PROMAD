package com.pol.processoservice.service;

import com.pol.processoservice.model.Processo;
import com.pol.processoservice.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    public Processo salvarProcesso(Processo processo) {
        if (processoRepository.existsByNumeroProcesso(processo.getNumeroProcesso())) {
            throw new RuntimeException("Processo já cadastrado.");
        }
        return processoRepository.save(processo);
    }

    public List<Processo> listarProcessos() {
        return processoRepository.findAll(); // Aqui, os réus serão carregados automaticamente
    }

    public void deletarProcesso(Long id) {
        processoRepository.deleteById(id);
    }
}
