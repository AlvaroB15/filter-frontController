package edu.patronesdiseno.srp.models.patterns;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityFilter implements Filter {
    @Override
    public String execute(String request) {
        // create a random hash token
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(request.getBytes());
        String encryptedString = new String(messageDigest.digest());

        System.out.println("Creating secure protocol: " + encryptedString);

        request += "&MAC=" + encryptedString;

        return request;
    }
}
