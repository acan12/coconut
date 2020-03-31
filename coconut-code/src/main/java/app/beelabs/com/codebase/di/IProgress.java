package app.beelabs.com.codebase.di;

import android.content.Context;

import app.beelabs.com.codebase.component.SpinKitLoadingDialogComponent;

/**
 * Created by ary on 5/28/17.
 */

public interface IProgress {
    void showProgressDialog(Context context, String message, boolean isCanceledOnTouch);

    void showSpinLoadingDialog(SpinKitLoadingDialogComponent dialog);
}
