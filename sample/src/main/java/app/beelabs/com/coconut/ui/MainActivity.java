package app.beelabs.com.coconut.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import app.beelabs.com.coconut.R;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.presenter.ResourcePresenter;
import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.support.rx.RxObserver;


public class MainActivity extends BaseActivity implements IMainView {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callSources();

        setupStatusBarStyle(Color.GREEN, true, this);
//        showFragment(new MainFragment(), R.id.container, true);
    }

    private void callSources() {
        new ResourcePresenter(this).getSourceRX("Ambil Data", RxObserver.DialogTypeEnum.DEFAULT);
    }

//    @OnClick(R.id.loadButton)
//    public void onLoadButton(View view) {
////        callSources();
//    }

    @Override
    public void handleSuccess(BaseResponse response) {
        SourceResponse model = (SourceResponse) response;
        ((TextView)findViewById(R.id.word)).setText("Source Data :" + model.getSources().size());
        Toast.makeText(this, "Source Data :" + model.getSources().size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleReconnection() {

    }

    @Override
    public void handleErrorResponse(BaseResponse response) {

    }

    // handle response method
//    @Override
//    public void handleProcessing() {
//        word.setTextColor(getResources().getColor(R.color.color_grey));
//    }
//
//    @SuppressLint("SetTextI18n")
//    @Override
//    public void handleProfileDone(ProfileResponseModel model) {
//        if (model.getMeta().isStatus()) {
//            word.setText("Full name: " + model.getData().getFull_name());
//            word.setTextColor(getResources().getColor(R.color.color_black_transparent80));
//        } else {
//            Toast.makeText(this, "Not Valid", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public void handleDataSource(SourceResponse model) {
//        Toast.makeText(this, model.getSources().size() + "", Toast.LENGTH_SHORT).show();
//        Log.d("TEST", "testing handle data source");
//
//        startActivity(new Intent(this, SecondActivity.class));
//    }
//
//
//    @Override
//    public void handleDataUpload(BaseResponse model) {
//        Toast.makeText(this, model.getBaseMeta().getMessage() + "", Toast.LENGTH_SHORT).show();
//    }
//
//
//    @Override
//    public void handleRetryConnection() {
//        callSources();
//    }
//
//    @Override
//    public void handleError(String message) {
//        Toast.makeText(this, "Error Api Failed!!", Toast.LENGTH_SHORT).show();
//    }
}