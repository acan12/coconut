package app.beelabs.com.codebase.component;

import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.base.contract.IView;
import app.beelabs.com.codebase.component.dialog.CoconutLostConnectionInternetDialog;

public class CoconutAlertInternetConnection {

    public static void show(String message, final IView iview) {
        CoconutLostConnectionInternetDialog dialog =
                new CoconutLostConnectionInternetDialog(message, iview.getCurrentActivity(),
                        R.style.CoconutDialogFullScreen);
        dialog.show();
    }
}
