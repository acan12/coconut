package app.beelabs.com.codebase.base;


import app.beelabs.com.codebase.IConfig;
import app.beelabs.com.codebase.di.IApi;
import app.beelabs.com.codebase.di.component.AppComponent;


/**
 * Created by arysuryawan on 8/18/17.
 */

public class BaseApi {
    private String apiDomain;
    private static BaseApi baseApi;

    public static BaseApi getInstance() {
        if (baseApi == null)
            baseApi = new BaseApi();
        return baseApi;
    }

    public String getApiDomain() {
        return apiDomain;
    }

    public void setApiDomain(String apiDomain) {
        this.apiDomain = apiDomain;
    }

    public Object setupApi(AppComponent appComponent, Class clazz, boolean allowUntrusted, int timeout, boolean enableLoggingHttp) {
        return setupApi(appComponent, clazz, false, IConfig.TIMEOUT_SHORT_INSECOND, true, null);
    }

    public Object setupApi(AppComponent appComponent, Class clazz, boolean allowUntrusted, int timeout, boolean enableLoggingHttp, String PedePublicKeyRSA) {
        IApi api = appComponent.getApi();
        return api.initApiService(getApiDomain(), allowUntrusted, clazz, timeout, enableLoggingHttp, PedePublicKeyRSA);
    }
}

