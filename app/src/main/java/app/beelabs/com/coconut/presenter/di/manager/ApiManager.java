package app.beelabs.com.coconut.presenter.di.manager;

import app.beelabs.com.coconut.IConfig;
import app.beelabs.com.coconut.model.api.ApiService;
import app.beelabs.com.coconut.presenter.di.IApi;
import app.beelabs.com.codebase.base.BaseManager;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by arysuryawan on 8/21/17.
 */

public class ApiManager extends BaseManager implements IApi {
    private ApiService api;


    @Override
    public ApiService getApiService() {


        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(IConfig.API_BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(getHttpClient())
                    .build();
            api = retrofit.create(ApiService.class);
        }
        return api;
    }
}
