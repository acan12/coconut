package app.beelabs.com.codebase.component.dialog

import android.app.Activity
import android.os.Bundle
import app.beelabs.com.codebase.R
import app.beelabs.com.codebase.base.BaseDialog
import app.beelabs.com.codebase.base.contract.IView
import kotlinx.android.synthetic.main.dialog_coconut_alert_no_connection.*

class CoconutAlertNoConnectionDialog(val message: String?, private val iview: IView, style: Int)
    : BaseDialog(iview as Activity, style) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowContentDialogLayout(R.layout.dialog_coconut_alert_no_connection)

        if (message != null) coconut_message.text = message
        coconut_btn_reconnect.setOnClickListener {
            dismiss()
            iview.handleReconnection()
        }
    }

}