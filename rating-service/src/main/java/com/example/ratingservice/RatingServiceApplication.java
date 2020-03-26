package com.example.ratingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import com.example.ratingservice.modeli.Korisnik;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.repozitorij.KorisnikRepozitorij;
import com.example.ratingservice.repozitorij.RatingRepozitorij;
import com.example.ratingservice.repozitorij.StripRepozitorij;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableAutoConfiguration
public class RatingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingServiceApplication.class, args);
	}
}

@Component
class DemoCommandLineRunner implements CommandLineRunner{
	
	@Autowired
	private RatingRepozitorij ratingRepozitorij;
	@Autowired
	private KorisnikRepozitorij korisnikRepozitorij;
	@Autowired
	private StripRepozitorij stripRepozitorij;
	
	@Override
	public void run(String... args) throws Exception {
		
		//unosi u tabelu
		Korisnik k1=new Korisnik();
		
		Strip s1=new Strip("skriveno lice",0,0);
		Strip s2=new Strip("magicni vjetar",0,0);
		
		k1.setBroj_losih_reviewa(1);
		k1.setUkupno_reviewa(2);
		
		s1.setUkupno_komentara(1);
		s2.setUkupno_komentara(1);
		
		stripRepozitorij.save(s1);
		stripRepozitorij.save(s2);
		korisnikRepozitorij.save(k1);
		ratingRepozitorij.save(new Rating(k1,s1,4, "super strip"));
		ratingRepozitorij.save(new Rating(k1,s2,1, "nisam odusevljen"));
	
		
	}
	
}


	

