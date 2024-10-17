package com.pol.processoservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pol.processoservice.model.Processo;
import com.pol.processoservice.repository.ProcessoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProcessoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        processoRepository.deleteAll();
    }

    @Test
    public void testCriarProcesso() throws Exception {
        Processo processo = new Processo();
        processo.setNumeroProcesso("123456789");

        mockMvc.perform(post("/processos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(processo)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCriarProcessoJaCadastrado() throws Exception {
        Processo processo = new Processo();
        processo.setNumeroProcesso("123456789");

        mockMvc.perform(post("/processos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(processo)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/processos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(processo)))
                .andExpect(status().isConflict());
    }

    @Test
    public void testDeletarProcesso() throws Exception {
        Processo processo = new Processo();
        processo.setNumeroProcesso("123456789");

        mockMvc.perform(post("/processos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(processo)))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/processos/1"))
                .andExpect(status().isNoContent());
    }
}
