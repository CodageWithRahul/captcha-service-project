# Captcha-service-project
A secure and reusable CAPTCHA service built with Java Servlets to generate and validate CAPTCHA images for any web application.

## Developed By

Rahul Gupta  
BCA Student, Amity University Online  

## Technologies Used

- Java 8  
- Servlet API  
- Apache Tomcat Server v9.0
- Eclipse IDE  
- HTML5, CSS3, JS

## Features

- Dynamic CAPTCHA image generation**
- One randomly rotated character** for enhanced security
- No exposure of original CAPTCHA text to frontend**
- Backend-only validation logic**
- Integrated with a secure user data collection form**

## How to Run

Requirements:
  - Java 8 or above
  - Apache Tomcat v9.0.x (Do not use Tomcat 10 or above)

There are two ways to run this project:

Option 1: Using WAR File (Recommended)

This is the easiest way to deploy and test the CAPTCHA service.

Steps:

  - Go to the Releases section of the repository.
  - Download the CaptchaService.war file.
  - Copy the WAR file to the webapps directory of Apache Tomcat v9.0.
      Make sure you're using Tomcat v9.0.x.
      Higher versions (like v10+) may cause compatibility issues due to the javax.servlet package changes.
  - Start the Tomcat server.
  - Open your browser and navigate to:
      http://localhost:8080/CaptchaService



Option 2: Run from Eclipse (For Development & Testing)

  If you prefer to run and test the project directly from your IDE:

Prerequisites:
  - Java 8 or above
  - Apache Tomcat v9.0 configured in Eclipse
  - Eclipse IDE for Enterprise Java Developers

Steps:

Import the Project:
  - Open Eclipse.
  - Go to File → Import → Existing Maven Projects.
  - Browse to your project folder (captcha-service-project) and click Finish.
Set the Target Runtime:
  - Right-click the project → Properties → Targeted Runtimes.
  - Select Apache Tomcat v9.0.
Add Tomcat Server:
  - In Servers tab → New → Server → Apache → Tomcat v9.0.
  - Choose the same Tomcat installation directory.
Run the Project:
  - Right-click on the project → Run As → Run on Server.
Access the Web App:
  - Once deployed, open your browser and visit:
      http://localhost:8080/CaptchaService
---

## Use Case
This CAPTCHA service can be integrated with any basic web form to prevent spam or bot submissions.
Where it can be used:
- Contact forms to avoid spam messages
- Registration forms so only real people can sign up
- Login pages for extra protection (if needed)
- Feedback or suggestion forms to make sure real users are submitting
- Any basic form where you want to check if a human is using it
