package app.beelabs.com.codebase.base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import app.beelabs.com.codebase.base.exception.NoConnectivityException;
import app.beelabs.com.codebase.base.service.WifiConnectionService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
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

        // default interceptor
        httpClient.addInterceptor(new ConnectivityInterceptor());

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


    public class ConnectivityInterceptor implements Interceptor {

        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            if (!WifiConnectionService.getInstance().isConnected()) {
                throw new NoConnectivityException();
            } else {
                Response response = chain.proceed(chain.request());
                return response;
            }
        }
    }

}
