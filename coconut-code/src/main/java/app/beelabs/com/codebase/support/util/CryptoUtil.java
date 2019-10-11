package app.beelabs.com.codebase.support.util;

import android.util.Base64;
import android.util.Log;

import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.KeySpec;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

public class CryptoUtil {

    public static String encryptRSA(byte[] text, String strPublicKey) {
        byte[] cipherText = null;
        String strEncryInfoData = "";
        try {
            KeyFactory keyFac = KeyFactory.getInstance("RSA");
            KeySpec keySpec = new X509EncodedKeySpec(Base64.decode(strPublicKey.trim().getBytes(), Base64.DEFAULT));
            Key publicKey = keyFac.generatePublic(keySpec);

            final Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
            OAEPParameterSpec oaepParams = new OAEPParameterSpec(
                    "SHA-1", "MGF1",
                    new MGF1ParameterSpec("SHA-1"),
                    PSource.PSpecified.DEFAULT
            );
            cipher.init(Cipher.ENCRYPT_MODE, publicKey, oaepParams);
            cipherText = cipher.doFinal(text);
            strEncryInfoData = new String(Base64.encode(cipherText, Base64.DEFAULT));

        } catch (Exception e) {
            String m = e.getMessage();
            Log.d("ERROR CRYPTO: ", m);
        }
        return strEncryInfoData.replaceAll("(\\r|\\n)", "");
    }

}
