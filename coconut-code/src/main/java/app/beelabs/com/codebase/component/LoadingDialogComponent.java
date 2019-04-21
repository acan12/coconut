package app.beelabs.com.codebase.component;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import app.beelabs.com.codebase.IConfig;
import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.BaseDialog;
import butterknife.ButterKnife;

public class LoadingDialogComponent extends BaseDialog {

    private String message;
    private static LoadingDialogComponent dialog;
    private TextView text;


    public LoadingDialogComponent(@NonNull String message, Context context, int style) {
        super(context, style);
        this.message = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowContentDialogLayout(R.layout.dialog_loading);
        ButterKnife.bind(this);

        dialog = this;

        text = (TextView) findViewById(R.id.loading_text);
        text.setText(message);
    }

    synchronized public static LoadingDialogComponent openLoadingDialog(Context context, String message) {
        if (dialog == null) {
            message = message != null ? message : IConfig.DEFAULT_LOADING;

            dialog = new LoadingDialogComponent(message, context, R.style.CoconutDialogFullScreen);
            dialog.show();
        }

        return dialog;

    }

    public static void closeLoadingDialog(BaseActivity ac) {
        if (dialog == null) return;
        if (ac == null || !ac.isFinishing()) {
            dialog.dismiss();
            dialog = null;
        }

    }
}
