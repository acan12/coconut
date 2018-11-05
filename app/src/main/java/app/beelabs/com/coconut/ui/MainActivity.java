package app.beelabs.com.coconut.ui;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import app.beelabs.com.coconut.App;
import app.beelabs.com.coconut.R;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.presenter.ResourcePresenter;
import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.BasePresenter;
import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.component.CoconutFrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements IMainView {
    @BindView(R.id.root)
    CoconutFrameLayout rootView;

    @BindView(R.id.demo_image)
    ImageView demoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupCoconutContentView(R.id.root);
        ButterKnife.bind(this);

        // way 1
//        showApiProgressDialog(App.getAppComponent(), BasePresenter.getInstance(this, new ResourcePresenter(this) {
//            @Override
//            public void call() {
//
////                getSource();
//
//
//
//                Uri imageUri = Uri.fromFile(new File(getAssets()+"/demo.png"));
//                File f = new File(imageUri.getPath());
//                Log.d("file exist? ", f.exists()+"");
//                try {
//
//                    InputStream stream = getAssets().open("demo.png");
//                    Drawable d = Drawable.createFromStream(stream, null);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//                uploadFile("testing", "09:00", "17:00",
//                        "2018-10-24", "2018-10-24", "1", null);
//            }
//
//            // custom override when done loading
////            @Override
////            public void done() {
////                super.done();
////                Toast.makeText(MainActivity.this, "Done!", Toast.LENGTH_SHORT).show();
////            }
//        }), "Please wait ", false);

        // way 2
        ((ResourcePresenter) BasePresenter.getInstance(this, new ResourcePresenter(this))).getSource();

//        showFragment(new MainFragment(), R.id.container);

    }

    @Override
    public void handleDataSource(SourceResponse model) {
        Toast.makeText(this, model.getSources().size() + "", Toast.LENGTH_SHORT).show();
        Log.d("TEST", "testing handle data source");
    }

    @Override
    public void handleFail() {
        Toast.makeText(this, "Internet Down!", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void handleDataUpload(BaseResponse model) {
        Toast.makeText(this, model.getBaseMeta().getMessage() + "", Toast.LENGTH_SHORT).show();
    }
}
