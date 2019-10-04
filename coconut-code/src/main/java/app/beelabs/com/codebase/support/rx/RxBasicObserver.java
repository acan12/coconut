package app.beelabs.com.codebase.support.rx;

import android.app.ProgressDialog;

import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.IView;
import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.component.ProgressDialogComponent;
import app.beelabs.com.codebase.component.SnackbarInternetConnection;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxBasicObserver<P extends BaseResponse> implements Observer {
    private IView iv;
    private String messageLoading;
    private long timeMilis;

    public RxBasicObserver(IView iv) {
        this.iv = iv;
    }

    public RxBasicObserver(IView iv, String messageLoading) {
        this.iv = iv;
        this.messageLoading = messageLoading;
        this.timeMilis = 0;
    }

    public RxBasicObserver(IView iv, String messageLoading, long timeMilis) {
        this.iv = iv;
        this.messageLoading = messageLoading;
        this.timeMilis = timeMilis;
    }

    @Override
    public void onSubscribe(Disposable d) {
        ProgressDialog dialogLoading = null;
        BaseActivity activity = iv.getCurrentActivity();
        if (messageLoading != null)
            dialogLoading = ProgressDialogComponent.showProgressDialog(activity, messageLoading, false);
        while (dialogLoading == null || dialogLoading.isShowing()) return;
    }

    @Override
    public void onNext(Object o) {
        ProgressDialogComponent.dismissProgressDialog(iv.getCurrentActivity());

    }

    @Override
    public void onError(Throwable e) {
        ProgressDialogComponent.dismissProgressDialog(iv.getCurrentActivity());
        SnackbarInternetConnection.show(iv.getCurrentActivity().getResources().getString(R.string.coconut_internet_fail_message), iv);
    }

    @Override
    public void onComplete() {
    }
}
