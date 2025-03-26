package com.gmail.lgsc92.service.strategy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gmail.lgsc92.dto.EmailRequestDTO;
import com.gmail.lgsc92.service.strategy.aws.AwsEmailIntegrationStrategy;
import com.gmail.lgsc92.service.strategy.oci.OciEmailIntegrationStrategy;

import jakarta.annotation.PostConstruct;

@Service
public class EmailServiceStrategy {

    @Value("${mail.integracao}")
    private String mailIntegration;
    
    private final AwsEmailIntegrationStrategy awsStrategy;
    private final OciEmailIntegrationStrategy ociStrategy;
    private EmailIntegrationStrategy emailIntegrationStrategy;

    public EmailServiceStrategy(AwsEmailIntegrationStrategy awsStrategy, OciEmailIntegrationStrategy ociStrategy) {
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
                throw new IllegalArgumentException("Configuração inválida para mail.integracao: " + mailIntegration);
        }
    }

    public void execute(EmailRequestDTO request) {
        emailIntegrationStrategy.sendEmail(request);
    }

}
