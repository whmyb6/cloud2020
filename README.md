# cloud2020

Spring Cloud 全家桶微服务实战教学项目。

## 组件清单

| 模块 | 技术 | 说明 |
|------|------|------|
| Eureka Server/Client | 服务注册发现 | Netflix Eureka |
| Config Server/Client | 配置中心 | Spring Cloud Config |
| Gateway | 网关路由 | Spring Cloud Gateway |
| Hystrix Dashboard | 熔断监控 | Netflix Hystrix |
| OpenFeign | 远程调用 | Feign + LoadBalancer |
| Stream RabbitMQ | 消息驱动 | Spring Cloud Stream |
| Nacos | 注册中心/配置中心 | Alibaba Nacos |
| Sentinel | 流量控制/熔断降级 | Alibaba Sentinel |
| Seata | 分布式事务 | Alibaba Seata |
| Sleuth + Zipkin | 链路追踪 | 分布式调用链 |

## 技术栈

- Spring Boot 2.2.x / 2.3.x
- Spring Cloud Hoxton.SRxx
- Spring Cloud Alibaba 2.2.x
- MySQL + MyBatis
- RabbitMQ
- Maven 多模块

## 快速开始

```bash
# 启动 Eureka 注册中心
cd cloud-eureka-server7001
mvn spring-boot:run

# 启动 Config 配置中心
cd cloud-config-center3344
mvn spring-boot:run

# 启动业务服务
cd cloud-provider-payment8001
mvn spring-boot:run
```
