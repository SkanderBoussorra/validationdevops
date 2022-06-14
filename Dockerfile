FROM openjdk:11
EXPOSE 8547
ADD target/food-order-manager-0.0.1-SNAPSHOT.jar food-order-manager-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/food-order-manager-0.0.1-SNAPSHOT.jar"]
