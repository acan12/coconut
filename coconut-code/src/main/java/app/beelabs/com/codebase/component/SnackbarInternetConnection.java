package app.beelabs.com.codebase.component;

import android.support.design.widget.Snackbar;
import android.view.View;

import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.base.IView;
import app.beelabs.com.codebase.support.util.SecurityUtil;

public class SnackbarInternetConnection {

    public static void show(String message, final IView iview) {
        if (!SecurityUtil.isNetworkAvailable(iview.getCurrentActivity())) {
            final Snackbar snackbar = Snackbar.make(iview.getContentView(), message, Snackbar.LENGTH_INDEFINITE);

            snackbar.show();
            snackbar.setAction(iview.getCurrentActivity().getResources().getString(R.string.coconut_reply_action_label), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    snackbar.dismiss();
                    iview.handleRetryConnection();
                }
            });
        }
    }
}
