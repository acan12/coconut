package app.beelabs.com.codebase.base;

import android.support.v4.app.Fragment;
import android.util.Log;

import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.component.ProgressDialogComponent;
import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by arysuryawan on 8/16/17.
 */

public abstract class BaseFragment extends Fragment {
    protected void onApiResponseCallback(BaseResponse mr, int responseCode) {
    }

    protected void onApiFailureCallback(String message) {
        Log.e("Message:", message);
    }


    public static void onResponseCallback(Call<BaseResponse> call, Response response, BaseFragment fm, int responseCode) {
        ProgressDialogComponent.dismissProgressDialog((BaseActivity) fm.getActivity());
        fm.onApiResponseCallback((BaseResponse) response.body(), responseCode);
    }

    public static void onFailureCallback(Throwable t, BaseFragment fm) {
        ProgressDialogComponent.dismissProgressDialog((BaseActivity) fm.getActivity());
        fm.onApiFailureCallback(t.getMessage());
    }


}
