package com.rremiao.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rremiao.security.execution.Instance;

@SpringBootApplication
public class SecurityApplication implements CommandLineRunner {
	
	@Value("${file.path}")
	private String file;

	@Autowired
	Instance instance;

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		instance.run(file);
	}

}
