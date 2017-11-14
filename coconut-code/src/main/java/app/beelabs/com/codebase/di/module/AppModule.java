package app.beelabs.com.codebase.di.module;


import android.app.Application;

import javax.inject.Singleton;

import app.beelabs.com.codebase.di.IProgress;
import app.beelabs.com.codebase.di.manager.ProgressManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by arysuryawan on 11/20/16.
 */

@Module
public class AppModule {

    private Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    protected IProgress provideProgressDialog() {
        return new ProgressManager();
    }

}
