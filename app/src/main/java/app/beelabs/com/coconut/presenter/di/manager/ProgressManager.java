package app.beelabs.com.coconut.presenter.di.manager;


import android.content.Context;

import app.beelabs.com.coconut.presenter.di.IProgress;
import app.beelabs.com.codebase.component.ProgressDialogComponent;

/**
 * Created by ary on 5/28/17.
 */

public class ProgressManager implements IProgress {
    @Override
    public void showProgressDialog(Context context, String message) {
        ProgressDialogComponent.showProgressDialog(context, message);
    }
}
