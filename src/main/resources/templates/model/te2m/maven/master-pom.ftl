<?xml version="1.0" encoding="UTF-8"?>
<!-- 
${info.copyrightInfo}
-->
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>te2m-services-core</groupId>
    <artifactId>${bo.javaName?lower_case}</artifactId>
    <version>1.0-SNAPSHOT</version>
    <parent>
        <groupId>te2m-services</groupId>
        <artifactId>core</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <properties>
    </properties>
    <name>te2m.de ${bo.javaName} Services (Parent)</name>
    <modules>
        <module>common</module>
        <!-- <module>rest</module> -->
        <module>mgmt</module>
        <module>api</module>
    </modules>
    <packaging>pom</packaging>
</project>