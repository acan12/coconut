package app.beelabs.com.coconut.ui;

import android.os.Bundle;
import android.widget.Toast;

import app.beelabs.com.coconut.IConfig;
import app.beelabs.com.coconut.R;
import app.beelabs.com.coconut.presenter.ResourcePresenter;
import app.beelabs.com.coconut.ui.fragment.MainFragment;
import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.BasePresenter;
import app.beelabs.com.codebase.base.IPresenter;
import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.component.CoconutFrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;


public class MainActivity extends BaseActivity implements IPresenter {
    @BindView(R.id.root)
    CoconutFrameLayout rootView;

    private boolean outsource = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRootView(findViewById(R.id.root));
        ButterKnife.bind(this);

        ResourcePresenter presenter = (ResourcePresenter) BasePresenter.getInstance(ResourcePresenter.class);
        presenter.callSource(this, IConfig.KEY_CALLER_API_SOURCE);

        showFragment(new MainFragment(), R.id.container);

    }

    @Override
    protected void onApiFailureCallback(String message, IPresenter iView) {
        super.onApiFailureCallback(message, iView);
    }

    @Override
    protected void onApiResponseCallback(BaseResponse mr, int responseCode, Response response) {

        switch (responseCode) {
            case IConfig.KEY_CALLER_API_SOURCE:
                Toast.makeText(this, "api Source being called!", Toast.LENGTH_SHORT).show();

                break;
        }
    }


}
