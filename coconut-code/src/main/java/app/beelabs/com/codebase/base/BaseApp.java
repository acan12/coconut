package app.beelabs.com.codebase.base;

import android.app.Application;

import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.base.service.WifiConnectionService;
import app.beelabs.com.codebase.di.component.AppComponent;
import app.beelabs.com.codebase.di.component.DaggerAppComponent;
import app.beelabs.com.codebase.di.module.ApiServiceModule;
import app.beelabs.com.codebase.di.module.AppModule;
import app.beelabs.com.codebase.support.util.CacheUtil;

/**
 * Created by arysuryawan on 11/15/17.
 */

public class BaseApp extends Application {
    protected static AppComponent component;

    protected static AppComponent getComponent() {
        return component;
    }

    protected static void setupBuilder(DaggerAppComponent.Builder builder, Application app) {
        component = builder.appModule(new AppModule(app))
                .apiServiceModule(new ApiServiceModule(app))
                .build();

        CacheUtil.setupSharedPref(
                app.getResources().getString(R.string.app_name),
                app.getApplicationContext()
        );

        WifiConnectionService.getInstance().initializeConnection(app.getApplicationContext());
    }


}
