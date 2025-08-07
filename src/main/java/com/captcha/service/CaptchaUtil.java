package com.captcha.service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CaptchaUtil {

    /**
     * Generates a random alphanumeric string of given length to be used as CAPTCHA text.
     * @param length Desired length of the CAPTCHA string.
     * @return Randomly generated CAPTCHA string.
     */
    public static String generateCaptchaText(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder captchaStr = new StringBuilder();
        Random rnd = new Random();

        // Keep appending random characters until desired length is reached
        while (captchaStr.length() < length) {
            int index = (int) (rnd.nextFloat() * chars.length());
            captchaStr.append(chars.charAt(index));
        }
        return captchaStr.toString();
    }

    /**
     * Generates a CAPTCHA image from the provided text.
     * Applies distortion, noise, and optional character flipping to make it hard for bots to decode.
     * @param captchaText CAPTCHA text to be drawn on the image.
     * @return BufferedImage object containing the CAPTCHA.
     */
 
    public static BufferedImage generateCaptchaImage(String captchaText) {
        int width = 180;
        int height = 60;

        // Create a blank image with RGB color model
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();

        // Set white background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // Draw black border around the image
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);

        // Configure font and anti-aliasing for smooth text
        Font font = new Font("Arial", Font.BOLD, 36);
        g.setFont(font);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Random rand = new Random();

        // Define characters that are still human-readable even when flipped vertically
        String rotatable = "EFAGJNQRTUYZ";

        // Find the first rotatable uppercase character to flip in the CAPTCHA
        int flippedIndex = -1;
        for (int i = 0; i < captchaText.length(); i++) {
            char c = captchaText.charAt(i);
            if (Character.isUpperCase(c) && rotatable.indexOf(c) != -1) {
                flippedIndex = i;
                break;
            }
        }

        // If no rotatable char found, randomly select one (flip will not apply if not flippable)
        if (flippedIndex == -1) {
            flippedIndex = rand.nextInt(captchaText.length());
        }

        // Draw each character on the image with slight rotation or flipping
        int charX = 15; // Initial X position
        for (int i = 0; i < captchaText.length(); i++) {
            char c = captchaText.charAt(i);
            int charY = 40 + rand.nextInt(10); // Slight vertical variation

            // Random color for each character
            g.setColor(new Color(rand.nextInt(100), rand.nextInt(100), rand.nextInt(100)));

            // Random rotation angle between -15° to 15°
            double angle = Math.toRadians(rand.nextInt(30) - 15);
            boolean flip = (i == flippedIndex && rotatable.indexOf(c) != -1);

            // Apply flipping or rotation
            if (flip) {
                g.rotate(Math.PI, charX + 10, charY - 20); // Flip 180 degrees
            } else {
                g.rotate(angle, charX + 10, charY - 20);   // Rotate slightly
            }

            // Draw the character
            g.drawString(String.valueOf(c), charX, charY);

            // Reset the rotation
            if (flip) {
                g.rotate(-Math.PI, charX + 10, charY - 20);
            } else {
                g.rotate(-angle, charX + 10, charY - 20);
            }

            // Move X position for the next character
            charX += 25;
        }

        // Draw random lines for background noise
        for (int i = 0; i < 30; i++) {
            g.setColor(new Color(rand.nextInt(200), rand.nextInt(200), rand.nextInt(200)));
            int x1 = rand.nextInt(width);
            int y1 = rand.nextInt(height);
            int x2 = rand.nextInt(width);
            int y2 = rand.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }

        // Draw random circles for additional noise
        for (int i = 0; i < 20; i++) {
            g.setColor(new Color(rand.nextInt(200), rand.nextInt(200), rand.nextInt(200)));
            int r = rand.nextInt(15); // radius
            int x = rand.nextInt(width - r);
            int y = rand.nextInt(height - r);
            g.drawOval(x, y, r, r);
        }
        
        

        // Finalize the image
        g.dispose();
        return bufferedImage;
    }
    
    public static Boolean isRotated(String captchaText) {
    	Boolean rotated = false;
    	String rotatable = "EFAGJNQRTUYZ";
    	  for (int i = 0; i < captchaText.length(); i++) {
              char c = captchaText.charAt(i);
              if (Character.isUpperCase(c) && rotatable.indexOf(c) != -1) {
            	  rotated = true;
            	  break;
              }
          }
    	  return rotated;
    }
}
