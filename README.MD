# TODOLIST

## 01.18 认证处理

- 增加认证（done）
- 远端服务请求返回用户信息

## 配置中心

- 接入nacos
- 权限中心sdk及原理
- 配置中心sdk及原理接入

## 01.19 消息推送

- websocket链接

## 多数据源

- ~~对接多数据源~~
- ~~处理默认数据源失效问题~~
- 处理优雅统一选择某个数据源
- 了解spring mybatis和原生的jdbc connection的区别

## 线程池ThreadPool

## 01.17 代码优化

- 全局异常捕获通过动态代理来处理（不是用instance of）
- 重写 generatorconfig以符合生成文件符合swagger标准

## 01.17 深入理解拦截器和过滤器的区别及应用

## 01.17 常用util类及组件沉淀

- http请求

  - 使用httpcomponents

  ```xml
  <dependency>
    <groupId>org.apache.httpcomponents.client5</groupId>
    <artifactId>httpclient5</artifactId>
    <version>5.1.2</version>
  </dependency>
  
  <dependency>
    <groupId>org.apache.httpcomponents.client5</groupId>
    <artifactId>httpclient5-fluent</artifactId>
    <version>5.1.2</version>
  </dependency>
  ```

  - 使用继承好的包装类

  

- 字符串通用类

- http响应码

- stringUtil

- Assert

- HttpUtil

## 01.20 redis

- 常用指令
- jdis
- lucense

## 数据门面项目 - 难点总结：

- ~~yaml解析及对应到各层的model上面~~

  - ~~直接使用依赖 snakeyaml `loadAs(String yaml, Class<T> type)`~~

- 设计模式的灵活运用，

- 

- 切换数据源获取结果

  

# 知识点：

## 1. spring 相关

### 1.1. @RequestAttribute和@RequestParam和@Param

- @RequestAttribute 适用于内置程序的参数传参，通过name映射
- @RequestParam 适用于url的传参，通过name映射
- @Param 适用于dao层的参数命名

### 1.2. @Configuration和@Component

- @Configuration
- @Compnent

### 1.3. filter和interceptor

[解答详情](https://juejin.cn/post/6844903811874553869)

### 1.4. spring cloud常用注解

- @ConfigurationProperties: 对带setter方法的类进行复制，添加会通过prefix之后的命名进行映射对应的属性值

  [使用方法](https://www.cnblogs.com/jimoer/p/11374229.html) 

- @Primary

## 2. 



## 
