package app.beelabs.com.coconut.presenter.di.module;


import javax.inject.Singleton;

import app.beelabs.com.coconut.App;
import app.beelabs.com.coconut.presenter.di.IProgress;
import app.beelabs.com.coconut.presenter.di.manager.ProgressManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by arysuryawan on 11/20/16.
 */

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    protected IProgress provideProgressDialog() {
        return new ProgressManager();
    }

}
