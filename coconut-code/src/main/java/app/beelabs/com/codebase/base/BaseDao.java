package app.beelabs.com.codebase.base;
import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.BaseFragment;
import retrofit2.Callback;

/**
 * Created by arysuryawan on 8/19/17.
 */

public class BaseDao {

    private int keyID;

    private static BaseActivity ac = null;
    private static BaseFragment fm = null;


    public BaseDao(Object obj) {
        if (obj instanceof BaseActivity) this.ac = ac;
        if (obj instanceof BaseFragment) this.fm = fm;
    }

    private BaseDao(Object obj, int keyID) {
        if (obj instanceof BaseActivity) {
            this.ac = (BaseActivity) obj;
            this.keyID = keyID;
        }
        if (obj instanceof BaseFragment) {
            this.fm = (BaseFragment) obj;
            this.keyID = keyID;
        }
    }


    public static BaseDao getInstance(Object current) {
        return getInstance(current, 0);
    }

    public static BaseDao getInstance(Object current, int keyID) {
        if (current instanceof BaseActivity)
            return new BaseDao(current, keyID);
        else if (current instanceof BaseFragment)
            return new BaseDao(current, keyID);
        return null;
    }

    public Callback callback = new Callback() {

        @Override
        public void onResponse(retrofit2.Call call, retrofit2.Response response) {
            if (ac != null)
                BaseActivity.onResponseCallback(call, response, ac, keyID);
            else
                BaseFragment.onResponseCallback(call, response, fm, keyID);
        }

        @Override
        public void onFailure(retrofit2.Call call, Throwable t) {
            if (ac != null)
                BaseActivity.onFailureCallback(t, ac);
            else
                BaseFragment.onFailureCallback(t, fm);
        }
    };


    public void call() {
    }
}
