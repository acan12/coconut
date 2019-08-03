package app.beelabs.com.coconut.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import app.beelabs.com.coconut.R;
import app.beelabs.com.coconut.model.api.response.ProfileResponseModel;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.presenter.ResourcePresenter;
import app.beelabs.com.coconut.ui.fragment.MainFragment;
import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.BasePresenter;
import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.support.rx.RxTimer;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements IMainView {
    @BindView(R.id.demo_image)
    ImageView demoImage;

//    @BindView(R.id.content1)
//    TextView content1;
    @BindView(R.id.content2)
    TextView content2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupCoconutContentView(R.id.root);
        ButterKnife.bind(this);

        showFragment(new MainFragment(), R.id.container, true);
    }

    private void callMultiApi() {

        ResourcePresenter resourcePresenter = ((ResourcePresenter) BasePresenter.getInstance(this, ResourcePresenter.class));
        resourcePresenter.getSourceRX("Ambil Data");
//        doGetProfile();

    }

    private void doGetProfile() {
        RxTimer.doTimer(3000, false, new RxTimer() {
            @Override
            public void onCallback(Long along) {
                ((ResourcePresenter) BasePresenter.getInstance(MainActivity.this, ResourcePresenter.class)).getProfileRX();
            }
        });

    }

    @OnClick(R.id.loadButton)
    public void onLoadButton(View view) {
        callMultiApi();


    }

    @Override
    public View getContentView() {
        return findViewById(R.id.root);
    }

    // handle response method
    @Override
    public void handleProcessing() {
        content2.setTextColor(getResources().getColor(R.color.color_grey));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void handleProfileDone(ProfileResponseModel model) {
        if (model.getMeta().isStatus()) {
            content2.setText("Full name: " + model.getData().getFull_name());
            content2.setTextColor(getResources().getColor(R.color.color_black_transparent80));
        } else {
            Toast.makeText(this, "Not Valid", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void handleDataSource(SourceResponse model) {
        Toast.makeText(this, model.getSources().size() + "", Toast.LENGTH_SHORT).show();
        Log.d("TEST", "testing handle data source");

//        startActivity(new Intent(this, SecondActivity.class));
    }


    @Override
    public void handleDataUpload(BaseResponse model) {
        Toast.makeText(this, model.getBaseMeta().getMessage() + "", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void handleRetryConnection() {
        callMultiApi();
    }
}