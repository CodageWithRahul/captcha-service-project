package com.captcha.service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * CaptchaStore is a utility class used to temporarily store and manage CAPTCHA codes.
 * It uses a thread-safe ConcurrentHashMap to associate a unique ID (like session ID or token)
 * with the generated CAPTCHA text.
 */
public class CaptchaStore {

    // Thread-safe map to store CAPTCHA text against unique IDs (like session ID)
    private static final ConcurrentHashMap<String, String> storeCaptcha = new ConcurrentHashMap<>();

    /**
     * Saves the CAPTCHA text associated with a unique ID.
     *
     * @param id           the unique identifier (e.g., session ID or UUID)
     * @param captchaText  the CAPTCHA string to be validated later
     */
    public static void save(String id, String captchaText) {
        storeCaptcha.put(id, captchaText);
    }

    /**
     * Retrieves the CAPTCHA text for the given ID.
     *
     * @param id  the unique identifier used during CAPTCHA generation
     * @return the stored CAPTCHA text or null if not found
     */
    public static String get(String id) {
        return storeCaptcha.get(id);
    }

    /**
     * Removes the CAPTCHA entry after validation or expiration.
     *
     * @param id  the unique identifier for which CAPTCHA should be removed
     */
    public static void remove(String id) {
        storeCaptcha.remove(id);
    }
}
