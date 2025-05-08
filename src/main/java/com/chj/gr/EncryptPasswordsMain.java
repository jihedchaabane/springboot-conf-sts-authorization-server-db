/**
package com.chj.gr;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptPasswordsMain {
----+-----------------+--------------------------------------------------------------+--------------------+------------------------------
 id |    client_id    |                        client_secret                         |    grant_types     |            scopes            
----+-----------------+--------------------------------------------------------------+--------------------+------------------------------
  1 | products-client | $2a$10$9jqiljibjNsTOLHadkmbJupbJ54lnSQ0UvZcsvmkMcLbgoon0F7de | client_credentials | products.read products.write
  2 | client1         | $2a$10$7kPsZCgc5FnK.CZFiugblO8eqOHll1WZymm9PHY4D6fMGarvnd5fC | client_credentials | read write
  3 | client2         | $2a$10$w4GclNTUKApSCDnrgxkibunO2oSiq9VLyv3z8XHdl263Mdc5Wfkjq | client_credentials | read
----+-----------------+--------------------------------------------------------------+--------------------+------------------------------

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("" + encoder.encode("secret")); 	==> products-client
		System.out.println("" + encoder.encode("secret1"));	==> client1
		System.out.println("" + encoder.encode("secret2"));	==> client2

	}
}
*/