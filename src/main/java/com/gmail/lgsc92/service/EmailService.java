package com.gmail.lgsc92.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.lgsc92.dto.EmailRequestDTO;
import com.gmail.lgsc92.mapper.EmailMapper;

@Service
public class EmailService {
	@Value("${mail.integracao}")
	private String mailIntegration;

	private final EmailMapper mapper;
	private final ObjectMapper objectMapper;

	public EmailService(EmailMapper mapper, ObjectMapper objectMapper) {
		this.mapper = mapper;
		this.objectMapper = objectMapper;
	}

	public void processEmail(EmailRequestDTO dto) {
		try {
			String json;
			if ("AWS".equalsIgnoreCase(mailIntegration)) {
				json = objectMapper.writeValueAsString(mapper.toAwsDTO(dto));
			} else if ("OCI".equalsIgnoreCase(mailIntegration)) {
				json = objectMapper.writeValueAsString(mapper.toOciDTO(dto));
			} else {
				throw new IllegalArgumentException("Configuração inválida para mail.integracao");
			}
			System.out.println("Serialized JSON " + mailIntegration + ": " + json);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao processar e-mail", e);
		}
	}
}
