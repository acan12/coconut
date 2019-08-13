package app.beelabs.com.codebase.support.util;

import android.util.Base64;
import android.util.Log;

import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class CryptoUtil {

    public static String encryptRSA(byte[] text, String strPublicKey) {
        byte[] cipherText = null;
        String strEncryInfoData = "";
        try {
            KeyFactory keyFac = KeyFactory.getInstance("RSA");
            KeySpec keySpec = new X509EncodedKeySpec(Base64.decode(strPublicKey.trim().getBytes(), Base64.DEFAULT));
            Key publicKey = keyFac.generatePublic(keySpec);

            final Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding");
            // encrypt the plain text using the public key
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            cipherText = cipher.doFinal(text);
            strEncryInfoData = new String(Base64.encode(cipherText, Base64.DEFAULT));

        } catch (Exception e) {
            String m = e.getMessage();
            Log.d("ERROR CRYPTO: ", m);
        }
        return strEncryInfoData.replaceAll("(\\r|\\n)", "");
    }

}
