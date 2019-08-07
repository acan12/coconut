package app.beelabs.com.codebase.di;


/**
 * Created by arysuryawan on 8/21/17.
 */

public interface IApi {
    Object initApiService(String apiDomain, boolean allowUntrusted, Class<IApiService> clazz, int timeout, boolean enableLoggingHttp);
    Object initApiService(String apiDomain, boolean allowUntrusted, Class<IApiService> clazz, int timeout, boolean enableLoggingHttp, boolean enableEncryptedRSA);

}
