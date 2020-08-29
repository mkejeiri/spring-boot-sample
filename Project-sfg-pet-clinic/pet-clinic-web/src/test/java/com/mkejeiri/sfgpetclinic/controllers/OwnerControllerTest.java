package com.mkejeiri.sfgpetclinic.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mkejeiri.sfgpetclinic.model.Owner;
import com.mkejeiri.sfgpetclinic.services.OwnerService;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

	@Mock
	OwnerService ownerService;

	@InjectMocks
	OwnerController controller;

	Set<Owner> owners;
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		// initialise owners set!
		owners = new HashSet<Owner>();
		owners.add(Owner.builder().id(1L).build());
		owners.add(Owner.builder().id(2L).build());

		// initialise spring MVC
		// for each test method it initialises a mock environment for the
		// OwnerController!, standaloneSetup is a ligntweight and allow us to test
		// interations
		// with controllers and services
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}

	@Test
	void processFindFormReturnMany() throws Exception {
		when(ownerService.findAllByLastNameLike(anyString()))
				.thenReturn(
						Arrays.asList(Owner.builder().id(1l).build(), 
								Owner.builder().id(2l).build())
						);

		mockMvc.perform(get("/owners")).andExpect(status().isOk())
		.andExpect(view().name("owners/ownersList"))
		.andExpect(model().attribute("owners", hasSize(2)));
	}

	@Test
	void processFindFormReturnOne() throws Exception {
		when(ownerService.findAllByLastNameLike(anyString()))
		.thenReturn(Arrays.asList(Owner.builder().id(1l).build()));

		mockMvc.perform(get("/owners"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/owners/1"));
	}

	@Test
	void processFindFormEmptyReturnMany() throws Exception {
		when(ownerService.findAllByLastNameLike(anyString()))
				.thenReturn(Arrays.asList(Owner.builder().id(1l).build(), 
						Owner.builder().id(2l).build()));

		mockMvc.perform(get("/owners").param("lastName", ""))
		.andExpect(status().isOk())
		.andExpect(view().name("owners/ownersList"))
		.andExpect(model().attribute("owners", hasSize(2)));
	}

	@Test
	void processCreationForm() throws Exception {
		when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1l).build());

		mockMvc.perform(post("/owners/new"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/owners/1"))
		.andExpect(model().attributeExists("owner"));

		verify(ownerService).save(ArgumentMatchers.any());
	}

	@Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyNoMoreInteractions(ownerService);
    }
	
	
	@Test
	void initUpdateOwnerForm() throws Exception {
		when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1l).build());

		mockMvc.perform(get("/owners/1/edit"))
		.andExpect(status().isOk())
		.andExpect(view().name("owners/createOrUpdateOwnerForm"))
		.andExpect(model().attributeExists("owner"));

		verifyNoMoreInteractions(ownerService);
	}

	@Test
	void processUpdateOwnerForm() throws Exception {
		when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1l).build());

		mockMvc.perform(post("/owners/1/edit"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/owners/1"))
		.andExpect(model().attributeExists("owner"));

		verify(ownerService).save(ArgumentMatchers.any());
	}

//	@Test
//	final void testListOwners() throws Exception {
//		when(ownerService.findAll()).thenReturn(owners);
//
//		mockMvc.perform(get("/owners"))
//		.andExpect(status().isOk())
//		.andExpect(view().name("owners/index"))
//		.andExpect(model().attribute("owners", hasSize(2)));
//	}

//	@Test
//	final void testListOwnersByIndex() throws Exception {
//		when(ownerService.findAll()).thenReturn(owners);
//
//		mockMvc.perform(get("/owners/index"))
//		.andExpect(status().isOk())
//		.andExpect(view().name("owners/index"))
//		.andExpect(model().attribute("owners", hasSize(2)));
//	}

	@Test
	final void testFindOwners() throws Exception {

		 mockMvc.perform(get("/owners/find"))
         .andExpect(status().isOk())
         .andExpect(view().name("owners/findOwners"))
         .andExpect(model().attributeExists("owner"));
		 
		 verifyNoMoreInteractions(ownerService);
		//verify(ownerService, times(1)).findAll();
	}

	@Test
	void displayOwner() throws Exception {

		// Given
		var owner = Owner.builder().id(1l).build();
		when(ownerService.findById(anyLong())).thenReturn(owner);

		// When and then
		mockMvc.perform(get("/owners/123")).andExpect(status().isOk()).andExpect(view().name("owners/ownerDetails"))
				.andExpect(model().attribute("owner", hasProperty("id", is(1l))));
	}

}
