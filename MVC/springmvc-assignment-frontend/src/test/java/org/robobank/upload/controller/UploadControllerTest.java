package org.robobank.upload.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import workshop.AssignmentFrontendApplication;
import workshop.controller.UploadController;
import workshop.service.UploadService;

@SpringBootTest(classes = AssignmentFrontendApplication.class)
@AutoConfigureMockMvc
class UploadControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	UploadService service;

	@InjectMocks
	UploadController controller;

	@Test
	@DisplayName("file upload test")
	void testUpload() throws Exception {
		File file = new File("C:\\Development\\Files\\Robobank\\Upload\\issues.csv");
		InputStream is = new FileInputStream(file);
		
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "issues.csv", MediaType.MULTIPART_FORM_DATA_VALUE, is);

		mockMvc.perform(multipart("/upload")
				.file(mockMultipartFile)
				.contentType(MediaType.MULTIPART_FORM_DATA))
		.andExpect(status().is(200));
	}
}
