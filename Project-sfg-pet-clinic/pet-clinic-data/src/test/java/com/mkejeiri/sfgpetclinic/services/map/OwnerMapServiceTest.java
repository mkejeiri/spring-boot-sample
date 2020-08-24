package com.mkejeiri.sfgpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mkejeiri.sfgpetclinic.model.Owner;

class OwnerMapServiceTest {
	final Long ownerId = 1L;
	final String lastName = "Shakespeare";
	OwnerMapService ownerMapService;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {		
		ownerMapService = new OwnerMapService(new PetMapService(), new PetTypeMapService());
		ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
	}

	@AfterEach
	void tearDown() throws Exception {
	}	

	@Test
	final void testFindByIdLong() {		
		var owner = ownerMapService.findById(ownerId);
		assertEquals(ownerId, owner.getId());
		
	}

	@Test
	final void testSaveExistingOwner() {
		Long id = 2L;
		Owner owner = Owner.builder().id(id).build();
		var savedOwner = ownerMapService.save(owner);
		assertEquals(id, savedOwner.getId());		
	}
	
	
	@Test
	final void testSaveOwnerNoId() {		
		Owner owner = Owner.builder().build();
		var savedOwner = ownerMapService.save(owner);
		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());
	}

	@Test
	final void testFindAll() {
		 Set<Owner> owners = ownerMapService.findAll();
		 assertEquals(1, owners.size());
	}

	@Test
	final void testDeleteOwner() {
		ownerMapService.delete(ownerMapService.findById(ownerId));
		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	final void testDeleteByIdLong() {
		ownerMapService.deleteById(ownerId);
		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	final void testFindByLastName() {		
		var owner = ownerMapService.findByLastName(lastName);
		assertNotNull(owner);
		assertEquals(lastName, owner.getLastName());
	}
	
	@Test
	final void testFindByLastNameNotFound() {
		var owner = ownerMapService.findByLastName("foo");
		assertNull(owner);
	}

}
