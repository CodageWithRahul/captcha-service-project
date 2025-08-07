package com.captcha.service;

import javax.imageio.ImageIO;
import javax.servlet.http.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

/**
 * This servlet is responsible for generating a CAPTCHA image
 * and sending it as a response to the client.
 */
public class GenerateCaptchaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Handles GET requests to generate a new CAPTCHA image.
     * Steps:
     *  - Generate random CAPTCHA text.
     *  - Generate a unique ID (UUID) to associate with the CAPTCHA.
     *  - Store the CAPTCHA ID and text in the server-side store.
     *  - Generate a CAPTCHA image from the text.
     *  - Set response headers to allow CORS and prevent caching.
     *  - Send the image and CAPTCHA ID in the response.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // Generate a 5-character random CAPTCHA text
        String captchaText = CaptchaUtil.generateCaptchaText(5);

        // Create a unique ID for this CAPTCHA session
        String captchaId = UUID.randomUUID().toString();

        // Save the generated CAPTCHA text with the ID to the server memory store
        CaptchaStore.save(captchaId, captchaText);

        // Generate an image from the CAPTCHA text
        BufferedImage image = CaptchaUtil.generateCaptchaImage(captchaText);
        Boolean containRotated = CaptchaUtil.isRotated(captchaText);

        // Set response content type as PNG image;
        response.setContentType("image/png");

        // Send the CAPTCHA ID in the response header
        response.setHeader("Captcha-ID", captchaId);
        response.setHeader("is-Rotated",String.valueOf(containRotated));

        // Allow cross-origin requests (CORS headers)
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Expose-Headers", "Captcha-ID");

        // Prevent caching of CAPTCHA images
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        // Write the CAPTCHA image to the output stream
        ImageIO.write(image, "png", response.getOutputStream());
    }

    /**
     * Handles HTTP OPTIONS requests to support CORS preflight.
     * Required for browsers to make cross-origin fetch requests.
     */
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Set CORS headers
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Set 200 OK response
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
