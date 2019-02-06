package app.beelabs.com.codebase.base;

import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.component.LoadingDialogComponent;
import app.beelabs.com.codebase.component.ProgressDialogComponent;

public class BasePresenter implements IPresenter {
    private static BasePresenter presenter;
    private static IView iview;


    public static BasePresenter getInstance(IView iv, BasePresenter bp) {
        iview = iv;
        presenter = bp;

        return presenter;
    }

    @Override
    public void call() {

    }

    @Override
    public void done() {
        ProgressDialogComponent.dismissProgressDialog(iview.getBaseActivity());
        LoadingDialogComponent.closeDialog(iview.getBaseActivity());

    }

    @Override
    public void fail(String message) {
        done();
        iview.handleError(message);
    }


    public static class OnPresenterResponseCallback {

        public void call(BaseResponse br) {

        }

        public void error(String message) {

        }

    }

}
