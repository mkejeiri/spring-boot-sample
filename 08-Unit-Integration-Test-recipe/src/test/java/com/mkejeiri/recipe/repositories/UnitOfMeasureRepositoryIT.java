package com.mkejeiri.recipe.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.mkejeiri.recipe.domain.UnitOfMeasure;

@RunWith(SpringRunner.class)
@DataJpaTest // will bring an embedded db and configure Spring Data JPA.
public class UnitOfMeasureRepositoryIT {

// spring will do DI for us in this integration test,
//i.e. spring context will start up and we will get an instance of UnitOfMeasureRepository inject into it 
	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	//@DirtiesContext //if the method is able to change the data in DB, 
	//this will rollback the changes after test
	public final void testFindByDescription() {
		// fail("Not yet implemented"); // TODO
		Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		assertEquals("Teaspoon", uomOptional.orElseGet(null).getDescription());
	}
	
	@Test
	public final void testFindByDescriptionCup() {
		// fail("Not yet implemented"); // TODO
		Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Cup");
		assertEquals("Cup", uomOptional.orElseGet(null).getDescription());
	}

}
