package app.beelabs.com.codebase.base;

import android.view.View;

public interface IView {
    void handleError(String message);

    BaseActivity getCurrentActivity();

    View getContentView();

    void handleRetryConnection();

}
