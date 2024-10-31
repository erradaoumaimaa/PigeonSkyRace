package com.pigeonskyrace;

import com.pigeonskyrace.model.Pigeon;
import com.pigeonskyrace.repository.PigeonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PigeonSkyRaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PigeonSkyRaceApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(PigeonRepository pigeonRepository) {
		return args -> {
//			System.out.println("Tentative d'insertion du pigeon...");
//			Pigeon pigeon = new Pigeon("B12345", "Femelle", 2, "Gris");
//			pigeonRepository.insert(pigeon);
//			System.out.println("Pigeon ajouté à la base de données : " + pigeon);
		};
	}

}
