package com.mkejeiri.recipe.services;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.mkejeiri.recipe.domain.Recipe;
import com.mkejeiri.recipe.repositories.RecipeRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;


class ImageServiceImplTest {
	 @Mock
	    RecipeRepository recipeRepository;

	    ImageService imageService;

    
    
    
	@BeforeEach
	void setUp() throws Exception {
		
		 MockitoAnnotations.initMocks(this);

	        imageService = new ImageServiceImpl(recipeRepository);
	}

	 @Test
	    public void saveImageFile() throws Exception {
	        //1-given
	        Long id = 1L;
	        MultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
	                "Spring Framework Guru".getBytes());

	        Recipe recipe = new Recipe();
	        recipe.setId(id);
	        Optional<Recipe> recipeOptional = Optional.of(recipe);

	        
	        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
	        
	        //we capture the argument of the RecipeRepository.save method
	        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

	        //2-when
	        imageService.saveImageFile(id, multipartFile);

	        //3-then
	        verify(recipeRepository, times(1)).save(argumentCaptor.capture());
	        
	        //We get the captured argument (recipe) and we compare the image bytes to the one we save through imageService
	        Recipe savedRecipe = argumentCaptor.getValue();
	        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
	    }


}
