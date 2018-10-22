package app.beelabs.com.coconut.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.BaseFragment;
import app.beelabs.com.codebase.base.IPresenter;


/**
 * Created by arysuryawan on 8/21/17.
 */

public class MainFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

//        showApiProgressDialog(App.getAppComponent(), new ResourcePresenter() {
//            @Override
//            public void call() {
//                postPhoneNumber("081212341212", MainFragment.this, IConfig.KEY_CALLER_API_SUMMARY);
//            }
//        }, "Loading", false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

//    @Override
//    public BaseActivity getCurrentActivity() {
//        return null;
//    }


//    @Override
//    protected void onApiResponseCallback(BaseResponse mr, int responseCode, Response response) {
//        Log.d("", "");
//        if(responseCode == IConfig.KEY_CALLER_API_SUMMARY){
//            Toast.makeText(getContext(), "api SUMMARY being called!", Toast.LENGTH_SHORT).show();
//        }
//    }


}
