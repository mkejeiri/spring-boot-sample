package com.mkejeiri.recipe.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mkejeiri.recipe.commands.RecipeCommand;
import com.mkejeiri.recipe.services.ImageService;
import com.mkejeiri.recipe.services.RecipeService;

class ImageControllerTest {
	@Mock
	ImageService imageService;

	@Mock
	RecipeService recipeService;

	ImageController controller;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		controller = new ImageController(imageService, recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(new ExceptionHandlerController())
				.build();
	}

	@Test
	public void getImageForm() throws Exception {
		// given
		RecipeCommand command = new RecipeCommand();
		command.setId(1L);

		when(recipeService.findCommandById(anyLong())).thenReturn(command);

		// when
		mockMvc.perform(get("/recipe/1/image"))
		.andExpect(status().isOk())
		.andExpect(model()
	    .attributeExists("recipe"));

		verify(recipeService, times(1)).findCommandById(anyLong());

	}
	
	@Test
	public void getImageFormBadRequest() throws Exception {
		
		// when
		mockMvc.perform(get("/recipe/aa/image"))
		.andExpect(status().isBadRequest())
		.andExpect(view().name("400error"));
	}
	

	@Test
	public void handleImagePost() throws Exception {
		// We will post a MultipartFile, hence the use of MultipartFile
		// <input id="imagefile" name="imagefile" type="file" class="file"> : the name
		// of the posted item is "imagefile"!!!!
		MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
				"Posted content for testing purposes".getBytes());

		mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
		.andExpect(status().is3xxRedirection())
		.andExpect(header().string("Location", "/recipe/1/show"));

		verify(imageService, times(1)).saveImageFile(anyLong(), any());
	}

	@Test
	public void renderImageFromDB() throws Exception {

		// given
		RecipeCommand command = new RecipeCommand();
		command.setId(1L);

		String s = "fake image text";
		Byte[] bytesBoxed = new Byte[s.getBytes().length];

		int i = 0;

		for (byte primByte : s.getBytes()) {
			bytesBoxed[i++] = primByte;
		}

		command.setImage(bytesBoxed);

		when(recipeService.findCommandById(anyLong())).thenReturn(command);

		// when
		MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
				.andExpect(status().isOk())
				.andReturn().getResponse();

		byte[] reponseBytes = response.getContentAsByteArray();

		assertEquals(s.getBytes().length, reponseBytes.length);
	}

}