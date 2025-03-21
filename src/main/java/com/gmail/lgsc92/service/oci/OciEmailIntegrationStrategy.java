package com.gmail.lgsc92.service.oci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.lgsc92.dto.EmailOciDTO;
import com.gmail.lgsc92.service.EmailIntegrationStrategy;

@Component
public class OciEmailIntegrationStrategy implements EmailIntegrationStrategy {
	
	private static final Logger logger = LoggerFactory.getLogger(OciEmailIntegrationStrategy.class);

    private final ObjectMapper objectMapper;

    public OciEmailIntegrationStrategy(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendEmail(String destinatario, String nomeDestinatario, String remetente, String assunto, String conteudo) {
        try {
            EmailOciDTO ociDTO = new EmailOciDTO(destinatario, nomeDestinatario, remetente, assunto, conteudo);
            String json = objectMapper.writeValueAsString(ociDTO);
            logger.info("\nEnviando e-mail via OCI: " + json);
        } catch (Exception e) {
        	logger.error("Erro ao enviar e-mail via OCI", e);
            throw new RuntimeException("Erro ao enviar e-mail via OCI", e);
        }
    }
}