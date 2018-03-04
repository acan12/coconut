package app.beelabs.com.codebase.component;


import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import app.beelabs.com.codebase.IConfig;
import app.beelabs.com.codebase.base.BaseActivity;


/**
 * Created by arysuryawan on 8/19/17.
 */

public class ProgressDialogComponent {
    private static ProgressDialog dialog;

    synchronized public static ProgressDialog showProgressDialog(Context context, String message, boolean isCanceledOnTouch) {
        if (dialog == null) {
            message = message != null ? message : IConfig.DEFAULT_LOADING;

            dialog = new ProgressDialog(context);
            dialog.setMessage(message + "...");
            dialog.setCanceledOnTouchOutside(isCanceledOnTouch);
            dialog.show();
        }

        return dialog;
    }

    public static void dismissProgressDialog(BaseActivity ac) {
        if (dialog == null) return;
        if (ac == null || !ac.isFinishing()) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
