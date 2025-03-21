package com.gmail.lgsc92.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmailOciDTO(

    @NotBlank
    @Email
    @Size(max = 40)
    @JsonProperty("recipientEmail")
    String recipientEmail,

    @NotBlank
    @Size(max = 50)
    @JsonProperty("recipientName")
    String recipientName,

    @NotBlank
    @Email
    @Size(max = 40)
    @JsonProperty("senderEmail")
    String senderEmail,

    @NotBlank
    @Size(max = 100)
    @JsonProperty("subject")
    String subject,

    @NotBlank
    @Size(max = 250)
    @JsonProperty("body")
    String body
) {}
