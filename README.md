# urlShortener
Rest API to generate a tinyUrl for a long URL :This is a small URL shortening REST application.
The application support two HTTP methods. POST & GET. The application has embedded tomcat server running on default port no 8080.
This can be changed if desired from the application.properties file by specifying server.port = PORTNO.

For POST method the request has to be in the below format.
===========================================================
localhost:8080/rest/url and specify the long url in the request body as raw text. This will generate and return a short URL 


For GET method the request has to be in the below format.
===========================================================
localhost:8080/rest/url/shortUrl where shortUrl is the short URL generated by POST method 
This will return the original corresponding long URL for the shortURL specified.




