package com.pigeonskyrace;

import com.pigeonskyrace.model.User;
import com.pigeonskyrace.model.enums.Role;
import com.pigeonskyrace.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.bson.types.ObjectId;

@SpringBootApplication
public class PigeonSkyRaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PigeonSkyRaceApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner(UserRepository userRepository) {
//		return args -> {
//			System.out.println("Tentative d'insertion de l'utilisateur...");
//
//			User user = new User()
//					.setName("test")
//					.setEmail("test1@gmail.com")
//					.setPassword("password1234")
//					.setRole(Role.ELEVEUR);
//
//			userRepository.save(user);
//
//			System.out.println("Utilisateur ajouté à la base de données : " + user);
//		};
//	}
}
