# Qm-Framework

![logo](./logo.png)

## 1. Maven Warehouse

> 中央仓库查询：https://search.maven.org/

> 阿里仓库查询：https://maven.aliyun.com/mvn/search

> Maven镜像仓库查询：https://mvnrepository.com/artifact/com.starmcc

```xml
<dependency>
  <groupId>com.starmcc</groupId>
  <artifactId>qm-framework</artifactId>
  <version>x.x.x-RELEASE</version>
</dependency>
```

## 2. Preface

### *2.1 Help Document*

[**Show Document**](http://www.starmcc.com/qm-framework/)

### *2.2 Update Version Log*

[**Show Version**](http://www.starmcc.com/qm-framework/UpdateLog.html)

## 3. Environment

* java - JDK 1.8 above
* Maven 2.0 above
* SpringBoot 2.5.5 above

## 4. Package Content

4.1 Redis缓存服务

> 良好的 `QmRedisService` 优雅的使用缓存服务

4.2 全局异常捕获返回 Json 信息

> 在服务器发生任何错误都将返回规范化`Json`格式的字符串。

4.3 重写 RequestBody 实现自定义注解 @QmBody

> 摒弃 `@RequestBody` , 在请求时对`Body`中的`Json`数据进行自动装配, 即传递Json时使用 `@QmBody` 优雅的接收参数。

4.4 快速拓展AOP业务场景

> 在方法上添加`@Agent`实现对方法快速AOP代理

4.5 ...有待更多发现