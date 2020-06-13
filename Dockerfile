FROM openjdk:14-alpine
COPY build/libs/balance-*-all.jar balance.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "balance.jar"]