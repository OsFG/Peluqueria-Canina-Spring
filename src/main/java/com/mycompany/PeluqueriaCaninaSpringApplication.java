package com.mycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
//@ComponentScan({"com.mycompany.modelos.cita"})
public class PeluqueriaCaninaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeluqueriaCaninaSpringApplication.class, args);
	}

}
