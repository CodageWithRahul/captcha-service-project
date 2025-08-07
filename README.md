Captcha Service Project
A secure and reusable CAPTCHA service built using Java Servlets to generate and validate CAPTCHA images for web applications.

👨‍💻 Developed By
Rahul Gupta
BCA Student, Amity University Online

🛠 Technologies Used
Java 8

Servlet API

Apache Tomcat Server v9.0

Eclipse IDE

HTML5, CSS3, JavaScript

✨ Features
Dynamic CAPTCHA image generation

Random character rotation for added security

No exposure of original CAPTCHA text to the frontend

Secure backend-only validation

Fully integrated with a demo user input form

🚀 How to Run
✅ Requirements:
Java 8 or above

Apache Tomcat v9.0.x (Do not use Tomcat 10 or above)

🔹 Option 1: Using WAR File (Recommended)
This is the easiest way to deploy and test the CAPTCHA service.

Steps:

Go to the Releases section.

Download the CaptchaService.war file.

Copy the WAR file into the webapps folder of your Apache Tomcat v9.0.

Start the Tomcat server.

Open your browser and go to:
http://localhost:8080/CaptchaService

⚠️ Make sure you're using Tomcat v9.0.x. Tomcat 10+ is not compatible due to javax.servlet package changes.

🔹 Option 2: Run from Eclipse (For Development & Testing)
If you prefer to test and run the project within your IDE:

Prerequisites:
Java 8 or above

Apache Tomcat v9.0 configured in Eclipse

Eclipse IDE for Enterprise Java Developers

Steps:

Import the Project

File → Import → Existing Maven Projects

Select your folder (captcha-service-project) and click Finish

Set Target Runtime

Right-click the project → Properties → Targeted Runtimes

Select Apache Tomcat v9.0

Add Tomcat Server

Go to Servers tab → New → Server → Apache → Tomcat v9.0

Select your Tomcat directory

Run the Project

Right-click the project → Run As → Run on Server

Visit the App

In browser:
http://localhost:8080/CaptchaService

📌 Use Case
This CAPTCHA service helps protect basic web forms from bots and spam. It can be used in:

Contact forms (to avoid spam messages)

Signup or registration forms

Login pages (optional extra layer)

Feedback or suggestion forms

Any form where human verification is needed
