package app.beelabs.com.codebase.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.base.contract.IView;
import app.beelabs.com.codebase.component.dialog.SpinKitLoadingDialogComponent;
import app.beelabs.com.codebase.di.IProgress;
import app.beelabs.com.codebase.di.component.AppComponent;

import static android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;

/**
 * Created by arysuryawan on 8/16/17.
 */

public class BaseActivity extends AppCompatActivity implements IView, ComponentCallbacks2 {
    private BroadcastReceiver broadcastReceiver;
    private LocalBroadcastManager bManager;
    private BroadcastReceiver bReceiver;

    protected void setupStatusBarStyle(int statusBarColor, boolean lightOn, Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (lightOn) {
                activity.getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
            if (statusBarColor != 0) activity.getWindow().setStatusBarColor(statusBarColor);
        }
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
    public void onBackPressed() {
        bManager.unregisterReceiver(broadcastReceiver);

        FragmentManager fm = getSupportFragmentManager();
        int backStackCount = fm.getBackStackEntryCount();
        fm.popBackStack();
        if (backStackCount <= 1) {
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
    @Deprecated
    protected void showApiProgressDialog(AppComponent appComponent, BasePresenter presenter) {
        showApiProgressDialog(appComponent, presenter, null);
    }

    @Deprecated
    protected void showApiProgressDialog(AppComponent appComponent, BasePresenter presenter, String message) {
        if (message != null) {
            IProgress progress = appComponent.getProgressDialog();
            progress.showProgressDialog(getCurrentActivity(), message, false);
            presenter.call();
        }
    }

    @Deprecated
    protected void showApiProgressDialog(AppComponent appComponent, BasePresenter presenter, String message, boolean isCanceledOnTouch) {
        if (message != null) {
            IProgress progress = appComponent.getProgressDialog();
            progress.showProgressDialog(getCurrentActivity(), message, isCanceledOnTouch);
            presenter.call();
        }
    }

    @Deprecated
    protected void showApiWithSpinKitDialog(AppComponent appComponent, BasePresenter presenter, String message) {
        if (message != null) {
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
    public void handleRetryConnection() {

    }
}
