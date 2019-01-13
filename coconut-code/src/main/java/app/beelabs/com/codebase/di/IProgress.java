package app.beelabs.com.codebase.di;

import android.content.Context;

import app.beelabs.com.codebase.component.IDialog;
import app.beelabs.com.codebase.component.LoadingDialogComponent;

/**
 * Created by ary on 5/28/17.
 */

public interface IProgress {
    void showProgressDialog(Context context, String message, boolean isCanceledOnTouch);

    void showLoadingDialog(LoadingDialogComponent dialog);
}
