package com.gmail.lgsc92.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.lgsc92.dto.EmailRequestDTO;
import com.gmail.lgsc92.service.EmailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/emails")
@Tag(name = "Email API", description = "API para envio de e-mails via AWS/OCI")
public class EmailController {

	private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @Operation(summary = "Enviar email", description = "Endpoint para processar o envio de e-mail conforme a configuração (AWS ou OCI)")
    @PostMapping
    public ResponseEntity<Void> sendEmail(@Valid @RequestBody EmailRequestDTO request) {
        try {
            emailService.execute(request);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}