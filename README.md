# Spring Boot 2 项目初始化模版

基于 Java SpringBoot 的项目初始模板，整合了常用框架和主流业务的示例代码。

- [Spring Boot 2 项目初始化模版](#Spring Boot 2 项目初始化模版)
    - [模版特点](#模版特点)
        - [主流框架&特性](#主流框架&特性)
        - [数据存储](#数据存储)
        - [工具类](工具类)
    - [业务特性](#业务特性)
    - [业务功能](业务功能)
    - [快速上手](快速上手)
        - [MySQL 数据库](#MySQL数据库)

## 模版特点

### 主流框架&特性

- Spring Boot 2.7.x
- SSM 架构
- MyBatis 3.x
- MyBatis-Plus 3.x (开启分页)

### 数据存储

- MySQL 8.x 数据库
- Redis 6.x 缓存数据库

### 工具类

- Swagger + Knife4j 4.x 接口文档
- Hutool 5.x 工具库
- MapStruct 1.x 映射工具
- guava 32.x 缓存工具
- Apache Commons Lang3 工具类
- Java Validation 3.x 校验工具
- lombok 1.18.x 注解工具
- Sa-Token 1.36.0 权限认证框架

## 业务特性

- 参数校验
- 全局异常处理器
- 全局跨域处理
- 自定义异常处理
- 自定义错误码
- 封装通用请求、响应类
- 使用 Sa-Token 实现用户登录、权限认证
- 多环境配置
- 防止 Sql 注入工具类

## 业务功能

- 用户注册、登录、注销、查询、权限认证
- 帖子创建、删除、更新、分页查询

## 快速上手

### MySQL数据库

1. 修改 application.yml 的数据库配置为你自己的：

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/my_db
    username: root
    password: 123456
```

2. 执行 sql/init.sql 中的数据库语句，自动创建库表
3. 启动项目，访问 http://localhost:8102/api/doc.html 即可打开接口文档，不需要写前端就能在线调试接口了

