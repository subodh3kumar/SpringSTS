package org.robobank.validation.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import workshop.AssignmentBackendApplication;
import workshop.controller.ValidatorController;
import workshop.service.ValidationService;

@SpringBootTest(classes = AssignmentBackendApplication.class)
@AutoConfigureMockMvc
class ValidatorControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ValidationService service;

	@InjectMocks
	ValidatorController controller;

	@Test
	@DisplayName("file upload test")
	void testValidateStatement() throws Exception {
		File file = new File("C:/Development/Files/Robobank/Statements/records.csv");
		InputStream is = new FileInputStream(file);
		
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "records.csv", "multipart/form-data", is);

		MvcResult result = mockMvc.perform(multipart("/validate/statement")
						.file(mockMultipartFile)
						.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		assertEquals(200, result.getResponse().getStatus());
		assertNotNull(result.getResponse().getContentAsString());
	}
}
