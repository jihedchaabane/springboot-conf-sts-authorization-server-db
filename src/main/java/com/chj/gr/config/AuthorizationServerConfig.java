package com.chj.gr.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.chj.gr.config.properties.ServiceParamsProperties;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfig {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Bean
	@Primary
	public JdbcRegisteredClientRepository jdbcRegisteredClientRepository() {
		return new JdbcRegisteredClientRepository(jdbcTemplate);
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	/**
     * Securing OPTIONS /oauth2/token indique que le serveur reçoit une requête OPTIONS, qui est une requête CORS preflight envoyée
     * par le navigateur (via Swagger UI) avant la requête POST.
     * 
     * Swagger UI, exécuté dans le navigateur (sur http://localhost:8081 ou http://localhost:8082), envoie une requête CORS vers http://localhost:8764/oauth2/token. 
     * 
     * Si le serveur d'autorisation ne retourne pas les en-têtes CORS appropriés (comme Access-Control-Allow-Origin), 
     * 		le navigateur rejette la réponse, entraînant une erreur 403.
     * 
     * Spring Security ou le serveur d'autorisation peut ne pas gérer correctement les requêtes OPTIONS, qui sont nécessaires pour les requêtes CORS:
     * 		donc, ci dessous la configuration correspondante.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        /**
         *  WORKS FINE.
         */
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowCredentials(false); // Nécessaire pour le motif "*"
        /** */
        /**
         * WORKS FINE TOO.
         */
//        configuration.setAllowedOriginPatterns(Arrays.asList("[*]"));
//        configuration.setAllowCredentials(false); // Nécessaire pour le motif "[*]"
        /** */
        /**
         * WORKS FINE TOO.
         */
//        configuration.setAllowedOrigins(Arrays.asList(
//        		"http://localhost:8081", 	// swagger Authorize : gr-oauth2-swagger-ms1
//        		"http://localhost:8082",	// swagger Authorize : gr-oauth2-swagger-ms2
//        		etc ...
//        		"http://localhost:8766" 	// swagger Authorize : gr-conf-swagger-aggregator
//        ));
//        configuration.setAllowCredentials(true);
        /** */
        /**
         * WORKS FINE TOO.
         */
//        configuration.setAllowedOriginPatterns(Arrays.asList("http://localhost:*"));
//        configuration.setAllowCredentials(true); // Nécessaire pour le motif "http://localhost:*"
        /** */
        
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
	@Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.sendError(400, "Authentication error: " + authException.getMessage());
                        }))
                .build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    private static RSAKey generateRsa() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

	@Autowired
	private ServiceParamsProperties serviceParamsProperties;
    
    @Bean
    public ProviderSettings providerSettings() {
        return ProviderSettings.builder()
              /**
        		.issuer("http://localhost:8764")
        		@TODO try to replace it with eureka discovery alternative.	
				.issuer("http://SPRINGBOOT-CONF-STS-AUTHORIZATION-SERVER-DB")
               */
        		.issuer(this.serviceParamsProperties.getOauth2().getIssuerUri())
        		.tokenEndpoint("/oauth2/token")
                .build();
    }
}