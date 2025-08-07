package com.captcha.service;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet to validate CAPTCHA input from the user.
 * Accepts POST requests with JSON data containing captcha_input and captcha_id.
 */
public class ValidateCaptchaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Handles POST requests for CAPTCHA validation.
     * Expects JSON input: { "captcha_input": "abc12", "captcha_id": "UUID-1234" }
     * Responds with JSON indicating success or failure.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // Enable CORS headers to allow requests from any domain (frontend compatibility)
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setContentType("application/json"); // Set response content type as JSON
        response.setCharacterEncoding("UTF-8");

        // --- Read JSON payload from request body ---
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        // --- Parse the JSON request using Gson library ---
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(sb.toString(), JsonObject.class);

        // Extract user-entered CAPTCHA and the unique captcha ID from JSON
        String userInput = json.get("captcha_input").getAsString();
        String captchaId = json.get("captcha_id").getAsString();

        // Fetch original CAPTCHA value stored during generation
        String originalCaptcha = CaptchaStore.get(captchaId);

        // Create JSON response object to send result back to client
        JsonObject jsonResponse = new JsonObject();

        if (originalCaptcha != null && originalCaptcha.equals(userInput)) {
            // CAPTCHA is correct
            jsonResponse.addProperty("success", true);
            jsonResponse.addProperty("message", "CAPTCHA verified successfully.");
            
            // Remove the used CAPTCHA from store to prevent reuse
            CaptchaStore.remove(captchaId);
        } else {
            // CAPTCHA is incorrect or expired
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Invalid CAPTCHA.");
        }

        // Send JSON response back to client
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(jsonResponse));
        out.flush();
    }

    /**
     * Handles CORS pre-flight requests (used in modern browsers for cross-origin POST).
     */
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setStatus(HttpServletResponse.SC_OK); // Indicate pre-flight success
    }
}
