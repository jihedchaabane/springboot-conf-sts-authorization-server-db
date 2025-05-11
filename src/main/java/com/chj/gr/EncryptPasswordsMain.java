/**
package com.chj.gr;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class EncryptPasswordsMain {
----+-----------------+--------------------------------------------------------------+--------------------+------------------------------
 id |    client_id    |                        client_secret                         |    grant_types     |            scopes            
----+-----------------+--------------------------------------------------------------+--------------------+------------------------------
  1 | products-client | $2a$10$9jqiljibjNsTOLHadkmbJupbJ54lnSQ0UvZcsvmkMcLbgoon0F7de | client_credentials | products.read,products.write
  2 | client1         | $2a$10$7kPsZCgc5FnK.CZFiugblO8eqOHll1WZymm9PHY4D6fMGarvnd5fC | client_credentials | client1.read,client1.write
  3 | client2         | $2a$10$w4GclNTUKApSCDnrgxkibunO2oSiq9VLyv3z8XHdl263Mdc5Wfkjq | client_credentials | client2.read,client2.write
----+-----------------+--------------------------------------------------------------+--------------------+------------------------------
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("" + encoder.encode("secret")); 	==> products-client
		System.out.println("" + encoder.encode("secret1"));	==> client1
		System.out.println("" + encoder.encode("secret2"));	==> client2
	}
}
*/

/**
CREATE TABLE IF NOT EXISTS oauth2_registered_client (
   id VARCHAR(255) NOT NULL,
   client_id VARCHAR(255) NOT NULL,
   client_secret VARCHAR(255),
   scopes TEXT NOT NULL,
   authorization_grant_types TEXT NOT NULL,
   client_authentication_methods TEXT NOT NULL,
   client_name VARCHAR(255) NOT NULL, 	    
   client_id_issued_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
   client_secret_expires_at TIMESTAMP,
   token_settings TEXT NOT NULL,
   
   redirect_uris TEXT,
   post_logout_redirect_uris TEXT,
   client_settings TEXT NOT NULL,
   PRIMARY KEY (id)
);

\dp oauth2_registered_client
GRANT ALL ON TABLE oauth2_registered_client TO postgres_data_source;

*/
/**
INSERT INTO oauth2_registered_client (
    id,
    client_id,
    client_id_issued_at,
    client_secret,
    client_name,
    client_authentication_methods,
    authorization_grant_types,
    scopes,
    client_settings,
    token_settings
) VALUES (
    '1',
    'products-client',
    CURRENT_TIMESTAMP,
    '$2a$10$9jqiljibjNsTOLHadkmbJupbJ54lnSQ0UvZcsvmkMcLbgoon0F7de', -- BCrypt pour "secret"
    'products-client-name',
    'client_secret_basic',
    'client_credentials',
    'products.read,products.write',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":"RS256","settings.token.access-token-time-to-live":"PT30M","settings.token.access-token-format":"self-contained","settings.token.refresh-token-time-to-live":"PT1H"}'
);
-------
INSERT INTO oauth2_registered_client (
    id,
    client_id,
    client_id_issued_at,
    client_secret,
    client_name,
    client_authentication_methods,
    authorization_grant_types,
    scopes,
    client_settings,
    token_settings
) VALUES (
    '2',
    'client1',
    CURRENT_TIMESTAMP,
    '$2a$10$7kPsZCgc5FnK.CZFiugblO8eqOHll1WZymm9PHY4D6fMGarvnd5fC', -- BCrypt pour "secret1"
    'client1-name',
    'client_secret_basic',
    'client_credentials',
    'client1.read,client1.write',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":"RS256","settings.token.access-token-time-to-live":"PT30M","settings.token.access-token-format":"self-contained","settings.token.refresh-token-time-to-live":"PT1H"}'
);
-------
INSERT INTO oauth2_registered_client (
    id,
    client_id,
    client_id_issued_at,
    client_secret,
    client_name,
    client_authentication_methods,
    authorization_grant_types,
    scopes,
    client_settings,
    token_settings
) VALUES (
    '3',
    'client2',
    CURRENT_TIMESTAMP,
    '$2a$10$w4GclNTUKApSCDnrgxkibunO2oSiq9VLyv3z8XHdl263Mdc5Wfkjq', -- BCrypt pour "secret2"
    'client2-name',
    'client_secret_basic',
    'client_credentials',
    'client2.read,client2.write',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":"RS256","settings.token.access-token-time-to-live":"PT30M","settings.token.access-token-format":"self-contained","settings.token.refresh-token-time-to-live":"PT1H"}'
);
*/
/**
<spring-security-oauth2-authorization-server.version>0.2.0</spring-security-oauth2-authorization-server.version>
<spring-security-oauth2-authorization-server.version>1.2.0</spring-security-oauth2-authorization-server.version>

UPDATE oauth2_registered_client
SET token_settings = '{
  "@class": "java.util.Collections$UnmodifiableMap",
  "settings.token.reuse-refresh-tokens": true,
  "settings.token.id-token-signature-algorithm": "RS256",
  "settings.token.access-token-time-to-live": ["java.time.Duration", 1800.000000000],
  "settings.token.access-token-format": "self-contained",
  "settings.token.refresh-token-time-to-live": ["java.time.Duration", 3600.000000000]
}';
==> La valeur 1800.000000000 correspond à 30 minutes (1800 secondes).
==> Ajustez selon les besoins (par exemple, 3600.000000000 pour 1 heure).
*/

/**
public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
    JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);

    // Insérer un client dans la base de données (optionnel, si vous voulez un client par défaut)
    RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("client-id")
            .clientSecret(passwordEncoder().encode("secret")) // Encoder le mot de passe
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
            .scope("read")
            .scope("write")
            .tokenSettings(TokenSettings.builder()
                    .accessTokenTimeToLive(Duration.ofMinutes(30)) // Durée d'expiration de 30 minutes
                    .build())
            .build();
    // Vérifier si le client existe déjà avant de l'insérer
    if (registeredClientRepository.findByClientId("client-id") == null) {
        registeredClientRepository.save(registeredClient);
    }
    return registeredClientRepository;
}
*/