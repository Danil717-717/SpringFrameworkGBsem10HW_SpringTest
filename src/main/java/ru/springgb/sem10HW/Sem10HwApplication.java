package ru.springgb.sem10HW;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class Sem10HwApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sem10HwApplication.class, args);
	}

}
