# Api Service
API Gateway for routing spring boot microservices 

# Docker Image
rizkinu/api-service:2.3

# Environment Variable
ENV_SERVER_EUREKA: http://discovery-service:8761/eureka
ENV_SERVER_PORT:8092

# Microservices Design
![alt text](https://github.com/nugriz/software-service/blob/main/image_2023-12-10_13-28-02.png)

## Repository to services
API Gateway: https://github.com/nugriz/api-service <br />
Eureka Discovery: https://github.com/nugriz/discovery-service <br />


Hardware Service: https://github.com/nugriz/hardware-service <br />
Software Service: https://github.com/nugriz/software-service <br />

Producer (Kafka): https://github.com/nugriz/producer <br />
Consumer (Kafka): https://github.com/nugriz/consumer <br />

Grafana-Prometheus: https://github.com/nugriz/grafana-prometheus-k8s
 

