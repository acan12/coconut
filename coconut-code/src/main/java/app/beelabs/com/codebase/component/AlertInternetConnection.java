package app.beelabs.com.codebase.component;

import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.base.contract.IView;
import app.beelabs.com.codebase.component.dialog.CoconutAlertInternetNoConnectionDialog;
import app.beelabs.com.codebase.support.util.SecurityUtil;

public class AlertInternetConnection {

    public static void show(String message, final IView iview) {
        boolean isConnected = SecurityUtil.isNetworkAvailable(iview.getCurrentActivity());
        if (!isConnected) {
            CoconutAlertInternetNoConnectionDialog dialog =
                    new CoconutAlertInternetNoConnectionDialog(message, iview.getCurrentActivity(),
                            R.style.CoconutDialogFullScreen);
            dialog.show();
        }
    }
}
