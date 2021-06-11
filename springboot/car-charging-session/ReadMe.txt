Information about system:
	1. Server is running on port 8082
	2. Sample end point to retrieve all: http://localhost:8082/chargingSessions
	
How to Run?
	1. Go to project directory.
	2. Create executable with: mvn clean package
	3. change directory to target folder.
	4. run command: java -jar car-charging-session-0.0.1-SNAPSHOT-spring-boot.jar
	
Documentation:
	1. openapi: http://localhost:8082/v3/api-docs or http://localhost:8082/v3/api-docs.yaml
	2. swagger: http://localhost:8082/swagger-ui.html
	3. javadoc: to create java doc run command: mvn javadoc:javadoc
		3.1. javadoc is created under target/site/apidocs directory. index.html can be used for navigation
		
Test:
	1. Spring boot tests are implemented

Assumptions:
	1. No security is used as there is no requirement for that. 
	2. HTTP is used as there is no requirement for HTTPs. 
	3. Station ID is not handled as unique as it is not in requirements and we have id
	4. If you try to stop stopped session you will get error