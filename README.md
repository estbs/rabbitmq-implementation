# rabbitmq-implementation

This is a basic implementation of RabbitMQ using spring boot 2.2.7 (JDK Java 11) and RabbitMQ 3.8.3 on Docker 19.03.12.

# Download rabbitmq docker from docker hub.
1. Pull rabbitmq from docker hub: docker pull rabbitmq
2. run command: docker run -d --name rabbitmq -e RABBITMQ_DEFAULT_USER=ebs-rabbitmq -e RABBITMQ_DEFAULT_PASS=EbsRabbit22 -p 15672:15672 -p 5672:5672 rabbitmq:3-management
    - rabbitmq: Specified name to the container.
    - RABBITMQ_DEFAULT_USER: User to rabbitmq. Default: guest
    - RABBITMQ_DEFAULT_PASS: Password to the user. Default: guest
3. Go to http://localhost:15672 and login with the previous user and password.
4. Create an user with the tag management through rabbit cli with the credentials set on application.yml
5. Create a virtual host through rabbit cli with the name set on application.yml
6. Asociate the user to the virtual host created.

# Bibliography
- https://hub.docker.com/_/rabbitmq
