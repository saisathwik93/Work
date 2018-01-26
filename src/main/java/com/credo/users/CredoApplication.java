package com.credo.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;


@SpringBootApplication
public class CredoApplication extends SpringBootServletInitializer{
	
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(CredoApplication.class);
	    }

    public static void main(String[] args) throws Exception{
        SpringApplication.run(CredoApplication.class, args);
    }
}

