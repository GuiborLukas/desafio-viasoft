package com.gmail.lgsc92.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmailAwsDTO(

    @NotBlank
    @Email
    @Size(max = 45)
    @JsonProperty("recipient")
    String recipient,

    @NotBlank
    @Size(max = 60)
    @JsonProperty("recipientName")
    String recipientName,

    @NotBlank
    @Email
    @Size(max = 45)
    @JsonProperty("sender")
    String sender,

    @NotBlank
    @Size(max = 120)
    @JsonProperty("subject")
    String subject,

    @NotBlank
    @Size(max = 256)
    @JsonProperty("content")
    String content
) {}
