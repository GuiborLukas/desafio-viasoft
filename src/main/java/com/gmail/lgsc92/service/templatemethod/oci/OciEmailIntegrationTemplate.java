package com.gmail.lgsc92.service.templatemethod.oci;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.lgsc92.dto.EmailRequestDTO;
import com.gmail.lgsc92.dto.EmailOciDTO;
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
public class OciEmailIntegrationTemplate extends AbstractEmailIntegrationTemplate {

    private final EmailMapper emailMapper;
    private final Validator validator;

    public OciEmailIntegrationTemplate(EmailMapper emailMapper, ObjectMapper objectMapper) {
        super(objectMapper);
        this.emailMapper = emailMapper;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    protected void validateDTO(Object emailDTO) {
        if (emailDTO instanceof EmailOciDTO) {
            EmailOciDTO ociDTO = (EmailOciDTO) emailDTO;
            Set<ConstraintViolation<EmailOciDTO>> violations = validator.validate(ociDTO);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException("Erro de validação no DTO do OCI: ", violations);
            }
        } else {
            throw new IllegalArgumentException("Tipo de DTO inválido: " + emailDTO.getClass().getName());
        }
    }

    @Override
    protected Object convertRequestToDTO(EmailRequestDTO request) {
        return emailMapper.toOciDTO(request);
    }

    @Override
    protected void processEmail(String json) {
        logger.info("Enviando e-mail via OCI (Template Method): " + json);
    }

    @Override
    public String getIntegrationName() {
        return "OCI";
    }
}
