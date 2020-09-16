# javac, java, ClassPath MEMO

```
package mp;

public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello, World");
    }

}
```

```
#package mp/*.java files, mp should be also a folder  because 
#it's a class package (mp = my package)

javac  .\mp\*.java


# need to specify a classpath (e.g. '.' ) and fully 
# qualified class name (packageName.ClassName)
java -cp . mp.HelloWorld
>> output: Hello, World


#create a test.jar file in win 10 using jdk-11.0.8
#'C:\Program Files\Java\jdk-11.0.8\bin\jar.exe' -cf test.jar mp/*.class

#run the jar file
java -cp test.jar  mp.HelloWorld

```
-----
# Maven


###### shows the dependency tree. Useful for resolving conflicts
 ``` 
 mvn dependency:tree
 ```

###### Resolve all, prepare to go offline
 ``` 
 mvn dependency:go-offline
 ```

###### Clear artifacts from local repository
 ``` 
 mvn dependency:purge-local-repository
 ```

###### get sources for all dependencies.
 ```
 mvn dependency:sources 
 ```
---
# Maven Build Lifecycles
Maven is based on the concept of build lifecycles :
- A lifecycle is a **pre-defined group** of **build steps** called **phases**
- Each **phase** can be **bound** to one or more **plugin goals**
- Keep in mind all **work** done in **Maven** is done by **plugins!**
- **Lifecycles** and **phases** provide the **framework** to call **plugin goals** in a **sequence**

Maven has _three_ **pre-defined lifecycles**: 
- **clean** : Does a clean of the project, **removes** all **build artifacts** from **working directory**, Defined with **plugin bindings**.
- **default** : Does the **build and deployment** of project, Defined without **plugin bindings**, **bindings** are *defined* for each **packaging**.
- **site**: 
	- Creates the a **website** for the project, Defined with **plugin bindings**.
	- **Least** used in the enterprise.
	- **Maven websites** are all **built** using the **Maven** **site lifecycle**.
	

**_Default Lifecycle - High-level_**
-
- **Validate** : Verify project is correct.
- **Compile** : Compile Source Code.
- **Test** : Test Compiled Source Code.
- **Package** : Package compiled files to packaging type.
- **Verify** : Run Integration Tests.
- **Install** : Install to local Maven Repository.
- **Deploy** : Deploy to shared Maven Repository.

**_Default Lifecycle All Phases_:**

*Validate*, *Initialize*, *Generate Sources*, *Process Sources*, *Generate Resources*, *Process Resources*, *Compile*, *Process Classes*, *Generate Test Sources*, *Process Test Sources*, *Generate Test Resources*, *Process Test Resources*, *Test Compile*, *Process Test Classes*, *Test*, *Prepare Package*, *Package*, *Pre-Integration Test*, *Integration Test*, *Post Integration Test*, *Verify*, *Install*, *Deploy*.

_Default Lifecycle - JAR Packaging_
--
1-  **Phase**: process-resources - **Plugin**: `maven-resources-plugin : resources`.

2- **Phase**: compile - **Plugin**: `maven-compiler-plugin : compile`.

3- **Phase**: process-test-resources - **Plugin**: `maven-resources-plugin : testResources`.

4- **Phase**: test-compile - **Plugin**: `maven-compiler-plugin : testCompile`.

5- **Phase**: test - **Plugin**: `maven-surefire-plugin : test`

6- **Phase**: package - **Plugin**: `maven-jar-plugin : jar`

7- **Phase**: install - **Plugin**: `maven-install-plugin : install`

8- **Phase**: deploy - **Plugin**: `maven-deploy-plugin : deploy`



_Site Build Lifecycle_:
--
**Site Lifecycle Phases:**

1- Phase: Pre-site - Plugin: `none`

2- Phase: Site - Plugin: `maven-site-plugin : site`

3- Phase: Post-site - Plugin: `none`

4- Phase: Site-Deploy - Plugin: `maven-site-plugin : deploy`


