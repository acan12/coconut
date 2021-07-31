package app.beelabs.com.codebase.base;

import androidx.fragment.app.Fragment;

import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.base.contract.IView;
import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.component.dialog.SpinKitLoadingDialogComponent;
import app.beelabs.com.codebase.di.IProgress;
import app.beelabs.com.codebase.di.component.AppComponent;


/**
 * Created by arysuryawan on 8/16/17.
 */

public abstract class BaseFragment extends Fragment implements IView {

    @Deprecated
    protected void showApiProgressDialog(AppComponent appComponent, BasePresenter presenter) {
        showApiProgressDialog(appComponent, presenter, null);
    }

    @Deprecated
    protected void showApiProgressDialog(AppComponent appComponent, BasePresenter presenter, String message) {
        IProgress progress = appComponent.getProgressDialog();
        progress.showProgressDialog(getActivity(), message, true);
        presenter.call();
    }

    @Deprecated
    protected void showApiProgressDialog(AppComponent appComponent, BasePresenter presenter, String message, boolean isCanceledOnTouch) {
        IProgress progress = appComponent.getProgressDialog();
        progress.showProgressDialog(getActivity(), message, isCanceledOnTouch);
        presenter.call();
    }

    @Deprecated
    protected void showLoadingDialog(AppComponent appComponent, BasePresenter presenter, String message) {
        IProgress progress = appComponent.getProgressDialog();
        progress.showSpinLoadingDialog(new SpinKitLoadingDialogComponent(message, 0, getActivity(), R.style.CoconutDialogFullScreen));
        presenter.call();
    }

    @Override
    public BaseActivity getCurrentActivity() {
        return (BaseActivity) getActivity();
    }

    @Override
    public void handleSuccess(BaseResponse response) { }

    @Override
    public void handleReconnection() { }

    @Override
    public void handleErrorResponse(BaseResponse response) { }

    @Override
    public void handleError(String message) { }
}
