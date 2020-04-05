package app.beelabs.com.codebase.component.interceptor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import app.beelabs.com.codebase.base.BaseManager;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class RSAInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header("User-Agent", "base")
                .header("Accept", "application/json")
                .method(original.method(), original.body())
                .build();


            if (BaseManager.Method.POST.toString().equals(request.method())) {
                MediaType mediaType = MediaType.parse("text/plain; charset=utf-8");
                RequestBody oldbody = request.body();
                Buffer buffer = new Buffer();
                oldbody.writeTo(buffer);
                String params = toJson(buffer.readUtf8());
                String strNewBody = null;
                try {
                    strNewBody = encryptRSA(params);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                }

                RequestBody body = RequestBody.create(mediaType, strNewBody);
                return chain.proceed(request.newBuilder().post(body).build());
            }


        return chain.proceed(request);
    }


    private String toJson(String params) {
        try {
            new JSONObject(params);
            return params;
        } catch (JSONException e) {
            JSONObject jsonObject = new JSONObject();
            String[] maps = params.split("&");
            for (String map : maps) {
                String[] kv = map.split("=");
                try {
                    jsonObject.put(kv[0], kv.length == 2 ? kv[1] : "");
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
            return jsonObject.toString();
        }
    }

    public String encryptRSA(String plain) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair kp = kpg.genKeyPair();
        PublicKey publicKey = kp.getPublic();

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plain.getBytes());

        String encrypted = bytesToString(encryptedBytes);
        return encrypted;
    }

    public String bytesToString(byte[] b) {
        byte[] b2 = new byte[b.length + 1];
        b2[0] = 1;
        System.arraycopy(b, 0, b2, 1, b.length);
        return new BigInteger(b2).toString(36);
    }

}
