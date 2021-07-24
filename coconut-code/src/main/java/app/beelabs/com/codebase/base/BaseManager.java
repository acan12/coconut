package app.beelabs.com.codebase.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import app.beelabs.com.codebase.base.exception.NoConnectivityException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

        // allowUntrustedSSL: true , if activate Untrusted SSL
        if (allowUntrustedSSL) httpClient = ApiHelper.allowUntrustedSSL(httpClient);

        httpClient.connectTimeout(timeout, TimeUnit.SECONDS);
        httpClient.readTimeout(timeout, TimeUnit.SECONDS);
        httpClient.writeTimeout(timeout, TimeUnit.SECONDS);

        // Interceptor logging HTTP request
        if (enableLoggingHttp) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }

        // Pre-Interceptor
        if (customInterceptors != null) {
            for (Interceptor interceptor : customInterceptors) {
                httpClient.addInterceptor(interceptor);
            }
        }

        // Post-Network Interceptor
        if (customNetworkInterceptors != null) {
            for (Interceptor interceptor : customNetworkInterceptors) {
                httpClient.addNetworkInterceptor(interceptor);
            }
        }
        return httpClient.build();
    }

    public class NetworkConnectionInterceptor implements Interceptor {

        private Context mContext;

        public NetworkConnectionInterceptor(Context context) {
            mContext = context;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            if (!isConnected()) {
                throw new NoConnectivityException();
                // Throwing our custom exception 'NoConnectivityException'
            }

            Request.Builder builder = chain.request().newBuilder();
            return chain.proceed(builder.build());
        }

        @SuppressLint("MissingPermission")
        public boolean isConnected(){
            ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            return (netInfo != null && netInfo.isConnected());
        }

    }
}
