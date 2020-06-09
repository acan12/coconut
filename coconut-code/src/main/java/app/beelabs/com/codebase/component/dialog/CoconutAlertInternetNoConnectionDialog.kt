package app.beelabs.com.codebase.component.dialog

import android.app.Activity
import android.os.Bundle
import app.beelabs.com.codebase.R
import app.beelabs.com.codebase.base.BaseDialog
import app.beelabs.com.codebase.base.contract.IView
import kotlinx.android.synthetic.main.dialog_coconut_alert_internet_noconnection.*

class CoconutAlertInternetNoConnectionDialog(val message: String?, private val iview: IView, style: Int) : BaseDialog(iview as Activity, style) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowContentDialogLayout(R.layout.dialog_coconut_alert_internet_noconnection)

        if(message != null) coconut_spinkit_message.text = message
        close_dialog.setOnClickListener {
            dismiss()
            iview.handleRetryConnection();
        }
    }

}