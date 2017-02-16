<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
<!-- 
${info.copyrightInfo}
-->
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>te2m-services-core</groupId>
        <artifactId>${bo.javaName?lower_case}</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <groupId>te2m-services-core-${bo.javaName?lower_case}</groupId>
    <artifactId>api</artifactId>
    <packaging>jar</packaging>
    <name>te2m: API for ${bo.javaName} Handling</name>
    <dependencies>
        <dependency>
            <groupId>de.te2m.core</groupId>
            <artifactId>de.te2m.core.api</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
</project>