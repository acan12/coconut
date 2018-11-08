package app.beelabs.com.codebase.base;

import android.app.Application;

import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.di.component.AppComponent;
import app.beelabs.com.codebase.di.component.DaggerAppComponent;
import app.beelabs.com.codebase.di.module.ApiServiceModule;
import app.beelabs.com.codebase.di.module.AppModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

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
    }

    protected static void setupDefaultFont(String defaultPath){
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(defaultPath)
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

}
