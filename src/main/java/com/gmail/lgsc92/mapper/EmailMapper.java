package com.gmail.lgsc92.mapper;

import org.springframework.stereotype.Component;

import com.gmail.lgsc92.dto.EmailAwsDTO;
import com.gmail.lgsc92.dto.EmailOciDTO;
import com.gmail.lgsc92.dto.EmailRequestDTO;

@Component
public class EmailMapper {
	
    public EmailAwsDTO toAwsDTO(EmailRequestDTO dto) {
        return new EmailAwsDTO(dto.destinatario(), dto.nomeDestinatario(), dto.remetente(), dto.assunto(), dto.conteudo());
    }

    public EmailOciDTO toOciDTO(EmailRequestDTO dto) {
        return new EmailOciDTO(dto.destinatario(), dto.nomeDestinatario(), dto.remetente(), dto.assunto(), dto.conteudo());
    }
}
