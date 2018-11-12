												***************** README ***********************

Steps to followed to fill the PO AEM form using the Automation script(Urgent and Standard)


															Pre Requisites
															
															
Maven Installation
	To install Apache Maven on Windows, you just need to download the Maven’s zip file, and Unzip it to the directory you wish to install, 
and configure the Windows environment variables.

1. Make sure JDK (version 1.7 or above) is installed, and “JAVA_HOME” variable is added as Windows environment variable.

2. Visit Maven official website, download the Maven zip file, for example : apache-maven-3.2.2-bin.zip. Unzip it to the folder you want to    install Maven.
   Assume you unzip to this folder – C:\Program Files\Apache\maven
   
3. Add both M2_HOME and MAVEN_HOME variables in the Windows environment, and point it to your Maven folder : C:\Program Files\Apache\maven

4.Update PATH variable, append Maven bin folder – %M2_HOME%\bin, so that you can run the Maven’s command everywhere

5.Done, to verify it, run mvn –version in the command prompt.
  output: C:\Users\mkyong>mvn -version
          Apache Maven 3.2.2 (45f7c06d68e745d05611f7fd14efb6594181933e; 2014-06-17T21:51:42+08:00)
          Maven home: C:\Program Files\Apache\maven
          Java version: 1.7.0_65, vendor: Oracle Corporation
          Java home: C:\Program Files\Java\jdk1.7.0_65\jre
          Default locale: en_US, platform encoding: Cp1252
          OS name: "windows 8.1", version: "6.3", arch: "amd64", family: "windows"



															TestData Setup
															
1. Fill the configuration sheet , location : \src\main\java\com\qa\purchaseorder\testdata 
2. Fill the Test Data sheet, Location : \src\main\java\com\qa\purchaseorder\properties

																Process
1. open cmd
2. Mount to the project location (to the level where you could spot pom.xml)
3. Trigger mvn clean test


Please contact if you wish to handle the 2 factor with the script,involves adding of the secret key as part of the code.
kindly add the ethernet IP of your machine to the whitelist(contact "Garvit Gor - QA IAM Team") as the page fails to load when login operations are performed recursively.

 