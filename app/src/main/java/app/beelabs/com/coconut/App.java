package app.beelabs.com.coconut;

import android.app.Application;
import android.content.Context;

import app.beelabs.com.codebase.base.BaseApp;
import app.beelabs.com.codebase.di.component.AppComponent;
import app.beelabs.com.codebase.di.component.DaggerAppComponent;
import app.beelabs.com.codebase.di.module.ApiServiceModule;
import app.beelabs.com.codebase.di.module.AppModule;

/**
 * Created by arysuryawan on 8/17/17.
 */

public class App extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        setupBuilder(DaggerAppComponent.builder(), this);
    }

    public static AppComponent getAppComponent(){
        return getComponent();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
