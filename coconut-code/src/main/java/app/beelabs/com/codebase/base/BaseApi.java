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

    public BaseApi() {
    }

    public static BaseApi getInstance(){
        if(baseApi ==null)
            baseApi = new BaseApi();
        return baseApi;
    }

    public String getApiDomain() {
        return apiDomain;
    }

    public void setApiDomain(String apiDomain) {
        this.apiDomain = apiDomain;
    }

    public Object setupApi(AppComponent appComponent, Class clazz, boolean enableLoggingHttp) {
        return setupApi(appComponent, clazz, false, IConfig.TIMEOUT_SHORT_INSECOND, enableLoggingHttp);
    }


    public Object setupApi(AppComponent appComponent, Class clazz, boolean allowUntrusted, int timeout, boolean enableLoggingHttp) {
        IApi api = appComponent.getApi();

        return api.initApiService(getApiDomain(), allowUntrusted, clazz, timeout, enableLoggingHttp);
    }


    public Object setupApiDomain(String domain, AppComponent appComponent, Class clazz, boolean allowUntrusted, int timeout, boolean enableLoggingHttp){
        this.apiDomain = domain;
        return appComponent.getApi().initApiService(domain, allowUntrusted, clazz, timeout, enableLoggingHttp, false);
    }

    public Object setupApiDomain(String domain, AppComponent appComponent, Class clazz, boolean allowUntrusted, int timeout, boolean enableLoggingHttp, boolean enableEncryptedRSA){
        this.apiDomain = domain;
        return appComponent.getApi().initApiService(domain, allowUntrusted, clazz, timeout, enableLoggingHttp, enableEncryptedRSA);
    }
}

