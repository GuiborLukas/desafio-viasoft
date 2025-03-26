package com.gmail.lgsc92.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmailOciDTO(

        @NotBlank 
        @Email 
        @Size(max = 40) 
        @Schema(description = "Email do destinatário", example = "destinatario@oci.com")
        @JsonProperty("recipientEmail") String recipientEmail,

        @NotBlank 
        @Size(max = 50) 
        @Schema(description = "Nome do destinatário", example = "Maria Oliveira")
        @JsonProperty("recipientName") String recipientName,

        @NotBlank 
        @Email 
        @Size(max = 40) 
        @Schema(description = "Email do remetente", example = "remetente@oci.com")
        @JsonProperty("senderEmail") String senderEmail,

        @NotBlank 
        @Size(max = 100) 
        @Schema(description = "Assunto", example = "Assunto do e-mail para OCI")
        @JsonProperty("subject") String subject,

        @NotBlank 
        @Size(max = 250) 
        @Schema(description = "Conteúdo do e-mail", example = "Este é o corpo do e-mail com conteúdo sobre OCI.")
        @JsonProperty("body") String body) {
}
