package app.beelabs.com.codebase.component.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import app.beelabs.com.codebase.IConfig;
import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.BaseDialog;
import app.beelabs.com.codebase.support.rx.RxTimer;

public class SpinKitLoadingDialogComponent extends BaseDialog {

    private String message;
    private long timerMilis;
    private TextView text;
    private static SpinKitLoadingDialogComponent dialog;

    public SpinKitLoadingDialogComponent(@NonNull String message, long timerMilis, Context context, int style) {
        super(context, style);
        this.message = message;
        this.timerMilis = timerMilis;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowContentDialogLayout(R.layout.dialog_coconut_spinkit_loading);
        dialog = this;

        showMessageLoadingInUI(message);
    }

    private void showMessageLoadingInUI(String message) {
        try {
            text = findViewById(R.id.coconut_spinkit_message);
            text.setText(message);
        } catch (Exception e) {
            Log.e("LOADING MESSAGE:", e.getMessage());
        }
    }

    synchronized public static SpinKitLoadingDialogComponent showProgressDialog(BaseActivity activity, String message, long timerMilis) {
        if (dialog == null) {
            message = message != null ? message : IConfig.DEFAULT_LOADING;

            dialog = new SpinKitLoadingDialogComponent(message, timerMilis, activity, R.style.CoconutDialogFullScreen);
            dialog.show();
        }

        while (dialog == null || dialog.isShowing()) return null;
        return dialog;
    }

    public static void dismissProgressDialog(BaseActivity ac, long timerMilis) {
        if (dialog == null) return;
        if (ac == null || !ac.isFinishing()) {
            RxTimer.doTimer(timerMilis, false, null);
            dialog.dismiss();
            dialog = null;
        }

    }
}
