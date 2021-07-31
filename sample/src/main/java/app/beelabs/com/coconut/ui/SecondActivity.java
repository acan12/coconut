package app.beelabs.com.coconut.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

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
        new ResourcePresenter(this).getSourceRX("Lg ngambil Data", RxObserver.DialogTypeEnum.DEFAULT);
    }

    @Override
    public void handleDataSource(SourceResponse model) {
        Toast.makeText(this, model.getSources().size() + "->[Second]", Toast.LENGTH_SHORT).show();
        Log.d("TEST", "Second Activity");

    }
}
