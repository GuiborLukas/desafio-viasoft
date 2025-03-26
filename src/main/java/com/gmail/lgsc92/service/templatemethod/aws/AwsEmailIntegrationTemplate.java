package com.gmail.lgsc92.service.templatemethod.aws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.lgsc92.dto.EmailRequestDTO;
import com.gmail.lgsc92.dto.EmailAwsDTO;
import com.gmail.lgsc92.mapper.EmailMapper;
import com.gmail.lgsc92.service.templatemethod.AbstractEmailIntegrationTemplate;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AwsEmailIntegrationTemplate extends AbstractEmailIntegrationTemplate {

    private final EmailMapper emailMapper;
    private final Validator validator;

    public AwsEmailIntegrationTemplate(EmailMapper emailMapper, ObjectMapper objectMapper) {
        super(objectMapper);
        this.emailMapper = emailMapper;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    protected void validateDTO(Object emailDTO) {
        if (emailDTO instanceof EmailAwsDTO) {
            EmailAwsDTO awsDTO = (EmailAwsDTO) emailDTO;
            Set<ConstraintViolation<EmailAwsDTO>> violations = validator.validate(awsDTO);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException("Erro de validação no DTO do AWS: ", violations);
            }
        } else {
            throw new IllegalArgumentException("Tipo de DTO inválido: " + emailDTO.getClass().getName());
        }
    }

    @Override
    protected Object convertRequestToDTO(EmailRequestDTO request) {
        return emailMapper.toAwsDTO(request);
    }

    @Override
    protected void processEmail(String json) {
        logger.info("Enviando e-mail via AWS (Template Method): " + json);
    }

    @Override
    public String getIntegrationName() {
        return "AWS";
    }
}
