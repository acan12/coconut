package app.beelabs.com.codebase.component;

import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.base.contract.IView;
import app.beelabs.com.codebase.component.dialog.CoconutAlertNetworkNoConnectionDialog;
import app.beelabs.com.codebase.support.util.SecurityUtil;

public class AlertInternetConnection {

    public static void show(String message, final IView iview) {
        boolean isConnected = SecurityUtil.isNetworkAvailable(iview.getCurrentActivity());
        if (!isConnected) {
            CoconutAlertNetworkNoConnectionDialog dialog = new CoconutAlertNetworkNoConnectionDialog(iview.getCurrentActivity(), R.style.CoconutDialogFullScreen);

            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
//            BaseDialog dialog = (new AlertNetworkNoConnectionDialog(message, iview.getCurrentActivity(), R.style.CoconutDialogFullScreen));
//            dialog.show();
//            View viewLayout = iview.getCurrentActivity().getWindow().getDecorView().getRootView();
//            final Snackbar snackbar = Snackbar.make(viewLayout, message, Snackbar.LENGTH_INDEFINITE);
//            snackbar.show();
//            snackbar.setAction(iview.getCurrentActivity().getResources().getString(R.string.coconut_reply_action_label), new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    snackbar.dismiss();
//                    iview.handleRetryConnection();
//                }
//            });
        }
    }
}
