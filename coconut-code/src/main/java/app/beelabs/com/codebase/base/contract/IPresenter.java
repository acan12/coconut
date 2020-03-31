package app.beelabs.com.codebase.base.contract;

public interface IPresenter {
    void call();

    void done();

    void fail(String message);

}
