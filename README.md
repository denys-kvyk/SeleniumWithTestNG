# TechnicalExercise
Technical exercise using Selenium and Testng

The Project is on automating a travel booking website.

Selenium-java is used along with Testng,log4j and maven.

The folder structure generated using  mvn archetype:generate  -DarchetypeGroupId=org.apache.maven.archetypes   -DgroupId=com.demo.<folderName>   -DartifactId=<foldername>

mvn takes care of all the dependencies.
This is handled using the pom.xml file.

Firefox driver is used to interact with the firefox browser.
The version of firefox required is anything lesser than 47.

Java 7 with selenium 2.53.0 is used here.


Page object model is the framework used
The WebdriverManager takes care of all the driver related activities.
The tests falls in SortingTest folder
All the Pages realted actions are captured under files in Pages folder.


The index.html generated by Testng shows the results for to the tests written
In addition log4j is used as a logging mechanim. The threshold set at INFO logs only the ones we log through the tests.
log4j is configured to log both on console and in file.


mvn clean install will install all the dependencies
mvn clean test will run the test suite
