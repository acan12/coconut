package app.beelabs.com.codebase.di;


/**
 * Created by arysuryawan on 8/21/17.
 */

public interface IApi {
    Object getApiService(String apiDomain, Class<IApiService> clazz);
}
