package app.beelabs.com.coconut.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import app.beelabs.com.coconut.App;
import app.beelabs.com.coconut.IConfig;
import app.beelabs.com.coconut.R;
import app.beelabs.com.coconut.model.api.response.ArticleResponse;
import app.beelabs.com.coconut.presenter.dao.ResourceDao;
import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.BaseDao;
import app.beelabs.com.codebase.base.response.BaseResponse;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        showApiProgressDialog(App.getComponent(this), new ResourceDao(this) {
            @Override
            public void call() {
                this.getArticleDAO(MainActivity.this, BaseDao.getInstance(MainActivity.this, IConfig.KEY_CALLER_API_SOURCE).callback);
            }
        }, "Loading");
    }

    @Override
    protected void onApiResponseCallback(BaseResponse mr, int responseCode) {

        switch (responseCode) {
//            case IConfig.KEY_CALLER_API_SOURCE:
//                Toast.makeText(this, IConfig.KEY_CALLER_API_SOURCE, Toast.LENGTH_LONG).show();
//
//                showProgressDialogOnDAOCalled(new ResourceDao(this) {
//                    @Override
//                    public void call() {
//                        this.getSourcesDAO(MainActivity.this, BaseDao.getInstance(MainActivity.this, IConfig.KEY_CALLER_API_ARTICLE).callback);
//                    }
//                });
//
//                break;
//            case IConfig.KEY_CALLER_API_ARTICLE:
//                Toast.makeText(this, IConfig.KEY_CALLER_API_ARTICLE, Toast.LENGTH_LONG).show();
//                break;
            default:
                // line default code
                if (mr.getStatus().equals("ok")) {
                    Toast.makeText(this, "Status: OK, Size= " + ((ArticleResponse) mr).getSortBy(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Status: 200, but error", Toast.LENGTH_LONG).show();
                }
        }
    }

    @Override
    protected void onApiFailureCallback(String message) {
        Log.e("ERROR: ", message);
    }
}
