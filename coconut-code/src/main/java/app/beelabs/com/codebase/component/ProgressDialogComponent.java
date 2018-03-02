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

    synchronized public static ProgressDialog showProgressDialog(Context context, String message) {
        if (dialog == null) {
            message = message != null ? message : IConfig.DEFAULT_LOADING;

            dialog = new ProgressDialog(context);
            dialog.setMessage(message + "...");
            dialog.show();
        }

        return dialog;
    }

    public static void dismissProgressDialog(BaseActivity ac) {
        try {
            if (!ac.isFinishing() && dialog != null) {
                dialog.dismiss();
                dialog = null;
            }
        }catch (Exception e){
            dialog.dismiss();
            Log.e("ProgressDialog", e.getMessage());
        }
    }
}
