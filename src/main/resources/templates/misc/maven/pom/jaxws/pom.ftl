<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

  <groupId>${pomInfo.groupID}</groupId>
  <artifactId>${pomInfo.artifactID}</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>${pomInfo.name}</name>
    <url>${pomInfo.url}</url>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.5</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.sun.xml.ws</groupId>
            <artifactId>jaxws-rt</artifactId>
            <version>2.1.5</version>
        </dependency>

    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.jvnet.jax-ws-commons</groupId>
                <artifactId>jaxws-maven-plugin</artifactId>
                <version>2.2</version>	
	
                <executions>
                    <execution>
                        <goals>
                            <goal>wsimport</goal>
                        </goals>
                        <configuration>
                            <wsdlDirectory><#noparse>${basedir}</#noparse>/src/main/resources/wsdl</wsdlDirectory>
                            <genJWS>false</genJWS>
                            <packageName>${pomInfo.packageName}</packageName>
                            <xnocompile>false</xnocompile>
                        </configuration>
                    </execution>		
                </executions>
            </plugin>
        </plugins>
    </build>

</project>
