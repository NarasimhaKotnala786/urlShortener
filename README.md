# urlShortener
Rest API to generate a tinyUrl for a long URL :This is a small URL shortening REST application.
The application support two HTTP methods. POST & GET. The application has embedded tomcat server running on default port no 8080.
This can be changed if desired from the application.properties file by specifying server.port = PORTNO.

Prerequisites:
==============
This application uses Redis to persist the data. Before starting the application Redis needs to be installed.
Following are the instructions to install Redis on a mac.
1. Install Redis:
   brew install redis

2. Start Redis server via “launchctl”:
   launchctl load ~/Library/LaunchAgents/homebrew.mxcl.redis.plist

3. Start Redis server using configuration file:
   redis-server /usr/local/etc/redis.conf

4. Test if Redis server is running:
   redis-cli ping

   If it replies “PONG”, then it’s good to go!

How to run the application
==========================
1. Run the main method of the UrlShortenerApplication class
2. Also can be run from commmand line. cd into the urlShortener directory after cloning the project. Make sure you are in the same
   directory where the pom.xml resides and run the below command.
   mvn spring-boot:run
   
The above ways the Springboot application can be run.If the sprinboot application has started successfully then the following messages
should be seen.

2021-06-06 16:54:12.041  INFO 42816 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode!
2021-06-06 16:54:12.058  INFO 42816 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data Redis repositories in DEFAULT mode.
2021-06-06 16:54:12.099  INFO 42816 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 9 ms. Found 0 Redis repository interfaces.
2021-06-06 16:54:19.320  INFO 42816 --- [           main] c.b.u.UrlShortenerApplicationTests       : Started UrlShortenerApplicationTests in 9.872 seconds (JVM running for 12.932)
2021-06-06 16:54:19.325  INFO 42816 --- [           main] o.s.b.a.ApplicationAvailabilityBean      : Application availability state LivenessState changed to CORRECT
2021-06-06 16:54:19.328  INFO 42816 --- [           main] o.s.b.a.ApplicationAvailabilityBean      : Application availability state ReadinessState changed to ACCEPTING_TRAFFIC

To test it a suitable REST Client is needed. Postman or Insomnia can be used.

For POST method the request has to be in the below format.
===========================================================
localhost:8080/rest/url and specify the long url in the request body as raw text. This will generate and return a short URL 

For GET method the request has to be in the below format.
===========================================================
localhost:8080/rest/url/shortUrl where shortUrl is the short URL generated by POST method 
This will return the original corresponding long URL for the shortURL specified.

Trade-Offs
==========
Due to limited time this is the simplest solution. This can be enhanced to no end. There are a multitude of ways this
functionality can be achieved using various algorithms.Also unit testing has not been explored fully. Each HTTP method
can be unit tested with more time.

HOW TO DEPLOY on AWS
=====================
The current solutions runs as a standalone but this can be deployed on the cloud. Following are the instructions to deploy
on AWS.

First Redis needs to be installed on AWS EBS or EC2 instance. 

1. Firstly the user needs to have an AWS account or Create a Free Tier AWS Account.
2. Create a IAM user.
3. Enable billing information.
4. Activate MFA (Multi-Factor Authentication) on Root Account
5. Create Admin Account
6. Apply IAM Password Policy
7. Set a Budget
8. Run the urlShortener SpringBoot app from the command line and see if it is up and running without any errors.
9. Install and Configure EB CLI
10.Configure the EB CL
11.Deploy to AWS 
   A few things are left to do before we can deploy the Spring Boot App to Elastic Beanstalk. The Elastic Beanstalk environments run an nginx instance on port 80 to proxy the actual application, running on port 5000. Therefore, we need to set the server port to port 5000 in the applications.properties file.
    server.port=5000
Now build the application which will create the jar file target/urlShortener-0.0.1-SNAPSHOT.jar:
    $ mvn clean install
12. Create AWS environment
    eb create -s
13. Rebuild the application with mvn clean install and execute the eb deploy command in order to deploy the application:    
14. Terminate the environment.
    eb terminate urlShortener
    
ALTERNATIVE SOLUTION:
=====================
The most robust solution for this would be to deploy the SpringBoot application on AWS using Docker. Haven't provided this 
due to shortage of time but can be done.
















