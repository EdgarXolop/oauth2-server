FROM maven:3-jdk8
WORKDIR /usr/src/java-code
COPY . /usr/src/java-code/
RUN mvn package
 
WORKDIR /usr/src/java-app
RUN cp /usr/src/java-code/target/*.jar ./app.jar
EXPOSE 8081
CMD ["java", "-jar", "app.jar"]