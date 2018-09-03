package app.beelabs.com.codebase.base;

import android.support.v4.app.Fragment;
import android.util.Log;

import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.component.ProgressDialogComponent;
import app.beelabs.com.codebase.di.IProgress;
import app.beelabs.com.codebase.di.component.AppComponent;
import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by arysuryawan on 8/16/17.
 */

public abstract class BaseFragment extends Fragment {

    protected void showApiProgressDialog(AppComponent appComponent, BaseDao dao) {
        showApiProgressDialog(appComponent, dao, null);
    }

    protected void showApiProgressDialog(AppComponent appComponent, BaseDao dao, String message) {
        IProgress progress = appComponent.getProgressDialog();
        progress.showProgressDialog(getActivity(), message, true);
        dao.call();
    }

    protected void showApiProgressDialog(AppComponent appComponent, BaseDao dao, String message, boolean isCanceledOnTouch) {
        IProgress progress = appComponent.getProgressDialog();
        progress.showProgressDialog(getActivity(), message, isCanceledOnTouch);
        dao.call();
    }

    protected void onApiResponseCallback(BaseResponse br, int responseCode, Response response) {
    }

    protected void onApiFailureCallback(String message) {
        Log.e("Message:", message+ "");
    }


    public static void onResponseCallback(Call<BaseResponse> call, Response response, BaseFragment fm, int responseCode) {
        ProgressDialogComponent.dismissProgressDialog((BaseActivity) fm.getActivity());
        fm.onApiResponseCallback((BaseResponse) response.body(), responseCode, response);
    }

    public static void onFailureCallback(Throwable t, BaseFragment fm) {
        ProgressDialogComponent.dismissProgressDialog((BaseActivity) fm.getActivity());
        fm.onApiFailureCallback(t.getMessage());
    }


}
