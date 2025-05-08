package com.chj.gr.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import com.chj.gr.model.Client;
import com.chj.gr.repository.ClientRepository;

@Service
public class CustomClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    public CustomClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public RegisteredClient findById(String id) {
        return clientRepository.findById(Long.valueOf(id))
                .map(this::mapToRegisteredClient)
                .orElse(null);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Optional<Client> client = clientRepository.findByClientId(clientId);
        return client.map(this::mapToRegisteredClient).orElse(null);
    }

    private RegisteredClient mapToRegisteredClient(Client client) {
        return RegisteredClient.withId(client.getId().toString())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantTypes(grants -> 
                    Arrays.stream(client.getGrantTypes().split(" "))
                          .forEach(grant -> grants.add(new AuthorizationGrantType(grant))))
                .scopes(scopes -> 
                    Arrays.stream(client.getScopes().split(" "))
                          .forEach(scopes::add))
                .build();
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        throw new UnsupportedOperationException("Client registration not supported at runtime.");
    }
}

