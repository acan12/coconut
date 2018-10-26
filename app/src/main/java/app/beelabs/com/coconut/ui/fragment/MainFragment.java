package app.beelabs.com.coconut.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app.beelabs.com.coconut.App;
import app.beelabs.com.coconut.model.api.response.SummaryResponse;
import app.beelabs.com.coconut.presenter.ResourcePresenter;
import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.BaseFragment;
import app.beelabs.com.codebase.base.BasePresenter;


/**
 * Created by arysuryawan on 8/21/17.
 */

public class MainFragment extends BaseFragment implements IMainFragmentView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        showApiProgressDialog(App.getAppComponent(), BasePresenter.getInstance((BaseActivity) getActivity(), new ResourcePresenter(this) {
            @Override
            public void call() {
                postPhoneNumber("081212341212");
            }
        }), "Please wait ", false);
        ;

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void handleDataSummary(SummaryResponse model) {
        Toast.makeText(getActivity(), model.getDescriptionCode() + "", Toast.LENGTH_SHORT).show();
        Log.d("TEST", "testing handle data summary");
    }


    @Override
    public void handleFail() {
        Toast.makeText(getActivity(), "Fragment Internet Down!", Toast.LENGTH_SHORT).show();
    }

}
