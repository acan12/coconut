package app.beelabs.com.codebase.base;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.component.ProgressDialogComponent;
import app.beelabs.com.codebase.di.IProgress;
import app.beelabs.com.codebase.di.component.AppComponent;
import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by arysuryawan on 8/16/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements ComponentCallbacks2 {
    private View rootView;

    public View getRootView() {
        return rootView;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    protected void onApiResponseCallback(BaseResponse br, int responseCode, Response response) {
    }

    protected void onApiFailureCallback(String message, BaseActivity ac) {
        // --- default callback if not defined on child class --
        try {
            Toast.makeText(this, "Error: " + message, Toast.LENGTH_LONG).show();
            Log.e("Message:", message);

            if (ac.getRootView() != null)
                showSnackbar(ac.getRootView(), getResources().getString(R.string.coconut_internet_fail_message), Snackbar.LENGTH_INDEFINITE).show();
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }

    }

    protected Snackbar showSnackbar(View view, String message, int duration) {
        final Snackbar snackbar = Snackbar.make(view, message, duration);

        snackbar.setAction(getResources().getString(R.string.coconut_reply_action_label), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();

                finish();
                startActivity(getIntent());
            }
        });

        return snackbar;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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

    protected BaseFragment onBackFragment(BaseActivity activity) {
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
        ac.onApiFailureCallback(t.getMessage(), ac);

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
