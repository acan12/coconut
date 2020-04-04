package app.beelabs.com.coconut.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import app.beelabs.com.coconut.App;
import app.beelabs.com.coconut.R;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.presenter.ResourcePresenter;
import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.support.rx.RxObserver;
import app.beelabs.com.codebase.support.rx.RxTimer;

public class SecondActivity extends BaseActivity implements ISecondView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        RxTimer.doTimer(3000, false, new RxTimer() {
            @Override
            public void onCallback(Long along) {
                callSources();
            }
        });

    }

    private void callSources() {
        showApiProgressDialog(App.getAppComponent(), new ResourcePresenter(this) {
            @Override
            public void call() {
                getSourceRX("Ambil Data", RxObserver.DialogTypeEnum.SPINKIT);
            }
        }, "lg nngambil data");
    }

    @Override
    public void handleDataSource(SourceResponse model) {
        Toast.makeText(this, model.getSources().size() + "->[Second]", Toast.LENGTH_SHORT).show();
        Log.d("TEST", "Second Activity");

    }
}
