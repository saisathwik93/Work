package com.credoop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ldap.core.support.LdapContextSource;

@SpringBootApplication
public class CredoOperationsApplication {
	@Value("${ldap.url}")
	private String ldapURL;

	@Value("${ldap.userdn}")
	private String ldapUserDn;

	@Value("${ldap.password}")
	private String ldapPassword;
	
	
public static void main(String[] args) {
		SpringApplication.run(CredoOperationsApplication.class, args);
	}

@Bean
	public LdapContextSource ldapContextSource() {
		LdapContextSource source = new LdapContextSource();
		source.setUrl(ldapURL);
		source.setUserDn(ldapUserDn);
		source.setPassword(ldapPassword);

		return source;
	}
	}
