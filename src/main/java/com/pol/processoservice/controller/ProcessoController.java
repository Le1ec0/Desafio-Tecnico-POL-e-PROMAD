package com.pol.processoservice.controller;

import com.pol.processoservice.model.Processo;
import com.pol.processoservice.model.Reu;
import com.pol.processoservice.service.ProcessoService;
import com.pol.processoservice.service.ReuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/processos")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private ReuService reuService;

    // Endpoint para listar todos os processos
    @GetMapping("/Listar")
    public List<Processo> listarProcessos() {
        return processoService.listarProcessos();
    }

    // Endpoint para criar um novo processo
    @PostMapping("/Criar")
    public ResponseEntity<String> criarProcesso(@RequestParam String numero_processo) {
        Processo processo = new Processo();
        processo.setNumeroProcesso(numero_processo);
        try {
            // Tente salvar o processo. Se já existir, a exceção será lançada.
            processoService.salvarProcesso(processo);
            // Retorna a mensagem de sucesso ao invés de apenas o status
            return ResponseEntity.status(HttpStatus.CREATED).body("Processo criado com sucesso");
        } catch (RuntimeException e) {
            // Retorne a mensagem específica se o processo já existir
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Já existe um processo cadastrado com esse número.");
        }
    }

    // Endpoint para deletar um processo pelo número do processo
    @DeleteMapping("/Deletar")
    public ResponseEntity<String> deletarProcesso(@RequestParam String numero_processo) {
        Processo processo = processoService.listarProcessos().stream()
                .filter(p -> p.getNumeroProcesso().equals(numero_processo))
                .findFirst()
                .orElse(null);

        if (processo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Processo não encontrado.");
        }

        processoService.deletarProcesso(processo.getId());
        return ResponseEntity.ok("Processo deletado com sucesso.");
    }

    // Endpoint para inserir um réu associado a um processo
    @PatchMapping("/InserirReu")
    public ResponseEntity<Reu> adicionarReu(@RequestParam String numero_processo, @RequestParam String nome) {
        // Obtém o processo pelo número
        Processo processo = processoService.listarProcessos().stream()
                .filter(p -> p.getNumeroProcesso().equals(numero_processo))
                .findFirst()
                .orElse(null);

        // Verifica se o processo foi encontrado
        if (processo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Cria o novo réu e associa ao processo
        Reu reu = new Reu();
        reu.setNome(nome);
        reu.setProcesso(processo);

        // Adiciona o réu à lista de réus do processo
        List<Reu> reus = processo.getReus();
        if (reus != null) {
            reus.add(reu);
        } else {
            reus = new ArrayList<>();
            reus.add(reu);
        }
        processo.setReus(reus); // Atualiza a lista de réus no processo

        // Salva o réu no repositório
        Reu savedReu = reuService.adicionarReu(reu); // Salva o réu no banco
        return new ResponseEntity<>(savedReu, HttpStatus.OK);
    }
}
