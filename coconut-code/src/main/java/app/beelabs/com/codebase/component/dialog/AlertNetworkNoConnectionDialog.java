package app.beelabs.com.codebase.component.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.base.BaseDialog;

public class AlertNetworkNoConnectionDialog extends BaseDialog {
    private final Activity activity;

    public AlertNetworkNoConnectionDialog(@NonNull Activity activity, int style) {
        super(activity, style);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowContentDialogLayout(R.layout.dialog_alert_network_noconnection);
    }
}
