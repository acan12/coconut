package app.beelabs.com.codebase.base;

import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.component.ProgressDialogComponent;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by arysuryawan on 8/19/17.
 */

public class BaseDao implements IDao {

    private BasePresenter presenter;
    private int callbackKey;

    //    private IPresenter base = null;
    private IDao base = null;
    private IView iv = null;

    public BaseDao() {
    }

    public BaseDao(IDao dao) {
        this.base = dao;

    }

    private BaseDao(IDao dao, IView iv, int keyCallback) {
        this.base = dao;
        this.iv = iv;
        this.callbackKey = keyCallback;

    }


    public static BaseDao getInstance(IDao current) {
        return getInstance(current, null, -1);
    }

    public static BaseDao getInstance(IDao current, IView iv, int key) {
//        if (current instanceof BaseActivity)
//            return new BaseDao(current, key);
//        else if (current instanceof BaseFragment)
//            return new BaseDao(current, key);
//        return null;

        return new BaseDao(current, iv, key);
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }


    @Override
    public void onApiResponseCallback(BaseResponse br, int responseCode, Response response) {

    }

    @Override
    public void onApiFailureCallback(String message) {
        // --- default callback if not defined on child class --
//        try {
//            Toast.makeText(this, "Error: " + message, Toast.LENGTH_LONG).show();
//            Log.e("Message:", message);
//
//
//            if (((BaseActivity) iView).getRootView() != null)
//                showSnackbar(((BaseActivity) iView).getRootView(), getResources().getString(R.string.coconut_internet_fail_message), Snackbar.LENGTH_INDEFINITE).show();
//        } catch (Exception e) {
//            Log.e("", e.getMessage());
//        }

    }


    public static void onResponseCallback(Response response, IDao dao, IView iview, int responseCode) {
        ProgressDialogComponent.dismissProgressDialog(((BaseActivity) iview));

        dao.onApiResponseCallback((BaseResponse) response.body(), responseCode, response);
    }


    public static void onFailureCallback(Throwable t, IDao dao, IView iview) {
        ProgressDialogComponent.dismissProgressDialog(((BaseActivity) iview));
        dao.onApiFailureCallback(t.getMessage());

    }


    public Callback callback = new Callback() {

        @Override
        public void onResponse(retrofit2.Call call, retrofit2.Response response) {
            if (base == null) return;
            if (base instanceof BaseDao)
                BaseDao.onResponseCallback(response, base, iv, callbackKey);

        }

        @Override
        public void onFailure(retrofit2.Call call, Throwable t) {
            if (base != null && base instanceof BaseDao)
                BaseDao.onFailureCallback(t, base, iv);

        }
    };

}
