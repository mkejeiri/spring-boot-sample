package com.mkejeiri.appjokes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mkejeiri.appjokes"}) //this is a default, no need to specify this, kept for training purpose only!


//step 1 - put xml configuration file (chuck-config.xml) inside main resource directory : 
//- configuration will be made available in spring context on the class path
//- java will load that in the class path, the resource file get packaged into a Jar file and made available at runtime.
//- chuck-config.xml is a spring configuration file type
//step 2 - add <bean name="chuckNorrisQuotes" class="guru.springframework.norris.chuck.ChuckNorrisQuotes"></bean> into chuck-config.xml
//step 3 - @ImportResource("classpath:chuck-config.xml")
@ImportResource("classpath:chuck-config.xml") //search in the class path chuck-config.xml file, and spring will pull that in.
public class AppjokesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppjokesApplication.class, args);
	}

}
