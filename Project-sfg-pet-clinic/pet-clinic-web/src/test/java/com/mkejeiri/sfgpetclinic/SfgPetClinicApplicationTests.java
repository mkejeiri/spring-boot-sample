package com.mkejeiri.sfgpetclinic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest //this is useful for Integration test since it loads all spring context (expensive!)
class SfgPetClinicApplicationTests {

	@Test
	void contextLoads() {
	}

}
