package app.beelabs.com.codebase.support.rx;

import app.beelabs.com.codebase.base.IView;
import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.component.LoadingDialogComponent;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxObserver<P extends BaseResponse> implements Observer {
    private IView iv;
    private String messageLoading;

    public RxObserver(IView iv) {
        this.iv = iv;
    }

    public RxObserver(IView iv, String messageLoading) {
        this.iv = iv;
        this.messageLoading = messageLoading;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if(messageLoading != null) LoadingDialogComponent.openLoadingDialog(iv.getBaseActivity(), messageLoading);
    }

    @Override
    public void onNext(Object o) {
        LoadingDialogComponent.closeLoadingDialog(iv.getBaseActivity());

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {
    }
}
