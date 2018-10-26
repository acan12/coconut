package app.beelabs.com.codebase.base;

import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.component.ProgressDialogComponent;

public class BasePresenter implements IPresenter {
    private static BasePresenter presenter;
    private static BaseActivity ba;


    public static BasePresenter getInstance(BaseActivity activity, BasePresenter bp) {
        ba = activity;
        presenter = bp;

        return presenter;
    }

    @Override
    public void call() {

    }

    @Override
    public void done() {
        ProgressDialogComponent.dismissProgressDialog(ba);

    }

    @Override
    public void fail() {
        done();
        ba.handleFail();
    }


    public static class OnPresenterResponseCallback {

        public void call(BaseResponse br) {

        }

    }

}
