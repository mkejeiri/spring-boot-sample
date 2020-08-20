package com.mkejeiri.greeting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

//step 1
@Configuration

//@PropertySource({"classpath:datasource.properties","classpath:jms.properties"})
//Or (added in Spring 4)
@PropertySources({ 
	@PropertySource("classpath:datasource.properties"), 
	@PropertySource("classpath:jms.properties") 
	})

public class PropertyConfig {

	// Wire up the ENV
	@Autowired
	Environment environment;

	// step 2
	@Value("${datasource.username}")
	String dsUsername;

	@Value("${datasource.password}")
	String dsPasword;

	@Value("${datasource.dburl}")
	String dsUrl;

	@Value("${jms.username}")
	String jmsUsername;

	@Value("${jms.password}")
	String jmsPasword;

	@Value("${jms.dburl}")
	String jmsUrl;

	@Bean
	FakeDataSource fakeDataSource() {
//		return new FakeDataSource(dsUsername, dsPasword, dsUrl);
		// environment.getProperty("USERNAME") this will get the environment variable
		// USERNAME
		// to add it via Eclipse go run configuration and environment

		return new FakeDataSource(environment.getProperty("USERNAME"), dsPasword, dsUrl);
	}

	@Bean
	FakeJmsBroker fakeJmsBroker() {
//		return new FakeDataSource(dsUsername, dsPasword, dsUrl);
		// environment.getProperty("USERNAME") this will get the environment variable
		// USERNAME
		// to add it via Eclipse go run configuration and environment

		return new FakeJmsBroker(jmsUsername, jmsUsername, jmsUrl);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
