# Project Object Model (pom) file
 
A litte explantion of the list of dependencies.

##### spring-boot-starter-data-jpa will bring Spring Data JPA and Hibernate
```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
```

##### spring-boot-starter-thymeleaf will bring thymeleaf that gives the thymeleaf template engine
```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
```

##### spring-boot-starter-web will give us tomcat instance
```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
```

##### h2 in memory relational database
```
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
```

##### projectlombok will give us the @Data (setter, getter, tostring, equal,..), @Setter,@Getter, contructor (@NoArgsConstructor, @AllArgsConstructor), @Builder, @Slf4j for logging, ... out of the box
```
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
```

##### webjars gives us access to bootstrap and Jquery
```
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>bootstrap</artifactId>
			<version>4.5.2</version>
		</dependency>
```

##### spring-boot-starter-test brings JUnit for tests
```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
```

