package com.bytedance.pressRestdatabaseVersion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.bytedance.pressRestdatabaseVersion.*"})
public class PressRestDatabaseVersionApplication {

	public static void main(String[] args) {
		SpringApplication.run(PressRestDatabaseVersionApplication.class, args);
	}

}
