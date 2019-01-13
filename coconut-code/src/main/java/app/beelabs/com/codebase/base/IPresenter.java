package app.beelabs.com.codebase.base;

import app.beelabs.com.codebase.component.BaseProgressDialog;

public interface IPresenter {
    void call();

    void done();

    void fail(String message);

}
