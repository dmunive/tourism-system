# Tourism System

## Components:
1. check-in-service: Service to check in an owner for a reservation.
2. check-in-subscriber-service: Service to process the check in.
3. owner-service: Service for the reservation owner.
4. reservation-service: Service for reservations.

## Run Rabbit MQ
Execute the following commands:
> docker pull rabbitmq:3-management <br/>
> docker run --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:3-management <br/>

Default credential for Rabbit MQ:
> username: guest <br/>
> password: guest <br/>

## Configuration:
For the mail messages, it's necessary to modify check-in-subscriber-service's application.properties. <br/>
Replace: <br/>
- \#username\# <br/> 
- \#password\# <br/>

## Comments:
- A dependency to handle errors was used:  https://github.com/alimate/errors-spring-boot-starter
- A microservice architecture was applied.
- The project is a multi-module maven project.