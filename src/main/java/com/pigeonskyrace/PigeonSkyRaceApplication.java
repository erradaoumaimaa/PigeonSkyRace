package com.pigeonskyrace;

import com.pigeonskyrace.model.Colombier;
import com.pigeonskyrace.repository.ColombierRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PigeonSkyRaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PigeonSkyRaceApplication.class, args);
	}

//	@Bean
	//CommandLineRunner runner(PigeonRepository pigeonRepository) {
	//return args -> {
//			System.out.println("Tentative d'insertion du pigeon...");
//			Pigeon pigeon = new Pigeon("B12345", "Femelle", 2, "Gris");
//			pigeonRepository.insert(pigeon);
//			System.out.println("Pigeon ajouté à la base de données : " + pigeon);

	//	};


//	}


	@Bean
	CommandLineRunner runner(ColombierRepository colombierRepository) {
	return args -> {
	System.out.println("Tentative d'insertion du colombier...");
	Colombier colombier = new Colombier();
		colombier.setNomColombier("Colombier-1");
	colombier.setCoordonneeGPS("48.9866, 2.3522");
	colombierRepository.insert(colombier);
	System.out.println("Colombier ajouté à la base de données : " + colombier);
		};
	}


	//public static void main(String[] args) {

//System.out.println("entre le nom");
//		Scanner scanner = new Scanner(System.in);
//	String	name=scanner.nextLine();
//	System.out.println("nombre des lettre en ce  est nom  :" +nomDistanc(name) );

//System.out.println("entere une phrase");
//Scanner sc = new Scanner(System.in);
//		String phrase = sc.nextLine();

		//	System.out.println("les nom de ce phrase contien des voyell ce sont"+nomVoyell(phrase));
		//}
		//public static String nomVoyell(String phrase) {
		//	char[] voyell= {'a', 'u', 'e', 'i', 'y'};
		//	String[] mots = phrase.split(" ");
		//if(phrase.contains(voyell)) {
		//		return mots[mots.length-1];
		//	}
//return phrase;
//	}
//	public  static Integer nomDistanc(String name){
//int count=0;
//for ( int i=0; i<name.length();i++){
		//for ( int j= i+1; j<name.length();j++){
		//	if (name.charAt(i)!=name.charAt(j) ){
		//		count++;
		//}
//	}
//return count;
//}
		//   return 0;
		// }


	}
