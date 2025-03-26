package com.gmail.lgsc92.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gmail.lgsc92.service.strategy.EmailServiceStrategy;
import com.gmail.lgsc92.service.templatemethod.EmailServiceTemplateMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class EmailControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmailServiceStrategy emailServiceStrategy;

    @Mock
    private EmailServiceTemplateMethod emailServiceTemplateMethod;

    @InjectMocks
    private EmailController emailController;

    private final String validEmailJson = "{"
            + "\"E-mail do destinatário\":\"destinatario@example.com\","
            + "\"Nome do destinatário\":\"Destinatario Nome\","
            + "\"Email do remetente\":\"remetente@example.com\","
            + "\"Assunto\":\"Teste\","
            + "\"Conteúdo\":\"Conteúdo do e-mail\""
            + "}";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(emailController).build();
    }


    @Test
    public void testSendEmailStrategy_Success() throws Exception {
        mockMvc.perform(post("/emails/strategy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validEmailJson))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testSendEmailStrategy_InvalidInput() throws Exception {
        String invalidJson = "{"
                + "\"Nome do destinatário\":\"Destinatario Nome\","
                + "\"Email do remetente\":\"remetente@example.com\","
                + "\"Assunto\":\"Teste\","
                + "\"Conteúdo\":\"Conteúdo do e-mail\""
                + "}";
        mockMvc.perform(post("/emails/strategy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSendEmailStrategy_InternalServerError() throws Exception {
        doThrow(new RuntimeException("Erro interno")).when(emailServiceStrategy).execute(any());
        mockMvc.perform(post("/emails/strategy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validEmailJson))
                .andExpect(status().isInternalServerError());
    }


    @Test
    public void testSendEmailTemplateMethod_Success() throws Exception {
        mockMvc.perform(post("/emails/template-method")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validEmailJson))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testSendEmailTemplateMethod_InvalidInput() throws Exception {
        String invalidJson = "{"
                + "\"Nome do destinatário\":\"Destinatario Nome\","
                + "\"Email do remetente\":\"remetente@example.com\","
                + "\"Assunto\":\"Teste\","
                + "\"Conteúdo\":\"Conteúdo do e-mail\""
                + "}";
        mockMvc.perform(post("/emails/template-method")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSendEmailTemplateMethod_InternalServerError() throws Exception {
        doThrow(new RuntimeException("Erro interno")).when(emailServiceTemplateMethod).execute(any());
        mockMvc.perform(post("/emails/template-method")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validEmailJson))
                .andExpect(status().isInternalServerError());
    }
}
