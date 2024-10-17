package com.pol.processoservice.service;

import com.pol.processoservice.model.Reu;
import com.pol.processoservice.repository.ReuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReuService {

    @Autowired
    private ReuRepository reuRepository;

    public Reu adicionarReu(Reu reu) {
        return reuRepository.save(reu); // Salva o réu no banco de dados
    }
}

