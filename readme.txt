1. 构建Maven项目的完整过程--普通web项目（Eclipse）
https://www.cnblogs.com/wbyp/p/7392681.html
2. 问题记录
	2.1 The superclass "javax.servlet.http.HttpServlet" was not found on the Java Build Path
		http://blog.csdn.net/testcs_dn/article/details/36455669

	2.2
		<!-- https://mvnrepository.com/artifact/org.testng/testng -->
		<dependency>
		    <groupId>org.testng</groupId>
		    <artifactId>testng</artifactId>
		    <version>6.11</version>
		    <scope>test</scope>
		</dependency>

	2.3 Can not find the tag library descriptor for “http://java.sun.com/jstl/core”
		<dependency>
		    <groupId>javax.servlet</groupId>
		    <artifactId>jstl</artifactId>
		    <version>1.2</version>
		</dependency>
	2.4 Description	Resource	Path	Location	Type Java compiler level does not match the version of the in解决方法
		http://blog.csdn.net/u013412790/article/details/51501697
	2.5 web.xml这里的配置要和Project Facts里的Dynamic Web Module保持一致， 本demo都是3.1
		<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
         xmlns="http://xmlns.jcp.org/xml/ns/javaee" 
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" 
         id="WebApp_ID" version="3.1">

