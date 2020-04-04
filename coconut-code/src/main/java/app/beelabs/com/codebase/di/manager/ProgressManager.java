package app.beelabs.com.codebase.di.manager;


import android.content.Context;

import app.beelabs.com.codebase.component.dialog.SpinKitLoadingDialogComponent;
import app.beelabs.com.codebase.component.dialog.ProgressDialogComponent;
import app.beelabs.com.codebase.di.IProgress;

/**
 * Created by ary on 5/28/17.
 */

public class ProgressManager implements IProgress {
    @Override
    public void showProgressDialog(Context context, String message, boolean isCanceledOnTouch) {
        ProgressDialogComponent.showProgressDialog(context, message, isCanceledOnTouch);
    }

    @Override
    public void showSpinLoadingDialog(SpinKitLoadingDialogComponent dialog) {
        dialog.show();
    }


}
