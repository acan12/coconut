package app.beelabs.com.codebase.base;

import android.view.View;

public interface IView {
    BaseActivity getCurrentActivity();

    View getContentView();

    void handleError(String message);

    void handleRetryConnection();

}
