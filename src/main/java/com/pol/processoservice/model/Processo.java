package com.pol.processoservice.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "processo", uniqueConstraints = @UniqueConstraint(columnNames = "numero_processo"))
public class Processo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_processo", nullable = false)
    private String numeroProcesso;

    @OneToMany(mappedBy = "processo", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Reu> reus;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public List<Reu> getReus() {
        return reus;
    }

    public void setReus(List<Reu> reus) {
        this.reus = reus;
    }
}
