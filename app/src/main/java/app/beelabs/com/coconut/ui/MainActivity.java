package app.beelabs.com.coconut.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import app.beelabs.com.coconut.R;
import app.beelabs.com.coconut.model.api.response.ProfileResponseModel;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.presenter.ResourcePresenter;
import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.BasePresenter;
import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.component.CoconutFrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;



public class MainActivity extends BaseActivity implements IMainView {
    @BindView(R.id.root)
    CoconutFrameLayout rootView;

    @BindView(R.id.demo_image)
    ImageView demoImage;

    @BindView(R.id.content1)
    TextView content1;
    @BindView(R.id.content2)
    TextView content2;


    private Disposable disposable;
    private Observable<ProfileResponseModel> profileObservable;

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
//        doWay1();
        doWayPremi();


//        showFragment(new MainFragment(), R.id.container);

    }

    private void doWayPremi() {


        ((ResourcePresenter) BasePresenter.getInstance(this, new ResourcePresenter(this)))
                .getProfileRX();

//        Observable.interval(0, 1000,
//                TimeUnit.MILLISECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Long>() {
//                               @Override
//                               public void accept(Long aLong) throws Exception {
//                                   content1.setText(String.valueOf(aLong));
//                               }
//                           }
//                );


    }

    private void doWay1() {

        Observer<SourceResponse> observer = new Observer<SourceResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
                content2.setText("Loading...");
//                Toast.makeText(MainActivity.this, "Load onsubscribe", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(SourceResponse model) {
                String value = model.getSources().get(0).getDescription();
                content2.setText(value);

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "Load ERROR", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
//                Toast.makeText(MainActivity.this, "Load COmpleted", Toast.LENGTH_SHORT).show();
            }
        };

        ((ResourcePresenter) BasePresenter.getInstance(this, new ResourcePresenter(this)))
                .getSourceRX().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

//        ((ResourcePresenter) BasePresenter.getInstance(this, new ResourcePresenter(this)))
//                .getSource();
        ((ResourcePresenter) BasePresenter.getInstance(this, new ResourcePresenter(this)))
                .getSourceRX().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(MainActivity.this, "Destroy", Toast.LENGTH_SHORT).show();
        disposable.dispose();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    // handle response method


    @Override
    public void handleProfileComplete(ProfileResponseModel model) {
        if (model.getMeta().isStatus()) {
            content2.setText("Full name: " + model.getData().getFull_name());
        } else {
            Toast.makeText(this, "Not Valid", Toast.LENGTH_SHORT).show();
        }
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