CREATE TABLE IF NOT EXISTS oauth2_registered_client (
   id VARCHAR(255) NOT NULL,
   client_id VARCHAR(255) NOT NULL,
   client_secret VARCHAR(255),
   scopes TEXT NOT NULL,
   authorization_grant_types TEXT NOT NULL,
   client_authentication_methods TEXT NOT NULL,
   client_name VARCHAR(255) NOT NULL, 	    
   client_id_issued_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
   token_settings TEXT NOT NULL,
   client_settings TEXT NOT NULL,
   
   client_secret_expires_at TIMESTAMP,
   redirect_uris TEXT,
   post_logout_redirect_uris TEXT,
   PRIMARY KEY (id)
);

\dp oauth2_registered_client
GRANT ALL ON TABLE oauth2_registered_client TO postgres_data_source;

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
    '1',
    'products-client',
    CURRENT_TIMESTAMP,
    '$2a$10$9jqiljibjNsTOLHadkmbJupbJ54lnSQ0UvZcsvmkMcLbgoon0F7de', -- BCrypt pour "secret"
    'products-client-name',
    'client_secret_basic',
    'client_credentials',
    'products.read,products.write',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}',
    '{
		  "@class": "java.util.Collections$UnmodifiableMap",
		  "settings.token.reuse-refresh-tokens": true,
		  "settings.token.id-token-signature-algorithm": "RS256",
		  "settings.token.access-token-time-to-live": ["java.time.Duration", 1800.000000000],
		  "settings.token.access-token-format": "self-contained",
		  "settings.token.refresh-token-time-to-live": ["java.time.Duration", 3600.000000000]
		}'
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
    'client1.read,client1.write,client2.read,client2.write',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}',
    '{
		  "@class": "java.util.Collections$UnmodifiableMap",
		  "settings.token.reuse-refresh-tokens": true,
		  "settings.token.id-token-signature-algorithm": "RS256",
		  "settings.token.access-token-time-to-live": ["java.time.Duration", 1800.000000000],
		  "settings.token.access-token-format": "self-contained",
		  "settings.token.refresh-token-time-to-live": ["java.time.Duration", 3600.000000000]
		}'
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
    '{
		  "@class": "java.util.Collections$UnmodifiableMap",
		  "settings.token.reuse-refresh-tokens": true,
		  "settings.token.id-token-signature-algorithm": "RS256",
		  "settings.token.access-token-time-to-live": ["java.time.Duration", 1800.000000000],
		  "settings.token.access-token-format": "self-contained",
		  "settings.token.refresh-token-time-to-live": ["java.time.Duration", 3600.000000000]
		}'
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
    '4',
    'gr-ms1-resource',
    CURRENT_TIMESTAMP,
    '$2a$10$DknUlal8WNAMOM8JN2EAcuaqKLsvUNIovApYiP5rfwmOZI...VhI6', -- BCrypt pour "ms1-resource"
    'gr-ms1-resource--name',
    'client_secret_basic',
    'client_credentials',
    'ms1.read,ms2.read',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}',
    '{
	  "@class": "java.util.Collections$UnmodifiableMap",
	  "settings.token.reuse-refresh-tokens": true,
	  "settings.token.id-token-signature-algorithm": "RS256",
	  "settings.token.access-token-time-to-live": ["java.time.Duration", 1800.000000000],
	  "settings.token.access-token-format": "self-contained",
	  "settings.token.refresh-token-time-to-live": ["java.time.Duration", 3600.000000000]
	}'
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
    '5',
    'gr-ms2-resource',
    CURRENT_TIMESTAMP,
    '$2a$10$39cfG2df23kkU4.RJGi33ua5E0IR/gEpWxfCL/LcwBwcNZ9XHJpWm', -- BCrypt pour "ms2-resource"
    'gr-ms2-resource--name',
    'client_secret_basic',
    'client_credentials',
    'ms2.read',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}',
    '{
	  "@class": "java.util.Collections$UnmodifiableMap",
	  "settings.token.reuse-refresh-tokens": true,
	  "settings.token.id-token-signature-algorithm": "RS256",
	  "settings.token.access-token-time-to-live": ["java.time.Duration", 1800.000000000],
	  "settings.token.access-token-format": "self-contained",
	  "settings.token.refresh-token-time-to-live": ["java.time.Duration", 3600.000000000]
	}'
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
    '6',
    'gr-ms3-resource',
    CURRENT_TIMESTAMP,
    '$2a$10$VNqId.GQaTNZDCVou25ItunFldfKKcShq2FTJJ5axHLcwJ.R7z006', -- BCrypt pour "ms3-resource"
    'gr-ms3-resource--name',
    'client_secret_basic',
    'client_credentials',
    'ms3.read,actuator.read',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}',
    '{
	  "@class": "java.util.Collections$UnmodifiableMap",
	  "settings.token.reuse-refresh-tokens": true,
	  "settings.token.id-token-signature-algorithm": "RS256",
	  "settings.token.access-token-time-to-live": ["java.time.Duration", 1800.000000000],
	  "settings.token.access-token-format": "self-contained",
	  "settings.token.refresh-token-time-to-live": ["java.time.Duration", 3600.000000000]
	}'
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
    '7',
    'gr-resource-consumer-resttemplate',
    CURRENT_TIMESTAMP,
    '$2a$10$4j99NgcYLpuaSaCMGXHE2OloCBJU5NepUU.9mjHSnTJSEraRLq9Iu', -- BCrypt pour "consumer-resttemplate"
    'gr-resource-consumer-resttemplate--name',
    'client_secret_basic',
    'client_credentials',
    'ms1.read,ms2.read,ms3.read',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}',
    '{
	  "@class": "java.util.Collections$UnmodifiableMap",
	  "settings.token.reuse-refresh-tokens": true,
	  "settings.token.id-token-signature-algorithm": "RS256",
	  "settings.token.access-token-time-to-live": ["java.time.Duration", 1800.000000000],
	  "settings.token.access-token-format": "self-contained",
	  "settings.token.refresh-token-time-to-live": ["java.time.Duration", 3600.000000000]
	}'
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
    '8',
    'gr-resource-consumer-webclient',
    CURRENT_TIMESTAMP,
    '$2a$10$sgqG0vq0gDBKuigIjA7VuOS207YTN/7OVcE/r6/3OPmxJKdvWsOdC', -- BCrypt pour "consumer-webclient"
    'gr-resource-consumer-webclient--name',
    'client_secret_basic',
    'client_credentials',
    'ms1.read,ms2.read,ms3.read',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}',
    '{
	  "@class": "java.util.Collections$UnmodifiableMap",
	  "settings.token.reuse-refresh-tokens": true,
	  "settings.token.id-token-signature-algorithm": "RS256",
	  "settings.token.access-token-time-to-live": ["java.time.Duration", 1800.000000000],
	  "settings.token.access-token-format": "self-contained",
	  "settings.token.refresh-token-time-to-live": ["java.time.Duration", 3600.000000000]
	}'
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
    '9',
    'gr-resource-consumer-feign',
    CURRENT_TIMESTAMP,
    '$2a$10$ckxROl.W7kZ/mIV4W1XCQecPsRreWT.Kn2MdfzMjfqpahDIJ9fVKu', -- BCrypt pour "consumer-feign"
    'gr-resource-consumer-feign--name',
    'client_secret_basic',
    'client_credentials',
    'ms1.read,ms2.read,ms3.read',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}',
    '{
	  "@class": "java.util.Collections$UnmodifiableMap",
	  "settings.token.reuse-refresh-tokens": true,
	  "settings.token.id-token-signature-algorithm": "RS256",
	  "settings.token.access-token-time-to-live": ["java.time.Duration", 1800.000000000],
	  "settings.token.access-token-format": "self-contained",
	  "settings.token.refresh-token-time-to-live": ["java.time.Duration", 3600.000000000]
	}'
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
    '10',
    'gr-oauth2-swagger-ms1',
    CURRENT_TIMESTAMP,
    '$2a$10$Im.5um3ZMJiCEts6vPtxAOA2XlSOfikHjtXEBN55wp6q.dUD9enkC', -- BCrypt pour "swagger-ms1"
    'gr-oauth2-swagger-ms1--name',
    'client_secret_basic',
    'client_credentials',
    'read,write',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}',
    '{
	  "@class": "java.util.Collections$UnmodifiableMap",
	  "settings.token.reuse-refresh-tokens": true,
	  "settings.token.id-token-signature-algorithm": "RS256",
	  "settings.token.access-token-time-to-live": ["java.time.Duration", 1800.000000000],
	  "settings.token.access-token-format": "self-contained",
	  "settings.token.refresh-token-time-to-live": ["java.time.Duration", 3600.000000000]
	}'
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
    '11',
    'gr-oauth2-swagger-ms2',
    CURRENT_TIMESTAMP,
    '$2a$10$aWLny8W3WhUe6dUnVthhSeyCbvAE952WeNzM58BGaqKHIYlgMKaXS', -- BCrypt pour "swagger-ms2"
    'gr-oauth2-swagger-ms2--name',
    'client_secret_basic',
    'client_credentials',
    'update,remove',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}',
    '{
	  "@class": "java.util.Collections$UnmodifiableMap",
	  "settings.token.reuse-refresh-tokens": true,
	  "settings.token.id-token-signature-algorithm": "RS256",
	  "settings.token.access-token-time-to-live": ["java.time.Duration", 1800.000000000],
	  "settings.token.access-token-format": "self-contained",
	  "settings.token.refresh-token-time-to-live": ["java.time.Duration", 3600.000000000]
	}'
);

