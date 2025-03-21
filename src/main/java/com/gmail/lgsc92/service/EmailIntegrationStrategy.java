package com.gmail.lgsc92.service;

public interface EmailIntegrationStrategy {
	void sendEmail(String destinatario, String nomeDestinatario, String remetente, String assunto, String conteudo);
}
