package app.beelabs.com.coconut.model.api;

import android.content.Context;

import app.beelabs.com.coconut.App;
import app.beelabs.com.coconut.IConfig;
import app.beelabs.com.coconut.model.api.response.ArticleResponse;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.model.api.response.SummaryResponse;
import app.beelabs.com.codebase.base.BaseApi;
import app.beelabs.com.codebase.base.IPresenter;
import retrofit2.Callback;


/**
 * Created by arysuryawan on 8/18/17.
 */
public class Api extends BaseApi {

    synchronized private static ApiService initApiDomain(Context context) {
        getInstance().setApiDomain(IConfig.API_BASE_URL);
        return (ApiService) getInstance().setupApi(App.getAppComponent(), ApiService.class, true, app.beelabs.com.codebase.IConfig.TIMEOUT_SHORT_INSECOND);
    }

    synchronized private static ApiService initApiDomain2(Context context) {
        getInstance().setApiDomain(IConfig.API_BASE_URL2);
        return (ApiService) getInstance().setupApi(App.getAppComponent(), ApiService.class, true, app.beelabs.com.codebase.IConfig.TIMEOUT_SHORT_INSECOND);
    }


    synchronized public static void doApiSources(IPresenter iView, Callback callback) {
        initApiDomain2(iView.getBaseActivity()).callApiSources("en").enqueue((Callback<SourceResponse>) callback);
    }

    synchronized public static void doApiArticles(Context context, Callback callback) {
        initApiDomain(context).callApiArticles("the-next-web", "latest", "6d362365d5e245faa1fe3253c83c45ac").enqueue((Callback<ArticleResponse>) callback);
    }

    synchronized public static void doTestFin(String phone, IPresenter iView, Callback callback){
        initApiDomain(iView.getBaseActivity()).callApiTestFintech(phone).enqueue((Callback<SummaryResponse>) callback);
    }

}
