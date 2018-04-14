package app.beelabs.com.coconut;

import app.beelabs.com.codebase.base.BaseApp;
import app.beelabs.com.codebase.di.component.AppComponent;
import app.beelabs.com.codebase.di.component.DaggerAppComponent;

/**
 * Created by arysuryawan on 8/17/17.
 */

public class App extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        setupBuilder(DaggerAppComponent.builder(), this);
        setupDefaultFont("fonts/SF-Pro-Display-Black.otf");
    }

    public static AppComponent getAppComponent() {
        return getComponent();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
