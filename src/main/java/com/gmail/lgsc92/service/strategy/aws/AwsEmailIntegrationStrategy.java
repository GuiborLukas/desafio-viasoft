package com.gmail.lgsc92.service.strategy.aws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.lgsc92.dto.EmailAwsDTO;
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
public class AwsEmailIntegrationStrategy implements EmailIntegrationStrategy {

    private static final Logger logger = LoggerFactory.getLogger(AwsEmailIntegrationStrategy.class);

    private final EmailMapper emailMapper;
    private final ObjectMapper objectMapper;
    private final Validator validator;

    public AwsEmailIntegrationStrategy(EmailMapper emailMapper, ObjectMapper objectMapper) {
        this.emailMapper = emailMapper;
        this.objectMapper = objectMapper;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    public void sendEmail(EmailRequestDTO request) {
        try {
            EmailAwsDTO awsDTO = emailMapper.toAwsDTO(request);

            validateDto(awsDTO);

            String json = objectMapper.writeValueAsString(awsDTO);
            logger.info("\nEnviando e-mail via AWS (Strategy): " + json);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar e-mail via AWS", e);
        }
    }

    private void validateDto(EmailAwsDTO awsDTO) {
        Set<ConstraintViolation<EmailAwsDTO>> violations = validator.validate(awsDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
