package app.beelabs.com.codebase.di.manager;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import app.beelabs.com.codebase.base.BaseManager;
import app.beelabs.com.codebase.di.IApi;
import app.beelabs.com.codebase.di.IApiService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by arysuryawan on 8/21/17.
 */

public class ApiManager extends BaseManager implements IApi {
    private Object api;

    @Override
    public Object initApiService(String apiDomain, boolean allowUntrusted, Class<IApiService> clazz, int timeout, boolean enableLoggingHttp) {
        return initApiService(apiDomain, allowUntrusted, clazz, timeout, enableLoggingHttp, false);
    }

    @Override
    public Object initApiService(String apiDomain, boolean allowUntrusted, Class<IApiService> clazz, int timeout, boolean enableLoggingHttp, boolean enableEncryptedRSA) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiDomain)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getHttpClient(allowUntrusted, timeout, enableLoggingHttp, enableEncryptedRSA))
                .build();
        api = retrofit.create(clazz);

        return api;
    }

}
