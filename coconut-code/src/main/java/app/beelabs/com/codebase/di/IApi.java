package app.beelabs.com.codebase.di;


/**
 * Created by arysuryawan on 8/21/17.
 */

public interface IApi {
    Object getApiService(String apiDomain, boolean allowUntrusted, Class<IApiService> clazz, int timeout);
}
