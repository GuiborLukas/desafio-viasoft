package com.gmail.lgsc92.service.strategy;

import com.gmail.lgsc92.dto.EmailRequestDTO;

public interface EmailIntegrationStrategy {
	void sendEmail(EmailRequestDTO request);
}
