package app.beelabs.com.codebase.di;

import android.content.Context;

/**
 * Created by ary on 5/28/17.
 */

public interface IProgress {
    void showProgressDialog(Context context, String message, boolean isCanceledOnTouch);
}