select id, client_id, scopes, client_name
from oauth2_registered_client
order by id;

 id |             client_id             |                                       scopes                                       |               client_name
----+-----------------------------------+------------------------------------------------------------------------------------+-----------------------------------------
 1  | products-client                   | products.read,products.write,client1.read,client1.write,client2.read,client2.write | products-client-name
 10 | gr-oauth2-swagger-ms1             | read,write                                                                         | gr-oauth2-swagger-ms1--name
 11 | gr-oauth2-swagger-ms2             | update,remove                                                                      | gr-oauth2-swagger-ms2--name
 2  | client1                           | client1.read,client1.write,client2.read,client2.write                              | client1-name
 3  | client2                           | client2.read,client2.write                                                         | client2-name
 4  | gr-ms1-resource                   | ms1.read,ms2.read                                                                  | gr-ms1-resource--name
 5  | gr-ms2-resource                   | ms2.read                                                                           | gr-ms2-resource--name
 6  | gr-ms6-resource                   | ms3.read,actuator.read                                                             | gr-ms3-resource--name
 7  | gr-resource-consumer-resttemplate | ms1.read,ms2.read,ms3.read                                                         | gr-resource-consumer-resttemplate--name
 8  | gr-resource-consumer-webclient    | ms1.read,ms2.read,ms3.read                                                         | gr-resource-consumer-webclient--name
 9  | gr-resource-consumer-feign        | ms1.read,ms2.read,ms3.read                                                         | gr-resource-consumer-feign--name
