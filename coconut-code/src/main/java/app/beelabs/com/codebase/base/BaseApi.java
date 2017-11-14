package app.beelabs.com.codebase.base;


import app.beelabs.com.codebase.di.IApi;
import app.beelabs.com.codebase.di.component.AppComponent;


/**
 * Created by arysuryawan on 8/18/17.
 */

public class BaseApi {
    private static String apiDomain;

    public static void setApiDomain(String apiDomain) {
        BaseApi.apiDomain = apiDomain;
    }

    protected static Object setupApi(AppComponent appComponent, Class clazz) {

        IApi api = appComponent.getApi();
        return api.getApiService(apiDomain, clazz);
    }
}

