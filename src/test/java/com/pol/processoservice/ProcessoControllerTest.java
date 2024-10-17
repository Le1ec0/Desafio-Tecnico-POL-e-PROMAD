package com.pol.processoservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pol.processoservice.model.Processo;
import com.pol.processoservice.model.Reu;
import com.pol.processoservice.repository.ProcessoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // Garante o rollback após cada teste
public class ProcessoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        processoRepository.deleteAll(); // Limpa o repositório antes de cada teste
    }

    // Teste para criar um processo
    @Test
    public void testCriarProcesso() throws Exception {
        mockMvc.perform(post("/processos/Criar")
                        .param("numero_processo", "123456789"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Processo criado com sucesso")));
    }

    // Teste para listar todos os processos
    @Test
    public void testListarProcessos() throws Exception {
        // Primeiro, crie dois processos
        mockMvc.perform(post("/processos/Criar")
                        .param("numero_processo", "123456789"))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/processos/Criar")
                        .param("numero_processo", "987654321"))
                .andExpect(status().isCreated());

        // Teste o endpoint de listagem
        mockMvc.perform(get("/processos/Listar"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("123456789")))
                .andExpect(content().string(containsString("987654321")));
    }

    // Teste para deletar um processo pelo número
    @Test
    public void testDeletarProcesso() throws Exception {
        // Primeiro, crie um processo
        mockMvc.perform(post("/processos/Criar")
                        .param("numero_processo", "123456789"))
                .andExpect(status().isCreated());

        // Teste deletar o processo criado
        mockMvc.perform(delete("/processos/Deletar")
                        .param("numero_processo", "123456789"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Processo deletado com sucesso.")));
    }

    // Teste para adicionar um réu a um processo
    @Test
    @Transactional
    public void testAdicionarReu() throws Exception {
        mockMvc.perform(patch("/processos/InserirReu")
                        .param("numero_processo", "123456789")
                        .param("nome", "João Doe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("João Doe")));
    }
}