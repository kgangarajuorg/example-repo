# spring-cloud-netflix-apps

Building apps using microservices patterns for cloud (cloud native apps)

I was facing problem in basic retry from zuul, when one request failed because of some microservice failure (one of the fligts microsevice was down) zuul has to retry with other services in cluster.  I was able to achieve this by adding spring-retry library to the classpath.


we've three microservice here

- eureka-restry (servicer registry)
- zuul-gate (gateway app acts as reverse proxy)
- flights (simple rest based micro service)




### eureka
``` gradle clean build```


``` java -jar build/lib/*.jar```

### Zuul Gate

```gradle clean build ```

``` java -jar build/lib/*.jar ```




### flights
``` gradle clean build ```

Run 3 instances of flights

```java -jar -Dserver.port=8090 build/lib/*.jar```

```java -jar -Dserver.port=8091 build/lib/*.jar```

```java -jar -Dserver.port=8092 build/lib/*.jar```


##Testing

repeatedly send requests

```http://localhost:8080/api/flights/something?origin=hyd&destination=bos```

observe that requests are served in Round Robin fashion.

now make one of the flights microservice down and send requests repeatedly, but still gateway is going to serve the requests, because of retry logic




#Microservices

- UI info provider
- Auth
- Flight list
- Flight details
- Checkout
- Passenger info
- Profile service
- Payment Service
- Booking Service
- Itinery Service
- Email Service
- Policy Service
- Loyalty service
