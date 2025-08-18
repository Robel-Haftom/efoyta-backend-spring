package com.wegagen.efoyta.utils;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

@Service
public class PasswordService {

    public boolean verifyPassword(String enteredPassword, String storedHash, String storedSalt) {
        try {
            byte[] saltBytes = Base64.getDecoder().decode(storedSalt);

            PBEKeySpec spec = new PBEKeySpec(
                    enteredPassword.toCharArray(),
                    saltBytes,
                    10000,
                    256 * 8 // 256 bytes = 2048 bits
            );
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hashBytes = skf.generateSecret(spec).getEncoded();
            String generatedHash = Base64.getEncoder().encodeToString(hashBytes);

            String storedHashPart = storedHash.substring(0, 44);
            String generatedHashPart = generatedHash.substring(0, 44);

            return storedHashPart.equals(generatedHashPart);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