(11 lignes)


gr-ms1-resource:ms1-resource=> $2a$10$DknUlal8WNAMOM8JN2EAcuaqKLsvUNIovApYiP5rfwmOZI...VhI6
.scope("ms1.read")
.scope("ms2.read")

gr-ms2-resource:ms2-resource=> $2a$10$39cfG2df23kkU4.RJGi33ua5E0IR/gEpWxfCL/LcwBwcNZ9XHJpWm
.scope("ms2.read")

gr-ms3-resource:ms3-resource=> $2a$10$VNqId.GQaTNZDCVou25ItunFldfKKcShq2FTJJ5axHLcwJ.R7z006
.scope("ms3.read")
.scope("actuator.read")

gr-resource-consumer-resttemplate:consumer-resttemplate	=> $2a$10$4j99NgcYLpuaSaCMGXHE2OloCBJU5NepUU.9mjHSnTJSEraRLq9Iu
.scope("ms1.read")
.scope("ms2.read")
.scope("ms3.read")

gr-resource-consumer-webclient:consumer-webclient		=> $2a$10$sgqG0vq0gDBKuigIjA7VuOS207YTN/7OVcE/r6/3OPmxJKdvWsOdC
.scope("ms1.read")
.scope("ms2.read")
.scope("ms3.read")

gr-resource-consumer-feign:consumer-feign				=> $2a$10$ckxROl.W7kZ/mIV4W1XCQecPsRreWT.Kn2MdfzMjfqpahDIJ9fVKu
.scope("ms1.read")
.scope("ms2.read")
.scope("ms3.read")

gr-oauth2-swagger-ms1:swagger-ms1	=> $2a$10$Im.5um3ZMJiCEts6vPtxAOA2XlSOfikHjtXEBN55wp6q.dUD9enkC
.scope("read")
.scope("write")

gr-oauth2-swagger-ms2:swagger-ms2	=> $2a$10$aWLny8W3WhUe6dUnVthhSeyCbvAE952WeNzM58BGaqKHIYlgMKaXS
.scope("update")
.scope("remove")
