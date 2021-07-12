package app.beelabs.com.codebase.base;

import android.util.Log;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
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
                                         Interceptor[] customInterceptors,
                                         Interceptor[] customNetworkInterceptors) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (allowUntrustedSSL) {
            httpClient = allowUntrustedSSL(httpClient);
        }


//        try {
//            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
//                    TrustManagerFactory.getDefaultAlgorithm());
//            trustManagerFactory.init((KeyStore) null);
//            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
//            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
//                throw new IllegalStateException("Unexpected default trust managers:"
//                        + Arrays.toString(trustManagers));
//            }
//
//            X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
//
//            SSLContext sc = SSLContext.getInstance("TLSv1.2");
//            sc.init(null, null, null);
//            httpClient.sslSocketFactory(new TLS12SocketFactory(sc.getSocketFactory()), trustManager);
//        } catch (NoSuchAlgorithmException | KeyManagementException e) {
//            e.printStackTrace();
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        }

        httpClient.connectTimeout(timeout, TimeUnit.SECONDS);
        httpClient.readTimeout(timeout, TimeUnit.SECONDS);
        httpClient.writeTimeout(timeout, TimeUnit.SECONDS);

        // interceptor logging HTTP request
        if (enableLoggingHttp) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }


        if (customInterceptors != null) {
            for (Interceptor interceptor : customInterceptors) {
                httpClient.addInterceptor(interceptor);
            }
        }

        if (customNetworkInterceptors != null) {
            for (Interceptor interceptor : customNetworkInterceptors) {
                httpClient.addNetworkInterceptor(interceptor);
            }
        }
        return httpClient.build();
    }

//    protected OkHttpClient getHttpClient(boolean allowUntrustedSSL,
//                                         int timeout,
//                                         boolean enableLoggingHttp,
//                                         final boolean enableEncryptedRSA) {
//
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//
//        if (allowUntrustedSSL) {
//            allowUntrustedSSL(httpClient);
//        }
//
//
//        try {
//            SSLContext sc = SSLContext.getInstance("TLSv1.2");
//            sc.init(null, null, null);
//            httpClient.sslSocketFactory(new TLS12SocketFactory(sc.getSocketFactory()));
//        } catch (NoSuchAlgorithmException | KeyManagementException e) {
//            e.printStackTrace();
//        }
//
//        httpClient.connectTimeout(timeout, TimeUnit.SECONDS);
//        httpClient.readTimeout(timeout, TimeUnit.SECONDS);
//        httpClient.writeTimeout(timeout, TimeUnit.SECONDS);
//
//        // interceptor RSA for body encryption
//        if (enableEncryptedRSA) {
//            httpClient.addInterceptor(new RSAInterceptor());
//        }
//
//        // interceptor logging HTTP request
//        if (enableLoggingHttp) {
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//            httpClient.addInterceptor(logging);
//        }
//
//        return httpClient.build();
//    }

    private OkHttpClient.Builder allowUntrustedSSL(OkHttpClient.Builder httpClient) {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            httpClient.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            httpClient.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return httpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
