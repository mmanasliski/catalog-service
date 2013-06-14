UMFlix - Catalog Service
===============

This project has the interface for CatalogService and an implementation.
The CatalogService service provides information about UMFlix's movies.

Requirements:

    - Apache Maven 3.0.5, MAVEN_HOME/bin added to the classpath.

   UMFlix dependencies that have to be previously installed with maven*:

    - umflix-persistence version 1.0-SNAPSHOT
    - autenticationhandler version 1.0-SNAPSHOT

*For installing them, run command "mvn install" in the dependency directory.

Instructions for creating Web Service:

1) Install Apache Tomcat and add TOMCAT_HOME_DIR/bin directory to the classpath
2) Modify the pom.xml file located in the Catalog Service directory:
        -Comment the line <packaging>jar</packaging> (line 10) changing it to <!-- <packaging>jar</packaging> -->
        -Uncomment the line <packaging>war</packaging> (line 12) deleting the <!-- and --> symbols.
3) Run the command "mvn install" in the Catalog Service directory.
4) Copy the war file generated (SERVICE_HOME_DIR/target/catalog-service-1.0-SNAPSHOT.war) into the TOMCAT_HOME_DIR/webapps directory
5) Run "startup" to start Tomcat Server. It will automatically deploy the web service from the war file.


Instructions for obtaining the wsdl file for the Web Service:

a) Run:
wsimport -s http://localhost:8080/catalog-service-1.0-SNAPSHOT/webservices/CatalogServiceImpl?wsdl

OR

b) Open a web browser and go to the URL: http://localhost:8080/catalog-service-1.0-SNAPSHOT/webservices/CatalogServiceImpl?wsdl






