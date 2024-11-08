package com.pigeonskyrace;

import com.pigeonskyrace.model.User;
import com.pigeonskyrace.model.enums.Role;
import com.pigeonskyrace.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.bson.types.ObjectId;

@SpringBootApplication(scanBasePackages = "com.pigeonskyrace")
public class PigeonSkyRaceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PigeonSkyRaceApplication.class, args);
	}
}
