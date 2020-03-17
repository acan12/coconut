package app.beelabs.com.codebase.base;

import android.view.View;

public interface IView {
    BaseActivity getCurrentActivity();

    View getRootContentView();

    void handleError(String message);

    void handleRetryConnection();
}
