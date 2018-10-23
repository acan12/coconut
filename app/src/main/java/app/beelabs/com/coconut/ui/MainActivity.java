package app.beelabs.com.coconut.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import app.beelabs.com.coconut.App;
import app.beelabs.com.coconut.R;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.presenter.ResourcePresenter;
import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.BasePresenter;
import app.beelabs.com.codebase.component.CoconutFrameLayout;
import app.beelabs.com.codebase.component.ProgressDialogComponent;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements IMainView {
    @BindView(R.id.root)
    CoconutFrameLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupCoconutContentView(R.id.root);
        ButterKnife.bind(this);

        showApiProgressDialog(App.getAppComponent(), new ResourcePresenter(this) {
            @Override
            public void call() {
                getSource();
            }

            @Override
            public void done() {
                Toast.makeText(MainActivity.this,"Done!", Toast.LENGTH_SHORT).show();
                ProgressDialogComponent.dismissProgressDialog(MainActivity.this);
            }
        }, "Please wait ", false);


//        ((ResourcePresenter) BasePresenter.getInstance(new ResourcePresenter(this))).getSource();
//        showFragment(new MainFragment(), R.id.container);

    }

    @Override
    public void handleData(SourceResponse model) {
        Toast.makeText(this, model.getSources().size() + "", Toast.LENGTH_SHORT).show();
        Log.d("TEST", "testing handle data source");
    }
}
