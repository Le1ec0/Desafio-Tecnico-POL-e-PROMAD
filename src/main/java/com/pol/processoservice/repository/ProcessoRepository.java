package com.pol.processoservice.repository;

import com.pol.processoservice.model.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    boolean existsByNumeroProcesso(String numeroProcesso);
}
