# captcha-service-project
A secure and reusable CAPTCHA service built with Java Servlets to generate and validate CAPTCHA images for any web application.

## Developed By
Rahul Gupta  
BCA Student, Amity University Online  

## Technologies Used
- Java 8  
- Servlet API  
- Apache Tomcat Server  
- Eclipse IDE  
- HTML5, CSS3,JS


## Features
- Dynamic CAPTCHA image generation**
- One randomly rotated character** for enhanced security
- No exposure of original CAPTCHA text to frontend**
- Backend-only validation logic**
- Integrated with a secure user data collection form**

## How to Run
There are two ways to run this project:

Option 1: Using WAR file (Recommended)
- Go to the release folder.
- Copy the captcha-service.war file to the webapps folder of Apache Tomcat.
- Start Tomcat.
- Open your browser and visit:
- #http://localhost:8080/captcha-service

Option 2: Run from Source Code
- Import the project in Eclipse (or any Java IDE).
- Add Apache Tomcat as the server.
- Deploy the project.
- Run the server and open the form page in your browser.

## Use Case
This CAPTCHA service can be integrated with any basic web form to prevent spam or bot submissions.

  Where it can be used:
    - Contact forms to avoid spam messages
    - Registration forms so only real people can sign up
    - Login pages for extra protection (if needed)
    - Feedback or suggestion forms to make sure real users are submitting
    - Any basic form where you want to check if a human is using it

