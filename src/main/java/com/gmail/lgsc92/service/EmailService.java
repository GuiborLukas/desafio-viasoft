package com.gmail.lgsc92.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gmail.lgsc92.dto.EmailRequestDTO;
import com.gmail.lgsc92.service.aws.AwsEmailIntegrationStrategy;
import com.gmail.lgsc92.service.oci.OciEmailIntegrationStrategy;

import jakarta.annotation.PostConstruct;

@Service
public class EmailService {

    @Value("${mail.integracao}")
    private String mailIntegration;
    
    private final AwsEmailIntegrationStrategy awsStrategy;
    private final OciEmailIntegrationStrategy ociStrategy;
    private EmailIntegrationStrategy emailIntegrationStrategy;

    public EmailService(AwsEmailIntegrationStrategy awsStrategy, OciEmailIntegrationStrategy ociStrategy) {
        this.awsStrategy = awsStrategy;
        this.ociStrategy = ociStrategy;
    }

    @PostConstruct
    public void init() {
        if (mailIntegration == null) {
            throw new IllegalArgumentException("Configuração mail.integracao não definida");
        }
        switch (mailIntegration.toUpperCase()) {
            case "AWS":
                this.emailIntegrationStrategy = awsStrategy;
                break;
            case "OCI":
                this.emailIntegrationStrategy = ociStrategy;
                break;
            default:
                throw new IllegalArgumentException("Configuração inválida para mail.integracao");
        }
    }

    public void execute(EmailRequestDTO request) {
        emailIntegrationStrategy.sendEmail(request.destinatario(), request.nomeDestinatario(), request.remetente(), request.assunto(), request.assunto());
    }

}
