package com.deliverytech.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(authorities = {"ROLE_CLIENTE"})
public class ClienteControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void deveCriarClienteComSucesso() throws Exception {
        String json = "{\"nome\":\"Rafael\",\"email\":\"rafael@teste.com\"}";

        mockMvc.perform(post("/api/clientes")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void naoDeveCriarClienteComCpfInvalido() throws Exception {
        String json = "{\"nome\":\"Rafael\",\"cpf\":\"000\"}";

        mockMvc.perform(post("/api/clientes")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void naoDeveCriarClienteComNomeEmBranco() throws Exception {
        String json = "{\"nome\":\"\",\"email\":\"rafael.quaquer@teste.com\"}";
        mockMvc.perform(post("/api/clientes")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isBadRequest());
    }
}