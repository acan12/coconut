package app.beelabs.com.codebase.base.contract;

import android.view.View;

import app.beelabs.com.codebase.base.BaseActivity;

public interface IView {
    BaseActivity getCurrentActivity();

    View getRootView();

    void handleError(String message);

    void handleRetryConnection();

}
