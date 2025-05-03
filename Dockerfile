FROM openjdk:21
WORKDIR /var/lib/jenkins/workspace/jars
ADD target/springboot-conf-sts-authorization-server-db-0.0.1-SNAPSHOT.jar springboot-conf-sts-authorization-server-db.jar
COPY target/springboot-conf-sts-authorization-server-db-0.0.1-SNAPSHOT.jar springboot-conf-sts-authorization-server-db-0.0.1-SNAPSHOT.jar
EXPOSE 8764
ENTRYPOINT ["java", "-jar", "springboot-conf-sts-authorization-server-db-0.0.1-SNAPSHOT.jar"]