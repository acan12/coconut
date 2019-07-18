package app.beelabs.com.codebase.base;

import app.beelabs.com.codebase.base.response.BaseResponse;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by arysuryawan on 8/19/17.
 */

public class BaseDao implements IDao {

    private static BaseDao base;
    private int callbackKey;

    private IDao dao;
    private BasePresenter bp;
    private String key;

    public BaseDao() {
    }

    public BaseDao(IDao dao) {
        this.dao = dao;

    }

    private BaseDao(IDao dao, BasePresenter bp, int keyCallback) {
        this.dao = dao;
        this.bp = bp;
        this.callbackKey = keyCallback;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static BaseDao getInstance(IDao current, BasePresenter bp, int key) {
        if (base == null || base.getKey() == null || !base.getKey().equals(key))
            base = new BaseDao(current, bp, key);

        return base;
    }


    @Override
    public BasePresenter getPresenter() {
        return null;
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
//            if (((BaseActivity) iView).getContentView() != null)
//                showSnackbar(((BaseActivity) iView).getContentView(), getResources().getString(R.string.coconut_internet_fail_message), Snackbar.LENGTH_INDEFINITE).show();
//        } catch (Exception e) {
//            Log.e("", e.getMessage());
//        }

    }


    public void onResponseCallback(Response response, IDao dao, int responseCode) {
        bp.done();
        dao.onApiResponseCallback((BaseResponse) response.body(), responseCode, response);
    }


    public void onFailureCallback(Throwable t, IDao dao) {
        bp.fail(t.getMessage());
        dao.onApiFailureCallback(t.getMessage());
    }


    public Callback callback = new Callback() {

        @Override
        public void onResponse(retrofit2.Call call, retrofit2.Response response) {
            if (dao == null) return;
            if (dao instanceof BaseDao)
                base.onResponseCallback(response, dao, callbackKey);
        }

        @Override
        public void onFailure(retrofit2.Call call, Throwable t) {
            if (dao != null && dao instanceof BaseDao)
                base.onFailureCallback(t, dao);
        }
    };

}
