package com.adb.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = "com.adb.web.app.*")
@EnableConfigurationProperties()
public class AdbWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdbWebApplication.class, args);
	}

}
