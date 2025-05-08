package com.chj.gr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomClientService implements RegisteredClientRepository {

	@Autowired
	private JdbcRegisteredClientRepository jdbcRegisteredClientRepository;

    @Override
    public RegisteredClient findById(String id) {
        return jdbcRegisteredClientRepository.findById(id);
    }

    @Override
	public RegisteredClient findByClientId(String clientId) {
		return jdbcRegisteredClientRepository.findByClientId(clientId);
	}

    @Override
    public void save(RegisteredClient registeredClient) {
        throw new UnsupportedOperationException("Client registration not supported at runtime.");
    }
}

