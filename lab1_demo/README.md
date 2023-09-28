## LAB1

### Requirement
#### 使用：
>* Java 17
#### 开发：
>* Mavean
>* Spring boot=3.1.4
>>SPRING 依赖：
>>* JPA
>>* H2
>>* Web
### Usage

```bash
#克隆本仓库的代码
git clone git@codeup.aliyun.com:6507a61595afb811ce327e57/11.git
cd 11
#切换到songmingyag_develop分支
git checkout songmingyang_develop
#开启后端(MacOS)
sh ./mvnw clean spring-boot:run
#进行测试
curl -v localhost:8080/api/v1/user/all
```
### 测试接口
```bash
#插入用户(POST)：
curl -X POST localhost:8080/api/v1/user -H 'Content-type:application/json' -d '{"name": "Samwise Gamgee", "gender": "M","age":28}'

#查找全体用户：（GET方法）
curl -v localhost:8080/api/v1/user/all

#查找某一个用户（GET方法）
curl -v localhost:8080/api/v1/user/{id}

#修改用户信息(PUT方法)
curl -X PUT localhost:8080/api/v1/user/{id} -H 'Content-type:application/json' -d '{"name": "Samwise Gamgee","gender": "M","age":28}'

#删除用户信息(DELETE)
curl -X DELETE localhost:8080/api/v1/user/{id}
```

### 前端页面访问
```bash
#显示所有学生：
localhost:8080/show
# 添加学生
localhost:8080/add
#查找、更新、删除在同一个页面
注意由于不是持久化的数据库，重新启动项目后添加的数据将会清除
localhost:8080/search
```


```bash
#若查找不到返回404及提示消息：
> GET http://localhost:8080/api/v1/user/99 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.1.2
> Accept: */*
> Proxy-Connection: Keep-Alive
>
< HTTP/1.1 404
< Content-Length: 26
< Connection: keep-alive
< Content-Type: text/plain;charset=UTF-8
< Date: Mon, 25 Sep 2023 16:20:48 GMT
< Keep-Alive: timeout=4
< Proxy-Connection: keep-alive
<
* Connection #0 to host 127.0.0.1 left intact
  Could not find employee 99%
```
### 实验要求
[实验要求链接](https://openmsg.yuque.com/krcw6r/ox372z/dphk2drit7ug3wgx?singleDoc#)

### TODO
- [x] V1版本要求(实验文档要求)
- [ ] V2版本要求(9.25课上新增内容)
### 项目文档
[项目文档链接](
https://docs.qq.com/doc/DYUlzaXRselFTeFhV?scene=0265cf9576c3e8ad9daf92b23eVnq1)
![avatar](../img/LAB1_doc.png)
### 测试结果
![avatar](../img/test1.png)
![avatar](../img/test2.png)
![avatar](../img/test3.png)
![avatar](../img/test4.png)
![avatar](../img/test5.png)





