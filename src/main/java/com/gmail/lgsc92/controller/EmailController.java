package com.gmail.lgsc92.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.lgsc92.dto.EmailRequestDTO;
import com.gmail.lgsc92.service.strategy.EmailServiceStrategy;
import com.gmail.lgsc92.service.templatemethod.EmailServiceTemplateMethod;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/emails")
@Tag(name = "Email API", description = "API para envio de e-mails via AWS/OCI. Documenta os endpoints e os erros possíveis.")
public class EmailController {

    private final EmailServiceStrategy emailServiceStrategy;
    private final EmailServiceTemplateMethod emailServiceTemplateMethod;

    public EmailController(EmailServiceStrategy emailServiceStrategy, EmailServiceTemplateMethod emailServiceTemplateMethod) {
        this.emailServiceStrategy = emailServiceStrategy;
        this.emailServiceTemplateMethod = emailServiceTemplateMethod;
    }

    @Operation(summary = "Enviar email via Strategy", 
               description = "Endpoint para processar o envio de e-mail utilizando o padrão Strategy.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Email enviado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/strategy")
    public ResponseEntity<Void> sendEmailStrategy(@Valid @RequestBody EmailRequestDTO request) {
        try {
            emailServiceStrategy.execute(request);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Enviar email via Template Method", 
               description = "Endpoint para processar o envio de e-mail utilizando o padrão Template Method.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Email enviado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/template-method")
    public ResponseEntity<Void> sendEmailTemplateMethod(@Valid @RequestBody EmailRequestDTO request) {
        try {
            emailServiceTemplateMethod.execute(request);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

