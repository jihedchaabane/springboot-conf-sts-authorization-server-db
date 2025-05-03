package com.chj.gr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chj.gr.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByClientId(String clientId);
}

