package app.beelabs.com.coconut.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import app.beelabs.com.coconut.R;
import app.beelabs.com.coconut.model.api.response.ProfileResponseModel;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.presenter.ResourcePresenter;
import app.beelabs.com.coconut.ui.fragment.MainFragment;
import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.support.rx.RxObserver;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements IMainView {

    @BindView(R.id.word)
    TextView word;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        showFragment(new MainFragment(), R.id.container, true);
    }

    private void callSources() {
        new ResourcePresenter(this).getSourceRX("Ambil Data", RxObserver.DialogTypeEnum.SPINKIT);
    }

    @OnClick(R.id.loadButton)
    public void onLoadButton(View view) {
        callSources();
//        AlertNetworkNoConnectionDialog dialog = new AlertNetworkNoConnectionDialog(this, R.style.CoconutDialogFullScreen);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.show();
    }

    // handle response method
    @Override
    public void handleProcessing() {
        word.setTextColor(getResources().getColor(R.color.color_grey));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void handleProfileDone(ProfileResponseModel model) {
        if (model.getMeta().isStatus()) {
            word.setText("Full name: " + model.getData().getFull_name());
            word.setTextColor(getResources().getColor(R.color.color_black_transparent80));
        } else {
            Toast.makeText(this, "Not Valid", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void handleDataSource(SourceResponse model) {
        Toast.makeText(this, model.getSources().size() + "", Toast.LENGTH_SHORT).show();
        Log.d("TEST", "testing handle data source");

        startActivity(new Intent(this, SecondActivity.class));
    }


    @Override
    public void handleDataUpload(BaseResponse model) {
        Toast.makeText(this, model.getBaseMeta().getMessage() + "", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void handleRetryConnection() {
        callSources();
    }

    @Override
    public void handleError(String message) {
        Toast.makeText(this, "Error Api Failed!!", Toast.LENGTH_SHORT).show();
    }
}