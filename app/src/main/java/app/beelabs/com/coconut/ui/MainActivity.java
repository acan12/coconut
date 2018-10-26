package app.beelabs.com.coconut.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import app.beelabs.com.coconut.App;
import app.beelabs.com.coconut.R;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.presenter.ResourcePresenter;
import app.beelabs.com.coconut.ui.fragment.MainFragment;
import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.BasePresenter;
import app.beelabs.com.codebase.component.CoconutFrameLayout;
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

        showApiProgressDialog(App.getAppComponent(), BasePresenter.getInstance(this, new ResourcePresenter(this) {
            @Override
            public void call() {
                getSource();
            }
        }), "Please wait ", false);


        ((ResourcePresenter) BasePresenter.getInstance(this, new ResourcePresenter(this))).getSource();
        showFragment(new MainFragment(), R.id.container);

    }

    @Override
    public void handleDataSource(SourceResponse model) {
        Toast.makeText(this, model.getSources().size() + "", Toast.LENGTH_SHORT).show();
        Log.d("TEST", "testing handle data source");
    }

    @Override
    public void handleFail() {
        Toast.makeText(this,   "Internet Down!", Toast.LENGTH_SHORT).show();
    }
}
