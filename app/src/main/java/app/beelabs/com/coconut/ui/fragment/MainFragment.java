package app.beelabs.com.coconut.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app.beelabs.com.coconut.App;
import app.beelabs.com.coconut.IConfig;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.presenter.dao.ResourceDao;
import app.beelabs.com.coconut.ui.MainActivity;
import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.BaseDao;
import app.beelabs.com.codebase.base.BaseFragment;
import app.beelabs.com.codebase.base.response.BaseResponse;
import retrofit2.Response;


/**
 * Created by arysuryawan on 8/21/17.
 */

public class MainFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        showApiProgressDialog(App.getAppComponent(), new ResourceDao((BaseActivity) getActivity()) {
            @Override
            public void call() {
                this.postPhoneNumber("081212341212", (BaseActivity) getActivity(), BaseDao.getInstance(MainFragment.this, IConfig.KEY_CALLER_API_SOURCE).callback);
            }
        }, "Loading", false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    protected void onApiResponseCallback(BaseResponse mr, int keyID, Response response) {
//        if (mr.getBaseMeta().isStatus()) {
//            Toast.makeText(getActivity(), "Status: OK, Size= " + ((SourceResponse)mr).getSources().size(), Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(getActivity(), "Status: 200, but error", Toast.LENGTH_LONG).show();
//        }
    }


}
