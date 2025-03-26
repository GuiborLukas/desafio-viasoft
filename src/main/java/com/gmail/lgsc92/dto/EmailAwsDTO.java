package com.gmail.lgsc92.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmailAwsDTO(

        @NotBlank 
        @Email 
        @Size(max = 45) 
        @Schema(description = "E-mail do destinatário", example = "destinatario@aws.com")
        @JsonProperty("recipient") String recipient,

        @NotBlank 
        @Size(max = 60) 
        @Schema(description = "Nome do destinatário", example = "Carlos Santos")
        @JsonProperty("recipientName") String recipientName,

        @NotBlank 
        @Email 
        @Size(max = 45) 
        @Schema(description = "E-mail do remetente", example = "remetente@aws.com")
        @JsonProperty("sender") String sender,

        @NotBlank 
        @Size(max = 120) 
        @Schema(description = "Assunto", example = "Assunto do e-mail AWS")
        @JsonProperty("subject") String subject,

        @NotBlank 
        @Size(max = 256) 
        @Schema(description = "Conteúdo do e-mail", example = "Este é o conteúdo do e-mail que será enviado via AWS.")
        @JsonProperty("content") String content) {
}