Maven Wrapper
-----
- It a wrapper around maven without having to install maven in the local machine, **but we need to have Java installed instead**.
  - check if it's install : `mvn -version`
  - example of creating an takari wrapper `mvn -N io.takari:maven:wrapper` [more...](https://github.com/takari/maven-wrapper) 
  - run `.\mvnw -version` to get the maven wrapper version which might be different from installed version:
  
    - if we go to .mvn/wrapper/maven-wrapper.properties folder, we get infromartion about version also io.takari maven repository:

```
distributionUrl=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.6.3/apache-maven-3.6.3-bin.zip
wrapperUrl=https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar
```
  - **example of updating an takari wrapper** with an mvn target version 3.6.0 (be sure that you are in the project folder!) `mvn -N io.takari:maven:wrapper -Dmaven=3.6.0`
    
- Good habit is to distribute the project using wrapper.
- If we run a project without a wrapper either it will use the maven installed in the system if any or use the one from IDEA (intellij, eclipse,...).




Maven Archetypes
-----
- Maven Archetypes are project templates
- Archetype - *An original pattern or model from with all other things of the same kind are made.*
- Apache Maven provides a variety of standard archetypes to serve as starters for common Java
projects
- Maven Archetypes are also available from a variety of 3rd parties
- Some of the archetypes are getting rather outdated (i.e. J2EE 10 year ago, Apache Camel,...)

Example : `mvn archetype:generate -DarchetypeArtifactId=maven-archetype-archetype -DgroupId=groupId -DartifactId=asimio-api-archetype -DinteractiveMode=false
` [more...](https://maven.apache.org/archetypes/) 
>> in windows it works only in cmd and not in powershell.


# Maven Plugins

**Maven Clean Plugin**
---
- Build Lifecycle - CLEAN 
- Has only one goal - 'clean'
- Purpose is to remove files generated during build process.
- By default removes /target directory project root and submodule root folders


**In maven all phases of lifecycles are hooks around plugin which have plugin's default implementations per phases that we could also customized**

> for **initialize** phase we explicitly add the clean goal, thus all phase will run the goal clean since they have the initialize phase

```
<build>
	<plugins>
		<plugin>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-clean-plugin</artifactId>
			<!--<version>3.1.0</version>-->
			<executions>
				<execution>				
					<id>my-auto-clean</id>
					<phase>initialize</phase>
					<goals>
						<goal>clean</goal>
					</goals>
				</execution>
			</executions>
		</plugin>
	</plugins>
</build>
```

>> output :
```
...
[INFO] --- maven-clean-plugin:2.5:clean (my-auto-clean) @ hello-world ---
[INFO] Deleting C:\Java-Examples\helloworld\target
..
```


**Maven Compiler Plugin** : Build Lifecycle - DEFAULT
---
- Has **two goals** : `compiler:compile`, `compiler:testCompile`
- By Default uses the compiler `javax.tools.JavaCompiler` out of JVM and which is the default compiler available within the JVM and not the `javac` we use in command line available also within the JVM!
- Can be **configured** to use `javac` if needed
- Default source and target language levels are `Java 1.6`
- Apache team encourages these values to be set

**Maven Resources Plugin** : Build Lifecycle - DEFAULT
---
- Has **3 goals**:  `resources:resources`, `resources:testResources`, `resources:copy-resources`
- Purpose is to **copy project resources** to output directory (**target dir**)
- Can be configured for encoding, **source and target directories**
- Rather versatile configuration options for **copying files** during **build processing**

Maven Surefire Plugin: Build Lifecycle - DEFAULT
----
- Has **1 goal**: `surefire:test`
- The **Surefire plugin** is used to **execute unit test** of the project.
- By default supports `JUnit 3`, `JUnit 4`, `JUnit 5`, and `TestNG`
- `Cucumber` runs under `JUnit`, `Spock` compiles to **JUnit byte code**.
- By default includes classes named: `**/Test*.java` ` **/*Test.java` `**/*Tests.java` `**/*TestCase.java`


**Maven jar plugin : Build Lifecycle - DEFAULT**
---
- Has **2 goals**: `jar:jar` and `jar:test-jar`
- **Purpose** is to **build jars** from **complied artifacts** and **project resources**
- Can be **configured** for **custom manifests**, and to **make** **executable jars**.
- e.g. used by **Springboot** out of the box.


  see ["Creating an Executable JAR File"](https://maven.apache.org/shared/maven-archiver/examples/classpath.html) 
    
 **pom.xml**
 ```
 ...
  <plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-jar-plugin</artifactId>
	<configuration>
		<archive>
			<manifest>
				<addClasspath>true</addClasspath>
				<mainClass>FullyQualifiedPackageName.HelloWorld</mainClass>
			</manifest>
		</archive>
	</configuration>
</plugin>
...
 ```
  

**Maven Deploy Plugin : Build Lifecycle - DEFAULT**
---
- Has **2 goals** : `deploy:deploy` and  `deploy:deploy-file`
- **Purpose** is to **deploy project artifacts** to **remote** **Maven repositories**
- Often done in **CI**
- **Configuration** is typically **part** of the Maven **POM**

  see ["Apache Maven Deploy Plugin"](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) 

 we could use different Apache wagon protocols for deployment : [https, ssh, ftp ](https://maven.apache.org/plugins/maven-deploy-plugin/project-deployment.html)
 
Maven Site Plugin : Build Lifecycle - SITE
----
It has **7 goals**
- `site:site` : Generate site for project
- `site:deploy` : Deploy site via Wagon
- `site:run` : Run Site **locally** using **Jetty** as **web server**
- `site:stage` : generate site to a local staging directory for testing
- `site:stage-deploy` : Deploy site to remote staging location for testing
- `site:attach-descriptor` : **adds site.xml** (site map file used by search engines) to files for **deployment**
- `site:jar` : **bundles site** into a jar for **deployment** to a Maven **repository**
- `site:effective-site` : generates the **site.xml** file


 
> Maven environment (maven 3.6.0) brings always [maven-site-plugin 3.3](https://stackoverflow.com/questions/51103120/why-does-maven-site-plugin-always-use-version-3-3), to fix this see the code snippet below : 

```
 <plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-site-plugin</artifactId>
	<version>3.7.1</version>
</plugin>
```
see https://maven.apache.org/ref/3.6.3/
and https://maven.apache.org/ref/3.6.3/maven-core/lifecycles.html in the **site Lifecycle** code snippet they bind to maven-site-plugin:3.3 instead of 3.7.1!
```
<phases>
  <phase>pre-site</phase>
  <phase>site</phase>
  <phase>post-site</phase>
  <phase>site-deploy</phase>
</phases>
<default-phases>
  <site>
    org.apache.maven.plugins:maven-site-plugin:3.3:site
  </site>
  <site-deploy>
    org.apache.maven.plugins:maven-site-plugin:3.3:deploy
  </site-deploy>
</default-phases>
```
 
**Maven commands**
---

```
#mvn plugin: run all goal within the plugin!
mvn clean

#mvn plugin:goal
mvn clean:clean 

mvn validate

mvn test

#combine 2 lifecycles
mvn clean test

#install the jar's in .m2 local repository
mvn install

mvn dependency:tree

mvn help:effective-pom

mvn package -DTestskip

mvn clean dependency:copy-dependencies package


```
see [Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

> when we run the package lifecycle maven create all sources in `target/generated-sources`, and then copies them to `target\classes`, and then zip them into a jar file in `target\status` (e.g. jaxb case).


**Lombok Project**
---
Project Lombok is a `code generator` which uses `annotation processing` to enhance classes, we can generate `getters, setters, toString, etc` from annotations at compile time.

> for intellij need to go: `Build, Execution, Deployment -> Compiler -> Annotation Processors` and then `Enable annotation processing` 

see example project using Maven with [Project Lombok](https://projectlombok.org/)

**MapStruct Maven**
---
- MapStruct is an annotation based object mapper.
- Works by creating an `interface` for a `mapper`, annotation processing is used to create the implementation.

see example Maven project using [MapStruct](http://mapstruct.org/)


# Alternate JVM Languages in Maven

- Most major alternate JVM languages can be compiled to Java byte code with Maven
- Typically done via plugins hooking into the ‘compile’ phase
- Each JVM flavor is a little different in terms of capabilities
- Most can be compiled with Java in the same project
- More than 2 languages in one project is generally not a good idea, nor supported, we should separate them into modules.

**Groovy**
----
This example use the [Groovy Eclipse Plugin](https://github.com/groovy/groovy-eclipse/wiki/Groovy-Eclipse-Maven-plugin) 
to compile Groovy with Java.

[see a full example](https://github.com/springframeworkguru/mb2g-alt-jvm/tree/groovy) 

**pom.xml** with an example of adding an extra `pluginRepositories` to groovy (`https://dl.bintray.com/groovy/maven`) 
```
 <build>
        <plugins>
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.0</version><!-- 3.1 is the minimum -->
                <configuration>
                    <compilerId>groovy-eclipse-compiler</compilerId>
                    <compilerArguments>
                        <indy/><!-- optional; supported by batch 2.4.12-04+ -->
                        <configScript>config.groovy</configScript><!-- optional; supported by batch 2.4.13-02+ -->
                    </compilerArguments>
                </configuration>
                <dependencies>
                    <dependency>
                        <groupId>org.codehaus.groovy</groupId>
                        <artifactId>groovy-eclipse-compiler</artifactId>
                        <version>3.0.0-01</version>
                    </dependency>
                    <dependency>
                        <groupId>org.codehaus.groovy</groupId>
                        <artifactId>groovy-eclipse-batch</artifactId>
                        <version>2.5.4-01</version>
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </build>

<!-- in addition to maven central repo we should also add https://dl.bintray.com/groovy/maven where the groovy latest plugin is released, it's been discontinued in maven central-->
    <pluginRepositories>
        <pluginRepository>
            <id>bintray</id>
            <name>Groovy Bintray</name>
            <url>https://dl.bintray.com/groovy/maven</url>
            <releases>
                <updatePolicy>never</updatePolicy>
            </releases>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </pluginRepository>
    </pluginRepositories>
```

**Kotlin**
----
for Kotlin with Maven, please see documentation on [configuring Maven to support Kotlin](https://kotlinlang.org/docs/reference/using-maven.html)

We need to add kotlin and maven plugin (i.e. to disable them!), see pom.xml next.


**pom.xml**
[see a full example](https://github.com/springframeworkguru/mb2g-alt-jvm/tree/kotlin)

```
<properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>11</java.version>
        <maven.compiler.source>${java.version}</maven.compiler.source>
        <maven.compiler.target>${java.version}</maven.compiler.target>
        <kotlin.version>1.3.11</kotlin.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.jetbrains.kotlin</groupId>
            <artifactId>kotlin-stdlib</artifactId>
            <version>${kotlin.version}</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <artifactId>kotlin-maven-plugin</artifactId>
                <groupId>org.jetbrains.kotlin</groupId>
                <version>${kotlin.version}</version>
                <executions>
                    <execution>
                        <id>compile</id>
                        <goals> <goal>compile</goal> </goals>
                        <configuration>
                            <sourceDirs>
                                <sourceDir>${project.basedir}/src/main/kotlin</sourceDir>
                                <sourceDir>${project.basedir}/src/main/java</sourceDir>
                            </sourceDirs>
                        </configuration>
                    </execution>
                    <execution>
                        <id>test-compile</id>
                        <goals> <goal>test-compile</goal> </goals>
                        <configuration>
                            <sourceDirs>
                                <sourceDir>${project.basedir}/src/test/kotlin</sourceDir>
                                <sourceDir>${project.basedir}/src/test/java</sourceDir>
                            </sourceDirs>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.5.1</version>
                <executions>
                    <!-- Replacing default-compile as it is treated specially by maven -->
                    <execution>
                        <id>default-compile</id>
                        <phase>none</phase>
                    </execution>
                    <!-- Replacing default-testCompile as it is treated specially by maven -->
                    <execution>
                        <id>default-testCompile</id>
                        <phase>none</phase>
                    </execution>
                    <execution>
                        <id>java-compile</id>
                        <phase>compile</phase>
                        <goals> <goal>compile</goal> </goals>
                    </execution>
                    <execution>
                        <id>java-test-compile</id>
                        <phase>test-compile</phase>
                        <goals> <goal>testCompile</goal> </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

**Scala**
----
for Scala with Maven, please see documentation on [SCALA WITH MAVEN
](https://docs.scala-lang.org/tutorials/scala-with-maven.html)
 
**pom.xml**
[see a full example](https://github.com/springframeworkguru/mb2g-alt-jvm/tree/scala)
> Enable maven Scala plugin and disable maven java plugin!
```
<properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>11</java.version>
        <maven.compiler.source>${java.version}</maven.compiler.source>
        <maven.compiler.target>${java.version}</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.scala-lang</groupId>
            <artifactId>scala-library</artifactId>
            <version>2.11.7</version>
        </dependency>
    </dependencies>

    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>net.alchim31.maven</groupId>
                    <artifactId>scala-maven-plugin</artifactId>
                    <version>3.4.4</version>
                </plugin>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>2.0.2</version>
                </plugin>
            </plugins>
        </pluginManagement>
        <plugins>
            <plugin>
                <groupId>net.alchim31.maven</groupId>
                <artifactId>scala-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <id>scala-compile-first</id>
                        <phase>process-resources</phase>
                        <goals>
                            <goal>add-source</goal>
                            <goal>compile</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>scala-test-compile</id>
                        <phase>process-test-resources</phase>
                        <goals>
                            <goal>testCompile</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <executions>
                    <execution>
                        <phase>compile</phase>
                        <goals>
                            <goal>compile</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
 
``` 


**Multiple Modules in Maven**
---
- Important to remember each module is effectively a Maven project
	- Just happens to inherit from its parent module
	- Much like the `effective` POM
- Each module can have child modules
	- Important for organization on larger projects
	
**Maven Reactor**
---
- Known simply as **The Reactor**
- The Reactor is the what builds each module of the Maven project
- The Reactor collects the modules to build
- The Reactor will then run selected build lifecycle against each module
- The Reactor will determine the build order of the modules
- By default The Reactor will build modules sequentially : Optionally can use threads to build modules in parallel

---
**Reactor Build Order**

Factors determining the build order used by Reactor:
- **Project dependencies**: **modules used** by other modules in the project will get **built first**
- **Plugin declaration**: i.e. if **module** is a **plugin** **used** by other **modules**
- **Order** of modules declared in **modules section** of **POM**


**Multi-Module Code Smells**
---
*to KEEP IN MIND* :
- Try to use modules only when needed
- Do not **over-use** modules , i.e. Multiple modules will slow down your build.
---

cases of **Code Smells** with **Modules**:
- One class or interface in a module!
- Many small modules which could be combined!
- Thinking that someone might need, stick to YAGNI principle.


**Maven BOM**
---
- BOM: **Bill of Material** : Manufacturing term meaning effectively a recipe of components required to produce a widget
- In Maven terminology, a **BOM** has become to mean **dependencies declared** within the
**dependencyManagement** section of the **POM**.
- **Fully qualified dependencies** are listed under the **dependencyManagement section** of the POM
- **Dependencies declared** under the **dependencies section** of the **POM inherit** from **dependencyManagement** (**version / packaging**)
- Typically **used** to **standardized versions**.


**Maven Spring-boot**
---
**some Maven commands to be keep in mind**:
- `mvn spring-boot:help`
- `mvn spring-boot:run`
- `mvn verify ` : verify run integration phase and run spring-boot app afterward.
- `mvn clean spring-boot:run` : remove target folder and run spring-boot app
- `mvn clean package spring-boot:run` : run clean, package and then run spring-boot app
- `mvn spring-boot:build-image`
- `mvn spring-boot:build-image -DskipTests`
- `mvn spring-boot:build-info`
- `mvn spring-boot:repackage`
- `mvn spring-boot:run`
- `mvn spring-boot:start`
- `mvn spring-boot:stop`



Within the spring-boot app, in order **debug and check out** some versions and other metadata info related to the deployed app, make sure that the `spring-boot-starter-actuator` is added to the project and change the the build `spring-boot-maven-plugin` in the **pom.xml** as follow:

```
...
<properties>
        <java.version>11</java.version>
        <some.custom.prop>Watching</some.custom.prop>
</properties>
...
  <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <id>build-info-goal</id>
                        <goals>
                            <goal>build-info</goal>
                        </goals>
                        <configuration>
                            <additionalProperties>
                                <java.version>${java.version}</java.version>
                                <some.prop>${some.custom.prop}</some.prop>
                            </additionalProperties>
                        </configuration>
                    </execution>
                    <execution>
                        <id>pre-it</id>
                        <goals>
                            <goal>start</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>post-it</id>
                        <goals>
                            <goal>stop</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```
go to [http://localhost:8080/actuator/info](http://localhost:8080/actuator/info) and you will receive :

```
{
	"build": {
		"some": {
			"prop": "Watching"
		},
		"java": {
			"version": "11"
		},
		"version": "0.0.1-SNAPSHOT",
		"artifact": "demo",
		"name": "demo",
		"time": "2020-09-13T19:19:27.614Z",
		"group": "com.example"
	}
}
```

to be able to add also **git info** to the **received json payload**, we need to add a plugin `git-commit-id-plugin` into the build like this :

```
  <build>
  ....
			<plugin>
                <groupId>pl.project13.maven</groupId>
                <artifactId>git-commit-id-plugin</artifactId>
            </plugin>
    </build>
```

and add the `management.info.git.mode=full` into `application.properties` file.

---



# Maven Repositories

**Maven Repositories Search Order**
---
When **resolving an artifact**, **Maven** will:
- **First**: Check in the local repository (aka cache) located under <user home>/.m2/repository/
- **Next**: Maven Central
- **Next**: any additional repositories configured

Search order of **additional repositories**:

- Typically not important
- Is Alphabetical by repository id value

**Repository Mirrors**
----
- **Mirrors** are used to override project defined repository values
- **Mirrors** are configured in settings.xml : **Default location** is in `<user>/.m2 directory`
- **A mirror** will override the URL of the repository
- Can be used to improve **performance** by directing to **regional servers**
- Or to redirect to **internal repository manager**
- Values set in `settings.xml` will apply to all **Maven projects** executed on system


**Defining Additional Remote Repositories**
---
Repositories can be defined in the `repositories` element of the **POM**, or in the repositories element in the `settings.xml` file :

- **POM** definitions will be specific to the Maven project
- `settings.xml` definitions are system wide

**Repository Element**
---
- **id** : unique value required
- **name** : human readable name
- **url** : URL for repository
- **layout** : legacy or default (Default is generally used)
- **releases** : Repository Policy for handling downloading of releases
- **snapshots** : Repository Policy for handling downloading of snapshots

**Repository Policy**
---
- Used for **release** and **snapshot** elements of **Repository definitions**
- **enabled** : `true/false`
- **updatePolicy**: `always, daily (default), interval:XXX (xxx in minutes), never`
- **checksumPolicy** : What to **do** if **verification** of **artifact** **fails**, values are : `ignore, fail, warn`

**pom.xml**
```
...
	<repositories>
        <repository>
            <id>redhat-ga</id>
            <url>https://maven.repository.redhat.com/ga/</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
        <repository>
            <id>maven.oracle.com</id>
            <name>oracle-maven-repo</name>
            <url>https://maven.oracle.com</url>
            <layout>default</layout>
            <releases>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </releases>
        </repository>
    </repositories>
...
	<pluginRepositories>
        <pluginRepository>
            <id>maven.oracle.com</id>
            <name>oracle-maven-repo</name>
            <url>https://maven.oracle.com</url>
            <layout>default</layout>
            <releases>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </releases>
        </pluginRepository>
    </pluginRepositories>	
	
...
```

**settings.xml** `pom->maven->create settings.xml`
> created at `C:\Users\<CurentUser>\.m2\settings.xml`

see[ Maven Mirror](https://maven.apache.org/guides/mini/guide-mirror-settings.html#:~:text=To%20configure%20a%20mirror%20of,are%20using%20a%20mirror%20of.) , [from google](https://www.deps.co/guides/public-maven-repositories/), [repo meta data](https://repo.maven.apache.org/maven2/.meta/repository-metadata.xml)

> if we go `pom.xml maven->show effective pom`, we won't see the uk mirror there, because it's local to our user (**settings.xml** only used at IDEA and current user level!).
```
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <mirrors>
        <mirror>
            <id>uk</id>
            <mirrorOf>central</mirrorOf>
            <url>http://uk.maven.org/maven2/</url>
        </mirror>
    </mirrors>
</settings>
```