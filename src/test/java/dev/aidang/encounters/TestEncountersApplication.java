package dev.aidang.encounters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestEncountersApplication {

	public static void main(String[] args) {
		SpringApplication.from(EncountersApplication::main).with(TestEncountersApplication.class).run(args);
	}

}
