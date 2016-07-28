# testing zipkin-server and sleuth on spring-boot with eureka-server on local machine.

##  prepare jdk 1.8

## rabbitmq : default port: 5672
brew install rabbitmq
/usr/local/sbin/rabbitmq-server
http://localhost:15672/#/ guest/guest

## build
zipkin-server> mvn clean package -DskipTests

## run zipkin: will use rabbitmq, hsqldb
zipkin-server> mvn spring-boot:run
http://localhost:9411/

## run eureka
eureka-server> mvn spring-boot:run
http://localhost:8761/

## run apps
service1 > mvn spring-boot:run -Dserver.port=8081
service2 > mvn spring-boot:run -Dserver.port=8082
service3 > mvn spring-boot:run -Dserver.port=8083
service3 > mvn spring-boot:run -Dserver.port=8084

and check if all server are registered to eureka-server
http://localhost:9411/

## testing
call following api several times.(5~10 times)

curl http://localhost:8081/call
curl http://localhost:8081/call-timeout

and see call trace in zipkin server
http://localhost:9411/


## link
https://github.com/spring-cloud/spring-cloud-sleuth
http://cloud.spring.io/spring-cloud-sleuth/spring-cloud-sleuth.html







