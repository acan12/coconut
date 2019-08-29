package app.beelabs.com.codebase.support.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;

public class CryptoUtil {

    public static byte[] encryptedKey64() {
        // security key to encrypted the Realm
        byte[] key = new byte[64];
        new Random(42).nextBytes(key);
        return key;
    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
