package com.example.talkversity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TalkversityApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void loginTest() throws Exception {
		mockMvc.perform(formLogin("/login").user("Anas").password("asd"))
				.andExpect(unauthenticated());
	}

	@Test
	void loginTest2() throws Exception {
		mockMvc.perform(formLogin("/login").user("admin").password("1234567"))
				.andExpect(authenticated());
	}
	@Test
	void signUpTest() throws Exception {
		mockMvc.perform(post("/signup")
						.param("firstname","ahmad")
						.param("midname","ali")
						.param("username","ahmad")
						.param("password","1234")
						.param("lastname","alsayed")
						.param("dateOfBirth", String.valueOf(new Date()))
						.param("gender","male")
						.param("faculty","Engineering")

				)
				.andExpect(redirectedUrl("/home")).andExpect(status().isFound());
	}

}
