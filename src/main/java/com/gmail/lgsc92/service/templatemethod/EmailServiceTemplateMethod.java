package com.gmail.lgsc92.service.templatemethod;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.gmail.lgsc92.dto.EmailRequestDTO;
import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
public class EmailServiceTemplateMethod {
    
    @Value("${mail.integracao}")
    private String mailIntegration;
    
    private final List<AbstractEmailIntegrationTemplate> templates;
    private AbstractEmailIntegrationTemplate emailIntegrationTemplate;
    
    public EmailServiceTemplateMethod(List<AbstractEmailIntegrationTemplate> templates) {
        this.templates = templates;
    }
    
    @PostConstruct
    public void init() {
        emailIntegrationTemplate = templates.stream()
                .filter(template -> template.getIntegrationName().equalsIgnoreCase(mailIntegration))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Configuração inválida para mail.integracao: " + mailIntegration));
    }
    
    public void execute(EmailRequestDTO request) {
        emailIntegrationTemplate.sendEmail(request);
    }
}
