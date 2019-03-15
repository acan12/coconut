package app.beelabs.com.coconut.model.api;

import android.content.Context;

import app.beelabs.com.coconut.App;
import app.beelabs.com.coconut.IConfig;
import app.beelabs.com.coconut.model.api.request.LoginRequestModel;
import app.beelabs.com.coconut.model.api.response.ArticleResponse;
import app.beelabs.com.coconut.model.api.response.LoginResponseModel;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.codebase.base.BaseApi;
import app.beelabs.com.codebase.base.response.BaseResponse;
import retrofit2.Callback;


/**
 * Created by arysuryawan on 8/18/17.
 */
public class Api extends BaseApi {

    synchronized private static ApiService initApiDomain(Context context) {
        getInstance().setApiDomain(IConfig.API_BASE_URL);
        return (ApiService) getInstance().setupApi(App.getAppComponent(), ApiService.class, true, app.beelabs.com.codebase.IConfig.TIMEOUT_SHORT_INSECOND);
    }

    synchronized private static ApiService initApiDomainSFA2(Context context) {
        getInstance().setApiDomain("https://sfa2.ottopay.id/v1/");

        return (ApiService) getInstance().setupApi(App.getAppComponent(), ApiService.class, false, app.beelabs.com.codebase.IConfig.TIMEOUT_SHORT_INSECOND);
    }


    // SFA2
    synchronized public static void onApiLogin(LoginRequestModel model, Context context,
                                               Callback callback) {
//        initApiDomainCA(context).callApiLogin(model)
        initApiDomainSFA2(context).callApiLogin(model)
                .enqueue((Callback<LoginResponseModel>) callback);
    }


    synchronized public static void doApiSources(Context context, Callback callback) {
        initApiDomain(context).callApiSources("en").enqueue((Callback<SourceResponse>) callback);
    }

    synchronized public static void doApiArticles(Context context, Callback callback) {
        initApiDomain(context).callApiArticles("the-next-web", "latest", "6d362365d5e245faa1fe3253c83c45ac").enqueue((Callback<ArticleResponse>) callback);
    }

    synchronized public static void doTestFin(String phone, Context context, Callback callback) {
        initApiDomain(context).callApiTestFintech(phone).enqueue((Callback<BaseResponse>) callback);
    }

}
