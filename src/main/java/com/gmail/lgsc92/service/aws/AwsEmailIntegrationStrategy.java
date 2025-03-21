package com.gmail.lgsc92.service.aws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.lgsc92.dto.EmailAwsDTO;
import com.gmail.lgsc92.service.EmailIntegrationStrategy;

@Component
public class AwsEmailIntegrationStrategy implements EmailIntegrationStrategy {
	
	private static final Logger logger = LoggerFactory.getLogger(AwsEmailIntegrationStrategy.class);

    private final ObjectMapper objectMapper;

    public AwsEmailIntegrationStrategy(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendEmail(String destinatario, String nomeDestinatario, String remetente, String assunto, String conteudo) {
        try {
            EmailAwsDTO awsDTO = new EmailAwsDTO(destinatario, nomeDestinatario, remetente, assunto, conteudo);
            String json = objectMapper.writeValueAsString(awsDTO);
            logger.info("\nEnviando e-mail via AWS: {}", json);
        } catch (Exception e) {
            logger.error("Erro ao enviar e-mail via AWS", e);
            throw new RuntimeException("Erro ao enviar e-mail via AWS", e);
        }
    }
}
