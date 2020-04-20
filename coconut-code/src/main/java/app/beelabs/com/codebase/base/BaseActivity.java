package app.beelabs.com.codebase.base;

import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.base.contract.IView;
import app.beelabs.com.codebase.component.dialog.SpinKitLoadingDialogComponent;
import app.beelabs.com.codebase.di.IProgress;
import app.beelabs.com.codebase.di.component.AppComponent;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/**
 * Created by arysuryawan on 8/16/17.
 */

public class BaseActivity extends AppCompatActivity implements IView, ComponentCallbacks2 {
    private BroadcastReceiver broadcastReceiver;
    private View rootView;
    private LocalBroadcastManager bManager;
    private BroadcastReceiver bReceiver;

    public void setupCoconutContentView(int rootIdLayout) {
        this.rootView = findViewById(rootIdLayout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerBroadCastReceiver();
    }

    @Override
    protected void onDestroy() {
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();

        /*
         * Uncomment this line in order to avoid memory leak.
         * You need to unregister the broadcast receiver since the broadcast receiver keeps a reference of the activity.
         * Now when its time for your Activity to die, the Android framework will call onDestroy() on it
         * but the garbage collector will not be able to remove the instance from memory because the broadcastReceiver
         * is still holding a strong reference to it.
         * */

        if (broadcastReceiver != null) {
            bManager.unregisterReceiver(broadcastReceiver);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        bManager.unregisterReceiver(broadcastReceiver);

        FragmentManager fm = getSupportFragmentManager();
        int backStackCount = fm.getBackStackEntryCount();
        if (backStackCount > 1) {
            fm.popBackStack();
        } else {
            fm.popBackStack();
            super.onBackPressed();
        }

    }

    public void showFragment(Fragment fragment, int fragmentResourceID) {
        showFragment(fragment, fragmentResourceID, false);
    }

    public void showFragment(Fragment fragment, int fragmentResourceID, boolean useFragmentBackStack) {

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(fragmentResourceID, fragment);
            if (useFragmentBackStack)
                fragmentTransaction.addToBackStack(fragment.getClass().getName());
            fragmentTransaction.detach(fragment);
            fragmentTransaction.attach(fragment);
            fragmentTransaction.commit();
        }
    }

    // handle progress dialog
    protected void showApiProgressDialog(AppComponent appComponent, BasePresenter presenter) {
        showApiProgressDialog(appComponent, presenter, null);
    }

    protected void showApiProgressDialog(AppComponent appComponent, BasePresenter presenter, String message) {
        if(message != null) {
            IProgress progress = appComponent.getProgressDialog();
            progress.showProgressDialog(getCurrentActivity(), message, false);
            presenter.call();
        }
    }

    protected void showApiProgressDialog(AppComponent appComponent, BasePresenter presenter, String message, boolean isCanceledOnTouch) {
        if(message != null) {
            IProgress progress = appComponent.getProgressDialog();
            progress.showProgressDialog(getCurrentActivity(), message, isCanceledOnTouch);
            presenter.call();
        }
    }


    protected void showApiWithSpinKitDialog(AppComponent appComponent, BasePresenter presenter, String message) {
        if(message != null) {
            IProgress progress = appComponent.getProgressDialog();
            progress.showSpinLoadingDialog(new SpinKitLoadingDialogComponent(message, 0, getCurrentActivity(), R.style.CoconutDialogFullScreen));
            presenter.call();
        }
    }

    private void registerBroadCastReceiver() {
        bReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //your receiver code goes here!
            }
        };

        bManager = LocalBroadcastManager.getInstance(getCurrentActivity());
        bManager.registerReceiver(bReceiver, new IntentFilter("CoconutAction"));
    }

    //--- end ----

    @Override
    public void handleError(String message) {

    }

    @Override
    public BaseActivity getCurrentActivity() {
        return this;
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void handleRetryConnection() {

    }
}
