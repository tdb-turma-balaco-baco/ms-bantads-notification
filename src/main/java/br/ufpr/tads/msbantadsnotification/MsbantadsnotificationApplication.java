package br.ufpr.tads.msbantadsnotification;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class MsbantadsnotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsbantadsnotificationApplication.class, args);
	}

}
