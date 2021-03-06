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
* SpringBoot 2.3.4 above

## 4. Package Content

4.1 Redis缓存客户端

> 直接调用`QmRedisClient`即可实现`key`、`value`的存储

4.2 全局异常捕获返回JSON信息

> 在服务器发生任何错误都将返回规范化`JSON`格式的字符串。

4.3 贯彻使用JSON数据传递

> 在`Controller`中继承`QmController`调用规定的方法。

4.4 重写RequestBody实现AES双向对称加密技术

> 在请求时拦截，获取`body`参数并对其进行解密格式化，把格式化后的`body`原路放置。

4.5 RequestBody自定义注解@QmBody

> 在请求时对请求`body`中的`json`数据进行自动装配,在`controller`中参数列表可直接获取对应类型的参数。

4.6 快速拓展AOP业务场景 (New)

> 在方法上添加`@Agent`实现对方法快速AOP代理

4.7 精简快速的异步事件拓展 (New)

> 定义一个事件或异步事件，只需实现函数式接口`QmEventInterface`，在需要的地方使用`QmEventManager`。