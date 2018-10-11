package app.beelabs.com.codebase.base;

import retrofit2.Callback;

/**
 * Created by arysuryawan on 8/19/17.
 */

public class BaseDao {


    private int callbackKey;

    private IPresenter base = null;

    public BaseDao() {
    }

    public BaseDao(IPresenter obj) {
        this.base = obj;

    }

    private BaseDao(IPresenter obj, int keyCallback) {
        this.base = obj;
        this.callbackKey = keyCallback;
    }


    public static BaseDao getInstance(IPresenter current) {
        return getInstance(current, -1);
    }

    public static BaseDao getInstance(IPresenter current, int key) {
        if (current instanceof BaseActivity)
            return new BaseDao(current, key);
        else if (current instanceof BaseFragment)
            return new BaseDao(current, key);
        return null;
    }

    public Callback callback = new Callback() {

        @Override
        public void onResponse(retrofit2.Call call, retrofit2.Response response) {
            if (base == null) return;
            if (base instanceof BaseActivity)
                BaseActivity.onResponseCallback(response, base, callbackKey);
            else
                BaseFragment.onResponseCallback(response, base, callbackKey);

        }

        @Override
        public void onFailure(retrofit2.Call call, Throwable t) {
            if (base != null && base instanceof BaseActivity)
                BaseActivity.onFailureCallback(t, base);
            else
                BaseFragment.onFailureCallback(t, base);
        }
    };

}
