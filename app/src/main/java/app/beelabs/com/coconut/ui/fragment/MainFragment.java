package app.beelabs.com.coconut.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app.beelabs.com.coconut.App;
import app.beelabs.com.coconut.IConfig;
import app.beelabs.com.coconut.presenter.ResourcePresenter;
import app.beelabs.com.codebase.base.BaseFragment;
import app.beelabs.com.codebase.base.IPresenter;
import app.beelabs.com.codebase.base.response.BaseResponse;
import retrofit2.Response;


/**
 * Created by arysuryawan on 8/21/17.
 */

public class MainFragment extends BaseFragment implements IPresenter {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        showApiProgressDialog(App.getAppComponent(), new ResourcePresenter() {
            @Override
            public void call() {
                postPhoneNumber("081212341212", MainFragment.this, IConfig.KEY_CALLER_API_SUMMARY);
            }
        }, "Loading", false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    protected void onApiResponseCallback(BaseResponse mr, int responseCode, Response response) {
        Log.d("", "");
        if(responseCode == IConfig.KEY_CALLER_API_SUMMARY){
            Toast.makeText(getContext(), "api SUMMARY being called!", Toast.LENGTH_SHORT).show();
        }
    }


}
