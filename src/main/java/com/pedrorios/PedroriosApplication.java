package com.pedrorios;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PedroriosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedroriosApplication.class, args);
		log.info("Running!!!");
	}

}
