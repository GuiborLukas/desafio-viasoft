package com.gmail.lgsc92.service.strategy.oci;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.lgsc92.dto.EmailOciDTO;
import com.gmail.lgsc92.dto.EmailRequestDTO;
import com.gmail.lgsc92.mapper.EmailMapper;
import com.gmail.lgsc92.service.strategy.EmailIntegrationStrategy;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OciEmailIntegrationStrategy implements EmailIntegrationStrategy {

    private static final Logger logger = LoggerFactory.getLogger(OciEmailIntegrationStrategy.class);

    private final EmailMapper emailMapper;
    private final ObjectMapper objectMapper;
    private final Validator validator;

    public OciEmailIntegrationStrategy(EmailMapper emailMapper, ObjectMapper objectMapper) {
        this.emailMapper = emailMapper;
        this.objectMapper = objectMapper;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    public void sendEmail(EmailRequestDTO request) {
        try {
            EmailOciDTO ociDTO = emailMapper.toOciDTO(request);

            validateDto(ociDTO);

            String json = objectMapper.writeValueAsString(ociDTO);
            logger.info("\nEnviando e-mail via OCI (Strategy): " + json);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar e-mail via OCI", e);
        }
    }

    private void validateDto(EmailOciDTO ociDTO) {
        Set<ConstraintViolation<EmailOciDTO>> violations = validator.validate(ociDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
