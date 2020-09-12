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
```