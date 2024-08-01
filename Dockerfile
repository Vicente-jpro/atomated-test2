FROM openjdk:11
VOLUME /tmp 
EXPOSE 8080
ADD ./target/atomated-test2-0.0.1-SNAPSHOT.jar atomated-test2.jar 
ENTRYPOINT [ "java", "-jar", "/atomated-test2.jar" ]
