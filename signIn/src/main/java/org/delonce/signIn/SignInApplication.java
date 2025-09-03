package org.delonce.signIn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SignInApplication {

	public static void main(String[] args) {
		SpringApplication.run(SignInApplication.class, args);
	}

}
