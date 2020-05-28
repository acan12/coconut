package app.beelabs.com.codebase.di;


import okhttp3.Interceptor;

/**
 * Created by arysuryawan on 8/21/17.
 */

public interface IApi {
    Object initApiService(String apiDomain, boolean allowUntrusted, Class<IApiService> clazz, int timeout, boolean enableLoggingHttp, String PedePublicKeyRSA);

    Object initApiService(String apiDomain, boolean allowUntrusted, Class<IApiService> clazz, int timeout, boolean enableLoggingHttp, Interceptor[] interceptors);
}
