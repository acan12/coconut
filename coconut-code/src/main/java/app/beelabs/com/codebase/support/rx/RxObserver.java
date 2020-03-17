package app.beelabs.com.codebase.support.rx;

import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.IView;
import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.component.LoadingDialogComponent;
import app.beelabs.com.codebase.component.ProgressDialogComponent;
import app.beelabs.com.codebase.component.SnackbarInternetConnection;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxObserver<P extends BaseResponse> implements Observer {
    private IView iv;
    private String messageLoading;
    private long timeMillis;

    public RxObserver(IView iv, String messageLoading) {
        this.iv = iv;
        this.messageLoading = messageLoading;
        this.timeMillis = 0;
    }

    public RxObserver(IView iv, String messageLoading, long timeMillis) {
        this.iv = iv;
        this.messageLoading = messageLoading;
        this.timeMillis = timeMillis;
    }

    @Override
    public void onSubscribe(Disposable d) {
        LoadingDialogComponent dialogLoading = null;
        BaseActivity activity = iv.getCurrentActivity();
        if (messageLoading != null)
            dialogLoading = LoadingDialogComponent.openLoadingDialog(activity, messageLoading, timeMillis);
        while (dialogLoading == null || dialogLoading.isShowing()) return;
    }

    @Override
    public void onNext(Object o) {
        LoadingDialogComponent.closeLoadingDialog(iv.getCurrentActivity(), timeMillis);
        ProgressDialogComponent.dismissProgressDialog(iv.getCurrentActivity());
        onNext(iv, o);
    }

    public void onNext(IView iv, Object o) { }



    @Override
    public void onError(Throwable e) {
        LoadingDialogComponent.closeLoadingDialog(iv.getCurrentActivity(), timeMillis);
        SnackbarInternetConnection.show(iv.getCurrentActivity().getResources().getString(R.string.coconut_internet_fail_message), iv);
    }

    @Override
    public void onComplete() {
    }
}
