package app.beelabs.com.codebase.support.rx;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.base.contract.IView;
import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.component.CoconutAlertInternetConnection;
import app.beelabs.com.codebase.component.dialog.ProgressDialogComponent;
import app.beelabs.com.codebase.component.dialog.SpinKitLoadingDialogComponent;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxObserver<P extends BaseResponse> implements Observer {
    private IView iv;
    private String messageLoading;
    private long timeMilis;
    private int dialogType;
    private boolean isEnable;

    public interface DialogTypeEnum {
        int DEFAULT = 0;
        int SPINKIT = 1;
    }

    public RxObserver(IView iv) {
        this.iv = iv;
    }

    public RxObserver(IView iv, String messageLoading) {
        this.iv = iv;
        this.messageLoading = messageLoading;
        this.timeMilis = 0;
    }

    public RxObserver(IView iv, String messageLoading, long timeMilis) {
        this.iv = iv;
        this.messageLoading = messageLoading;
        this.timeMilis = timeMilis;
    }

    public RxObserver setDialogType(int dialogType) {
        this.dialogType = dialogType;
        return this;
    }

    public RxObserver setEnableCoconutAlertConnection(boolean isEnable) {
        this.isEnable = isEnable;
        return this;
    }

    public void enableAlertInternetLostConnection(boolean isEnable) {
        this.isEnable = isEnable;
    }

    @Override
    public void onSubscribe(Disposable d) {
        ProgressDialogComponent.dismissProgressDialog(iv.getCurrentActivity());
        SpinKitLoadingDialogComponent.dismissProgressDialog(iv.getCurrentActivity(), timeMilis);
        if (messageLoading != null) {
            switch (dialogType) {
                case DialogTypeEnum.DEFAULT:
                    ProgressDialogComponent.showProgressDialog(iv.getCurrentActivity(), messageLoading, false);
                    break;

                case DialogTypeEnum.SPINKIT:
                    SpinKitLoadingDialogComponent.showProgressDialog(iv.getCurrentActivity(), messageLoading, timeMilis);
                    break;
            }
        }
    }

    @Override
    public void onNext(Object o) {
        SpinKitLoadingDialogComponent.dismissProgressDialog(iv.getCurrentActivity(), timeMilis);
        ProgressDialogComponent.dismissProgressDialog(iv.getCurrentActivity());
    }

    @Override
    public void onError(Throwable e) {
        ProgressDialogComponent.dismissProgressDialog(iv.getCurrentActivity());
        SpinKitLoadingDialogComponent.dismissProgressDialog(iv.getCurrentActivity(), timeMilis);

        if ((e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof IOException) && isEnable) {
            CoconutAlertInternetConnection.show(
                    iv.getCurrentActivity().getResources().getString(R.string.coconut_internet_fail_message), iv);
        }
    }

    @Override
    public void onComplete() {
    }
}
