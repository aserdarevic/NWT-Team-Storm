package com.example.ratingservice;

import com.example.ratingservice.modeli.User;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.repozitorij.KorisnikRepozitorij;
import com.example.ratingservice.repozitorij.RatingRepozitorij;
import com.example.ratingservice.repozitorij.StripRepozitorij;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import static java.lang.StrictMath.ceil;
import static java.lang.StrictMath.round;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@SpringBootApplication
@EnableEurekaClient
public class RatingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingServiceApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
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
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public void run(String... args) throws Exception {
		//dobavimo usere iz user servisa
		String resourceURL = "http://user-service/user/svi";
		ResponseEntity<String> response = restTemplate.getForEntity(resourceURL, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		root.forEach(korisnik->{
			User k = new User(korisnik.path("id").asLong());
			korisnikRepozitorij.save(k);
		});
		//dobavimo stripove iz strip servisa
		String urlStripoviNaStranici = "http://comicbook-service/strip/svi";
		ObjectMapper mapperStripovi = new ObjectMapper();
		int brojStranica = 1;

		int i=0;
		while(i<brojStranica){
			ResponseEntity<String> stripoviSaStranice = restTemplate.getForEntity(urlStripoviNaStranici + "?brojStranice="+i, String.class);
			brojStranica = mapperStripovi.readTree(stripoviSaStranice.getBody()).path("brojStranica").asInt();
			JsonNode svi = mapperStripovi.readTree(stripoviSaStranice.getBody()).path("stripovi");
			List<Strip> stripovi = new ArrayList<>();
			svi.forEach(strip->{
				Strip s = new Strip(strip.path("id").asLong());
				stripovi.add(s);
			});
			stripRepozitorij.saveAll(stripovi);
			i++;
		}
		//dodamo nekoliko komentara i ocjena
		User k2 = korisnikRepozitorij.getOne(2L);
		User k3 = korisnikRepozitorij.getOne(3L);
		Strip s1 = stripRepozitorij.getOne(1L);
		Strip s2 = stripRepozitorij.getOne(2L);
		ratingRepozitorij.save(new Rating(k2,s1,1, "Užas, šta je ovo"));
		ratingRepozitorij.save(new Rating(k3,s1,1, "Ne znam šta sam pročitao"));

		ratingRepozitorij.save(new Rating(k2, s2,5, "Extrica :D"));
		ratingRepozitorij.save(new Rating(k3, s2, 1, "Meni se ovo ništa ne sviđa"));
	}
	
}


	

