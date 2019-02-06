package app.beelabs.com.codebase.base;

public interface IView {
    void handleError(String message);

    BaseActivity getBaseActivity();
}
