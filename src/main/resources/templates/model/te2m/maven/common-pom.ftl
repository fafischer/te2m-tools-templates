<?xml version="1.0" encoding="UTF-8"?>
<!-- 
${info.copyrightInfo}
-->
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>te2m-services-core</groupId>
        <artifactId>${bo.javaName?lower_case}</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <groupId>te2m-services-core-${bo.javaName?lower_case}</groupId>
    <artifactId>api</artifactId>
    <name>te2m Common Web Resources</name>
    <description>Common resources for ${bo.javaName} handling web applications</description>
    <packaging>jar</packaging>
    <dependencies>
        <dependency>
            <groupId>javax</groupId>
            <artifactId>javaee-web-api</artifactId>
            <version>7.0</version>
            <type>jar</type>
        </dependency>
    </dependencies>
</project>