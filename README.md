# Spring boot samples 
- 01-spring5webapp-starter : Quick into to MVC with spring boot, using Thymeleaf templates, jpa hibernate and H2 in-memory database.
- 02-sf-di : deep dive into dependency injection in Spring boot : component, Service, Controller, Qualifier, Primary, Profile annotations
- 03-appjokes: small example of controller, services using Thymeleaf templates
- 04-Spring Framework Configuration: component-scan,  java-configuration-based & xml-configuration-based : mutiple choices how to manage DI conflict ,i.e. Service, Qualifier, Primary, Profile, Bean, Configuration annotations, **spring-factory-bean** is about how to isolate the *java based configuration* in a Factory config class. 
- 05- [External Properties with Spring Framework](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/boot-features-external-config.html): property source, spring Environment variables, multiple property files, application.yml properties, overriding properties by using profile specific application property (YAML) which has more precedence over file which doesn't have profile...
- 06-spring-mvc-recipe : JPA Data Modeling with Spring and Hibernate
- 07-lombok-recipe: spring-mvc-recipe refactored into a project lombok spring-mvc.
- 08-Unit-Integration-Test-recipe: unit and integration test with junit4 : 
> Unit and Integration test consideration : *maven-surefire-plugin* run the test by default (the unit test), but *maven-failsafe-plugin* will run the integration test, see pom.xml where we explicitly inlude everything ending with *IT.java*. in addition, *additionalClasspathElements* is there because the spring team made a change in the ClassPath and now spring boot JAR laid out and it broke the *maven-failsafe-plugin*, therefore we need to include this, otherwise we will get an error saying : *maven  that is unable to find classes*.   
> JUnit Vintage : it's  Optional JUnit 5 Library, it Provides a test runner for JUnit 3 and 4 tests using JUnit 5, it also allows easy migration to JUnit 5
> **Example of pom.xml :**
```
	<groupId>org.junit.vintage</groupId>
	<artifactId>junit-vintage-engine</artifactId>
```

- 09-CRUD Operations with Spring MVC: CRUD operation on H2 database using Hibernate, upload images, save images into DB (not recommended, use S3 for instance), unit test and integration test

------------------
# Project
There is a **Project-sfg-pet-clinic** which goes through all of the above.
