# [Docker Cheat Sheet](https://springframework.guru/docker-cheat-sheet-for-spring-devlopers/) 

#### List all Docker Images 
```
docker images -a 
```
#### List All Running Docker Containers 
```
docker ps 
```
#### List All Docker Containers 
```
docker ps -a 
```
#### Start a Docker Container 
```
docker start <container name> 
```
#### Stop a Docker Container 
```
docker stop <container name> 
```
#### Kill All Running Containers 
```
docker kill $(docker ps -q) 
```
#### View the logs of a Running Docker Container 
```
docker logs <container name> 
```
#### Delete All Stopped Docker Containers, Use -f option to nuke the running containers too. 
```
docker rm $(docker ps -a -q) 
```
#### Remove a Docker Image 
```
docker rmi <image name> 
```
#### Delete All Docker Images 
```
docker rmi $(docker images -q) 
```
#### Delete All Untagged (dangling) Docker Images 
```
docker rmi $(docker images -q -f dangling=true) 
```
#### Delete All Images 
```
docker rmi $(docker images -q) 
```
#### Remove Dangling Volumes 
```
docker volume rm -f $(docker volume ls -f dangling=true -q) 
```
#### SSH Into a Running Docker Container 

#### Okay not technically SSH, but this will give you a bash shell in the container. 
```
sudo docker exec -it <container name> bash 
```
#### Use Docker Compose to Build Containers 

#### Run from directory of your docker-compose.yml file. 
```
docker-compose build 
```
#### Use Docker Compose to Start a Group of Containers 

#### Use this command from directory of your docker-compose.yml file. 
```
docker-compose up -d 
```
This will tell Docker to fetch the latest version of the container from the repo, and not use the local cache. 
```
docker-compose up -d --force-recreate 
```
*This can be problematic if you’re doing CI builds with Jenkins and pushing Docker images to another host, or using for CI testing. deploying a Spring Boot Web Application from Jenkins, and found the docker container was not getting refreshed with the latest Spring Boot artifact.* 

#### stop docker containers, and rebuild 
```
docker-compose stop -t 1 

docker-compose rm -f 

docker-compose pull 

docker-compose build 

docker-compose up -d 
```
#### Follow the Logs of Running Docker Containers With Docker Compose 
```
docker-compose logs -f 
```
#### Save a Running Docker Container as an Image 
```
docker commit <image name> <name for image> 
```
#### Follow the logs of one container running under Docker Compose 
```
docker-compose logs pump <name> 
```

#### Dockerfile Hints 
##### Add Oracle Java to an Image For CentOS/ RHEL 
```
ENV JAVA_VERSION 8u31 
ENV BUILD_VERSION b13 
# Upgrading system 

RUN yum -y upgrade 
RUN yum -y install wget 
#  Downloading & Config Java 8 

RUN wget --no-cookies --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/$JAVA_VERSION-$BUILD_VERSION/jdk-$JAVA_VERSION-linux-x64.rpm" -O /tmp/jdk-8-linux-x64.rpm 

RUN yum -y install /tmp/jdk-8-linux-x64.rpm 

RUN alternatives --install /usr/bin/java jar /usr/java/latest/bin/java 200000 

RUN alternatives --install /usr/bin/javaws javaws /usr/java/latest/bin/javaws 200000 

RUN alternatives --install /usr/bin/javac javac /usr/java/latest/bin/javac 200000 

Add / Run a Spring Boot Executable Jar to a Docker Image 

VOLUME /tmp 

ADD /maven/myapp-0.0.1-SNAPSHOT.jar my-springbootweb-app.jar 

RUN sh -c 'touch /my-springbootweb-app.jar' 

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/my-springbootweb-app.jar"] 
```
 
### Sample of docker file  assuming we already package our project into a Jar using maven

```
FROM centos

RUN yum install -y java

VOLUME /tmp

ADD /spring-boot-web-0.0.1-SNAPSHOT.jarmy-springbootweb-app.jar

RUN sh -c 'touch /my-springbootweb-app.jar'

RUN chmod 777 /my-springbootweb-app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/my-springbootweb-app.jar"]

```

```
FROM openjdk:latest

# Set the working directory to /app

WORKDIR /app

VOLUME /tmp

# Copy the current spring-boot-web-0.0.1-SNAPSHOT.jar file into the container at /app
ADD /spring-boot-web-0.0.1-SNAPSHOT.jarmy-springbootweb-app.jar

RUN sh -c 'touch /my-springbootweb-app.jar'

RUN chmod 777 /my-springbootweb-app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/my-springbootweb-app.jar"]
```

```
docker build -t myapp .
```
