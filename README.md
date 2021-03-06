# Spring 4 MVC ToDo Web App

This is a simple Eclipse Maven Spring 4 MVC ToDo list web app project.  It uses basic Spring-Maven dependency management 
instead of Spring Boot.  The idea is to use this project for comparison with Spring Boot to better understand its 
auto configuration behavior.

## Features
* ToDo app has both JSP views and a client-facing REST API.
* Embedded Tomcat 8x servlet container.  It will auto deploy and run the project's war file (mvn cargo:run).
* Spring MVC and Mockito mock test coverage of controller end points.
* In-memory ToDo repository for simplicity.

## Usage
* Clone project
* Import as Maven project into Eclipse
* mvn install clean compile package cargo:run
* http://localhost:9000 to view home page

 