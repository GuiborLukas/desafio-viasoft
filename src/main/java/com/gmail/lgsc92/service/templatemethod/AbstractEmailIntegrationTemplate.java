package com.gmail.lgsc92.service.templatemethod;

import com.gmail.lgsc92.dto.EmailRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractEmailIntegrationTemplate {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private final ObjectMapper objectMapper;

    public AbstractEmailIntegrationTemplate(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public final void sendEmail(EmailRequestDTO request) {
        try {
            Object emailDTO = convertRequestToDTO(request);

            validateDTO(emailDTO);

            String json = objectMapper.writeValueAsString(emailDTO);
            processEmail(json);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar e-mail via " + getIntegrationName(), e);
        }
    }

    protected abstract void validateDTO(Object emailDTO);

    protected abstract Object convertRequestToDTO(EmailRequestDTO request);

    protected abstract void processEmail(String json);

    public abstract String getIntegrationName();
}
