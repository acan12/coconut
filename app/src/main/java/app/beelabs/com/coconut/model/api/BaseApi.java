package app.beelabs.com.coconut.model.api;


import android.content.Context;

import app.beelabs.com.coconut.App;
import app.beelabs.com.coconut.presenter.di.IApi;


/**
 * Created by arysuryawan on 8/18/17.
 */

public class BaseApi {

    protected static ApiService setupApi(Context context) {

        IApi api = App.getComponent(context).getApi();
        return api.getApiService();
    }
}

