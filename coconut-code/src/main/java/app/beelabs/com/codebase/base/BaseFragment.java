package app.beelabs.com.codebase.base;

import android.support.v4.app.Fragment;

import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.component.LoadingDialogComponent;
import app.beelabs.com.codebase.di.IProgress;
import app.beelabs.com.codebase.di.component.AppComponent;


/**
 * Created by arysuryawan on 8/16/17.
 */

public abstract class BaseFragment extends Fragment implements IView {


    protected void showApiProgressDialog(AppComponent appComponent, BasePresenter presenter) {
        showApiProgressDialog(appComponent, presenter, null);
    }

    protected void showApiProgressDialog(AppComponent appComponent, BasePresenter presenter, String message) {
        IProgress progress = appComponent.getProgressDialog();
        progress.showProgressDialog(getActivity(), message, true);
        presenter.call();
    }

    protected void showApiProgressDialog(AppComponent appComponent, BasePresenter presenter, String message, boolean isCanceledOnTouch) {
        IProgress progress = appComponent.getProgressDialog();
        progress.showProgressDialog(getActivity(), message, isCanceledOnTouch);
        presenter.call();
    }

    protected void showLoadingDialog(AppComponent appComponent, BasePresenter presenter, String message) {
        IProgress progress = appComponent.getProgressDialog();
        progress.showLoadingDialog(new LoadingDialogComponent(message, 0, getActivity(), R.style.CoconutDialogFullScreen));
        presenter.call();
    }

    @Override
    public void handleError(String message) {

    }

    @Override
    public void handleRetryConnection() {

    }
}
