package app.beelabs.com.codebase.base;

import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import app.beelabs.com.codebase.support.util.CryptoUtil;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;

/**
 * Created by arysuryawan on 11/10/17.
 */

public class BaseManager {

    enum Method {
        GET,
        POST,
        UPDATE
    }

    protected OkHttpClient getHttpClient(boolean allowUntrustedSSL,
                                         int timeout,
                                         boolean enableLoggingHttp,
                                         final String publicKeyRSA) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (allowUntrustedSSL) {
            allowUntrustedSSL(httpClient);
            try {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init((KeyStore) null);
                TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                    throw new IllegalStateException("Unexpected default trust managers:" + java.util.Arrays.toString(trustManagers));
                }
                X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
                SSLContext sc = SSLContext.getInstance("TLSv1.2");
                sc.init(null, new TrustManager[]{trustManager}, null);
                httpClient.sslSocketFactory(new TLS12SocketFactory(sc.getSocketFactory()));
            } catch (NoSuchAlgorithmException | KeyManagementException | IllegalStateException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }
        }


        httpClient.connectTimeout(timeout, TimeUnit.SECONDS);
        httpClient.readTimeout(timeout, TimeUnit.SECONDS);
        httpClient.writeTimeout(timeout, TimeUnit.SECONDS);

//        httpClient.addInterceptor(logging);

        // interceptor RSA for body encryption
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("User-Agent", "base")
                        .header("Accept", "application/json")
                        .method(original.method(), original.body())
                        .build();

                Log.d("", "Method.POST= " + Method.POST);
                if (publicKeyRSA != null) {
                    if (Method.POST.toString().equals(request.method())) {
                        MediaType mediaType = MediaType.parse("text/plain; charset=utf-8");
                        RequestBody oldbody = request.body();
                        Buffer buffer = new Buffer();
                        oldbody.writeTo(buffer);
                        String json = toJson(buffer.readUtf8());
                        String strNewBody = null;
                        try {
                            strNewBody = generateEncryptedParam(json, publicKeyRSA).toString();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        RequestBody body = RequestBody.create(mediaType, strNewBody);
                        return chain.proceed(request.newBuilder().post(body).build());
                    }
                }

                return chain.proceed(request);
            }
        });

        // interceptor logging HTTP request
        if (enableLoggingHttp) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            httpClient.addInterceptor(logging);
        }

        return httpClient.build();
    }

    private void allowUntrustedSSL(OkHttpClient.Builder httpClient) {

        Log.w("", "**** Allow untrusted SSL connection ****");
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] cArrr = new X509Certificate[0];
                return cArrr;
            }

            @Override
            public void checkServerTrusted(final X509Certificate[] chain,
                                           final String authType) throws CertificateException {
            }

            @Override
            public void checkClientTrusted(final X509Certificate[] chain,
                                           final String authType) throws CertificateException {
            }
        }};

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        httpClient.sslSocketFactory(sslContext.getSocketFactory());

        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                Log.d("", "Trust Host :" + hostname);
                return true;
            }
        };
        httpClient.hostnameVerifier(hostnameVerifier);

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


    public static JSONObject generateEncryptedParam(String jsonBody, String publicKeyRSA) {
        int maxChar = 50;
        ArrayList<byte[]> bytePartials = new ArrayList<>();
        JSONObject jsonParam = new JSONObject();
        try {
            byte[] bytesJSONBody = jsonBody.getBytes("UTF-8");
            Log.d("bytesJSONBody length: ", "" + bytesJSONBody.length);
            if (bytesJSONBody.length > maxChar) {
                int counter = 0;
                do {
                    if (bytesJSONBody.length - counter > maxChar) {
                        bytePartials.add(Arrays.copyOfRange(bytesJSONBody, counter, counter + maxChar));
                    } else {
                        bytePartials.add(Arrays.copyOfRange(bytesJSONBody, counter, bytesJSONBody.length));
                    }

                    counter = counter + maxChar;
                } while (counter < bytesJSONBody.length);
            } else {
                bytePartials.add(bytesJSONBody);
            }


            JSONArray encArray = new JSONArray();
            for (int i = 0; i < bytePartials.size(); i++) {
                encArray.put(CryptoUtil.encryptRSA(bytePartials.get(i), publicKeyRSA));
            }
            jsonParam.put("data", encArray);
            jsonParam.put("device", "Android");


        } catch (Exception e) {
        }

        return jsonParam;
    }


}
