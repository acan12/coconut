package app.beelabs.com.codebase.di.component;


import javax.inject.Singleton;

import app.beelabs.com.codebase.di.IApi;
import app.beelabs.com.codebase.di.IProgress;
import app.beelabs.com.codebase.di.module.ApiServiceModule;
import app.beelabs.com.codebase.di.module.AppModule;
import dagger.Component;

/**
 * Created by arysuryawan on 11/20/16.
 */

@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class})
public interface AppComponent {

    IApi getApi();

    IProgress getProgressDialog();

}
