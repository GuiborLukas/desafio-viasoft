package com.gmail.lgsc92.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmailRequestDTO(
		@NotBlank @Email @Size(max = 45) @JsonProperty("E-mail do destinatário") String destinatario,
		@NotBlank @Size(max = 60) @JsonProperty("Nome do destinatário") String nomeDestinatario,
		@NotBlank @Email @Size(max = 45) @JsonProperty("Email do remetente") String remetente,
		@NotBlank @Size(max = 120) @JsonProperty("Assunto") String assunto,
		@NotBlank @Size(max = 256) @JsonProperty("Conteúdo") String conteudo) {
}