package com.gmail.lgsc92.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRequestDTO(
        @NotBlank 
        @Email 
        @Schema(description = "E-mail do destinatário", example = "destinatario@exemplo.com")
        @JsonProperty("E-mail do destinatário") String destinatario,

        @NotBlank 
        @Schema(description = "Nome do destinatário", example = "João Silva")
        @JsonProperty("Nome do destinatário") String nomeDestinatario,

        @NotBlank 
        @Schema(description = "Email do remetente", example = "remetente@exemplo.com")
        @JsonProperty("Email do remetente") String remetente,

        @NotBlank 
        @Schema(description = "Assunto", example = "Assunto do e-mail")
        @JsonProperty("Assunto") String assunto,

        @NotBlank 
        @Schema(description = "Conteúdo", example = "Este é o conteúdo do e-mail.")
        @JsonProperty("Conteúdo") String conteudo) {
}

