package app.beelabs.com.codebase.di.manager;

import app.beelabs.com.codebase.base.BaseManager;
import app.beelabs.com.codebase.di.IApi;
import app.beelabs.com.codebase.di.IApiService;
import okhttp3.Interceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by arysuryawan on 8/21/17.
 */

public class ApiManager extends BaseManager implements IApi {
    private Object api;
    private String apiDomain = "";

    @Override
    public Object initApiService(String apiDomain, boolean allowUntrusted, Class<IApiService> clazz, int timeout, boolean enableLoggingHttp, String PedePublicKeyRSA) {

        if (api == null || !this.apiDomain.equals(apiDomain)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(apiDomain)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(getHttpClient(allowUntrusted, timeout, enableLoggingHttp, PedePublicKeyRSA))
                    .build();
            api = retrofit.create(clazz);
            this.apiDomain = apiDomain;
        }
        return api;
    }

    @Override
    public Object initApiService(String apiDomain, boolean allowUntrusted, Class<IApiService> clazz, int timeout, boolean enableLoggingHttp, Interceptor[] interceptors) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiDomain)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(getHttpClient(allowUntrusted, timeout, enableLoggingHttp, interceptors))
                .build();
        api = retrofit.create(clazz);

        return api;
    }
}
