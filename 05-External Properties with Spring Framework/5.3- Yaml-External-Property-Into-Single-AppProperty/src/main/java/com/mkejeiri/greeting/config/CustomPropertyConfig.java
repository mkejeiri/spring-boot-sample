package com.mkejeiri.greeting.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomPropertyConfig {

	@Value("${datasource.username}")
	String dsUsername;

	@Value("${datasource.password}")
	String dsPasword;

	@Value("${datasource.dburl}")
	String dsUrl;

	@Value("${mkejeiri.jms.username}")
	String jmsUsername;

	@Value("${mkejeiri.jms.password}")
	String jmsPasword;

	@Value("${mkejeiri.jms.dburl}")
	String jmsUrl;

	@Bean
	FakeDataSource fakeDataSource() {
		return new FakeDataSource(dsUsername, dsPasword, dsUrl);
		// environment.getProperty("USERNAME") this will get the environment variable
		// USERNAME to add it via Eclipse go run configuration and environment

	}

	@Bean
	FakeJmsBroker fakeJmsBroker() {
		return new FakeJmsBroker(jmsUsername, jmsPasword, jmsUrl);
	}

//	@Bean
//	public static PropertySourcesPlaceholderConfigurer properties() {
//		return new PropertySourcesPlaceholderConfigurer();
//	}

}
