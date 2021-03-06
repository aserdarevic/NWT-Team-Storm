package com.example.ratingservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserKontrolerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void all() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/korisnici")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void userById () throws Exception {
		//sve okej
		mockMvc.perform( MockMvcRequestBuilders
				.get("/korisnik/{id}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
		//korisnik ne postoji
		mockMvc.perform( MockMvcRequestBuilders
				.get("/korisnik/{id}", 1132)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$.message").value("Korisnik sa id 1132 nije pronadjen!"));
	}

	@Test
	public void save() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/dodaj-korisnika")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

}
