# Spring-Boot-Security-JWT-MySQL

In this sample code, I am making use of JWT authentication for securing REST API.<br />

#### This integration flow diagram is an illustrated API call between layers.

![image](https://user-images.githubusercontent.com/67745525/147435733-fa0afdb4-56c7-4f20-9af5-b559f1dc298a.png)

In this sample code, Two REST API endpoints have been implemented to facilitate this use-case<br />

1. Register user (This API endpoint is whitelisted in "WebSecurityConfig")<br />
[POST]/register

2. Validate user credentials and generate JWT Token<br />
[POST]/authenticate

3. Validating JWT Token and allowing access to the desired endpoint if Request has valid JWT Token<br />
[GET]/hello

###### Technology Stack
```diff
+ Java 11
+ Spring Boot 2.5.1.RELEASE
+ Spring Boot Rest API
+ Spring security
+ JWT
+ MySQL
```
###### Dependency for Spring Boot 
```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt</artifactId>
			<version>0.9.1</version>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
```
###### Configure Properties
```
spring.datasource.url=jdbc:mysql://<ip>:3306/jwtdb
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform = org.hibernate.dialect.MySQL5Dialect
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto = update
#debug=true
server.port = 8090
jwt.secret=testjwtauth
```
###### Test using Postman
###### 1. Register user

![Jwt-create-user](https://user-images.githubusercontent.com/67745525/147435197-5a6fe7d3-636d-41b8-b97c-6eabfdee3524.png)

###### 2. Validate user credentials and get JWT Token<br />

![Jwt-authenticate-user](https://user-images.githubusercontent.com/67745525/147435241-e5462e46-ad0d-4474-af10-e0f547423b6e.png)

###### 3. Call /hello with JWT Token append in Authorization header<br />

![Jwt-token-hello](https://user-images.githubusercontent.com/67745525/147435332-734bb204-c298-4ef9-8ec9-f09fb5475891.png)

###### JSON Web Token structure
In its compact form, JSON Web Tokens consist of three parts separated by dots (.), which are:</br>
```
Header
Payload
Signature

Therefore, a JWT typically looks like the following.

xxxxx.yyyyy.zzzzz
```

###### Additional reading 

https://jwt.io/introduction
#### Done !

## Author
 * [Roshitha Wishvajith](https://github.com/rowishva?tab=repositories)


