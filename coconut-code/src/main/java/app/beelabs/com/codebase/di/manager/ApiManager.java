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
    private String apiDomain = "";

    @Override
    public Object getApiService(String apiDomain, boolean allowUntrusted, Class<IApiService> clazz, int timeout) {

        if (api == null || !this.apiDomain.equals(apiDomain)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(apiDomain)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getHttpClient(allowUntrusted, timeout))
                    .build();
            api = retrofit.create(clazz);
            this.apiDomain = apiDomain;
        }
        return api;
    }
}
