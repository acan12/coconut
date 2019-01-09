package app.beelabs.com.codebase.component;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.base.BaseDialog;
import butterknife.ButterKnife;

public class LoadingDialogComponent extends BaseDialog {
    private Activity activity;

    public LoadingDialogComponent(@NonNull Context context, int style) {
        super(context, style);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowContentDialogLayout(R.layout.dialog_loading);
        ButterKnife.bind(this);

    }
}
