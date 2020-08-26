package com.mkejeiri.recipe.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import com.mkejeiri.recipe.command.CategoryCommand;
import com.mkejeiri.recipe.domain.Category;

@ExtendWith(MockitoExtension.class)
public class CategoryCommandToCategoryTest {
	public static final Long ID_VALUE = 1L;
	public static final String DESCRIPTION = "description";

	CategoryCommand categoryCommand;
	CategoryCommandToCategory conveter;

	@BeforeEach
	void setUp() throws Exception {
		 conveter = new CategoryCommandToCategory();
	}

	@Test
	final void testConvert() throws Exception {
        //given
        categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = conveter.convert(categoryCommand);

        //then
        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
	
	@Test
    public void testNullObject() throws Exception {
        assertNull(conveter.convert(null));
    }
	
	@Test
    public void testEmptyObject() throws Exception {
        assertNotNull(conveter.convert(new CategoryCommand()));
    }
	
	

}
