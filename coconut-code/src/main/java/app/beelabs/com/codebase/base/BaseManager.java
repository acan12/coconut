package app.beelabs.com.codebase.base;

import android.util.Log;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import app.beelabs.com.codebase.component.interceptor.RSAInterceptor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by arysuryawan on 11/10/17.
 */

public class BaseManager {

    public enum Method {
        GET,
        POST,
        UPDATE
    }

    protected OkHttpClient getHttpClient(boolean allowUntrustedSSL,
                                         int timeout,
                                         boolean enableLoggingHttp,
                                         Interceptor[] customInterceptors) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (allowUntrustedSSL) {
            allowUntrustedSSL(httpClient);
        }


        try {
            SSLContext sc = SSLContext.getInstance("TLSv1.2");
            sc.init(null, null, null);
            httpClient.sslSocketFactory(new TLS12SocketFactory(sc.getSocketFactory()));
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }

        httpClient.connectTimeout(timeout, TimeUnit.SECONDS);
        httpClient.readTimeout(timeout, TimeUnit.SECONDS);
        httpClient.writeTimeout(timeout, TimeUnit.SECONDS);

        // interceptor logging HTTP request
        if (enableLoggingHttp) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }


        for (Interceptor interceptor : customInterceptors) {
            httpClient.addInterceptor(interceptor);
        }
        return  httpClient.build();
    }

    protected OkHttpClient getHttpClient(boolean allowUntrustedSSL,
                                         int timeout,
                                         boolean enableLoggingHttp,
                                         final boolean enableEncryptedRSA) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (allowUntrustedSSL) {
            allowUntrustedSSL(httpClient);
        }


        try {
            SSLContext sc = SSLContext.getInstance("TLSv1.2");
            sc.init(null, null, null);
            httpClient.sslSocketFactory(new TLS12SocketFactory(sc.getSocketFactory()));
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }

        httpClient.connectTimeout(timeout, TimeUnit.SECONDS);
        httpClient.readTimeout(timeout, TimeUnit.SECONDS);
        httpClient.writeTimeout(timeout, TimeUnit.SECONDS);

        // interceptor RSA for body encryption
        if (enableEncryptedRSA) {
            httpClient.addInterceptor(new RSAInterceptor());
        }

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
}
