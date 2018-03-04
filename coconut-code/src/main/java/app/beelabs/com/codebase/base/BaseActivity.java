package app.beelabs.com.codebase.base;

import android.content.ComponentCallbacks2;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.component.ProgressDialogComponent;
import app.beelabs.com.codebase.di.IProgress;
import app.beelabs.com.codebase.di.component.AppComponent;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by arysuryawan on 8/16/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements ComponentCallbacks2 {
    protected void onApiResponseCallback(BaseResponse br, int responseCode, Response response) {
    }


    protected void onApiFailureCallback(String message) {
        // --- default callback if not defined on child class --
        Toast.makeText(this, "Error: " + message, Toast.LENGTH_LONG).show();
        Log.e("Message:", message);
    }

    public void showFragment(Fragment fragment, int fragmentResourceID) {

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(fragmentResourceID, fragment);
            fragmentTransaction.detach(fragment);
            fragmentTransaction.attach(fragment);
            fragmentTransaction.commit();
        }
    }

    protected BaseFragment onBackFragment(BaseActivity activity){
        List fragments = activity.getSupportFragmentManager().getFragments();
        BaseFragment currentFragment = (BaseFragment) fragments.get(fragments.size() - 1);

        return currentFragment;
    }

    public static void onResponseCallback(Call<BaseResponse> call, Response response, BaseActivity ac, int responseCode) {
        ProgressDialogComponent.dismissProgressDialog(ac);
        ac.onApiResponseCallback((BaseResponse) response.body(), responseCode, response);
    }

    public static void onFailureCallback(Throwable t, BaseActivity ac) {
        ProgressDialogComponent.dismissProgressDialog(ac);
        ac.onApiFailureCallback(t.getMessage());
    }

    protected void showApiProgressDialog(AppComponent appComponent, BaseDao dao) {
        showApiProgressDialog(appComponent, dao, null);
    }

    protected void showApiProgressDialog(AppComponent appComponent, BaseDao dao, String message) {
        IProgress progress = appComponent.getProgressDialog();
        progress.showProgressDialog(this, message, true);
        dao.call();
    }

    protected void showApiProgressDialog(AppComponent appComponent, BaseDao dao, String message, boolean isCanceledOnTouch) {
        IProgress progress = appComponent.getProgressDialog();
        progress.showProgressDialog(this, message, isCanceledOnTouch);
        dao.call();
    }

}
