package app.beelabs.com.codebase.base;

import android.support.v4.app.Fragment;

import app.beelabs.com.codebase.di.IProgress;
import app.beelabs.com.codebase.di.component.AppComponent;


/**
 * Created by arysuryawan on 8/16/17.
 */

public abstract class BaseFragment extends Fragment implements IView {

//    @Override
//    public BaseActivity getCurrentActivity() {
//        return (BaseActivity) getActivity();
//    }

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

//    protected void onApiResponseCallback(BaseResponse br, int responseCode, Response response) {
//    }
//
//    protected void onApiFailureCallback(String message) {
//        Log.e("Message:", message + "");
//    }


//    public static void onResponseCallback(Response response, IPresenter iView, int responseCode) {
//        ProgressDialogComponent.dismissProgressDialog(iView.getBaseActivity());
//        ((BaseFragment) iView).onApiResponseCallback((BaseResponse) response.body(), responseCode, response);
//    }
//
//    public static void onFailureCallback(Throwable t, IPresenter iView) {
//        ProgressDialogComponent.dismissProgressDialog(iView.getBaseActivity());
//        ((BaseFragment) iView).onApiFailureCallback(t.getMessage());
//    }


}
