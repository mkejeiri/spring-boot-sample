package com.mkejeiri.sfgpetclinic.services.springdatajpa;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mkejeiri.sfgpetclinic.model.Owner;
import com.mkejeiri.sfgpetclinic.repositories.OwnerRepository;
import com.mkejeiri.sfgpetclinic.repositories.PetRepository;
import com.mkejeiri.sfgpetclinic.repositories.PetTypeRepository;

//We are leveraging Mockito for unit test, don't forget to add to pom.xml
@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
	static final String LAST_NAME = "smith";
	static final Long ID = 1L;
	Owner owner;

	@Mock
	OwnerRepository ownerRepository;

	@Mock
	PetRepository petRepository;

	@Mock
	PetTypeRepository petTypeRepository;

	@InjectMocks // inject dependencies the above dependencies to OwnerSDJpaService,
	// otherwise when using ownerSDJpaService gives java.lang.NullPointerException
	OwnerSDJpaService ownerSDJpaService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		owner = Owner.builder().id(ID).lastName(LAST_NAME).build();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testFindById() {
		// given
		// when
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));
		var returnedOwner = ownerSDJpaService.findById(ID);
		// then
		assertNotNull(returnedOwner);
		// times(1) is the default
		verify(ownerRepository, times(1)).findById(anyLong());
	}

	@Test // testing orElse(null)
	final void testFindByIdNotFound() {
		// given
		// when
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
		var returnedOwner = ownerSDJpaService.findById(ID);
		// then
		assertNull(returnedOwner);
		// times(1) is the default
		verify(ownerRepository, times(1)).findById(anyLong());
	}

	@Test
	final void testSave() {
		// given
		Owner ownerToSave = Owner.builder().id(ID).firstName("M").lastName(LAST_NAME).build();
		// when
		when(ownerRepository.save(any())).thenReturn(owner);

		// then
		Owner savedOwner = ownerSDJpaService.save(ownerToSave);
		// times(1) is the default
		verify(ownerRepository, times(1)).save(any());
		assertNotNull(savedOwner);

	}

	@Test
	final void testFindAll() {
		// given
		Set<Owner> owners = new HashSet<Owner>();
		owners.add(Owner.builder().id(1L).build());
		owners.add(Owner.builder().id(2L).build());

		// when
		when(ownerRepository.findAll()).thenReturn(owners);

		// then
		var returnedOwners = ownerSDJpaService.findAll();
		assertNotNull(returnedOwners);
		assertEquals(2, returnedOwners.size());

	}

	@Test // this delete methods doesn't return a value, the best candidate for verify!
	final void testDelete() {
		ownerSDJpaService.delete(owner);
		// times(1) is the default
		//verify(ownerRepository, times(1)).delete(any());
		verify(ownerRepository).delete(any());
	}

	@Test
	final void testDeleteById() {
		ownerRepository.deleteById(owner.getId());

	}

	@Test
	final void testFindByLastName() {

		// given: see setUp
		// when
		when(ownerRepository.findByLastName(any())).thenReturn(owner);

		// assert
		assertEquals(LAST_NAME, ownerSDJpaService.findByLastName(LAST_NAME).getLastName());
		// verify that we call ownerRepository
		// times(1) is the default
		//verify(ownerRepository, times(1)).findByLastName(any());
		verify(ownerRepository).findByLastName(any());
	}

}
