package app.beelabs.com.codebase.base.contract;

import app.beelabs.com.codebase.base.BaseActivity;

public interface IView {
    BaseActivity getCurrentActivity();

    void handleError(String message);

    void handleRetryConnection();

}
