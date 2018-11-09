package app.beelabs.com.codebase.base;

public interface IPresenter {
    void call();

    void done();

    void fail(String message);

}
