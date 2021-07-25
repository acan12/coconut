package app.beelabs.com.codebase.component.dialog

import android.app.Activity
import android.os.Bundle
import app.beelabs.com.codebase.R
import app.beelabs.com.codebase.base.BaseDialog
import app.beelabs.com.codebase.base.contract.IView
import kotlinx.android.synthetic.main.dialog_coconut_alert_no_connection.*

class CoconutAlertNoConnectionDialog(val iview: IView)
    : BaseDialog(iview as Activity, R.style.CoconutDialogFullScreen) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowContentDialogLayout(R.layout.dialog_coconut_alert_no_connection)

        coconut_btn_reconnect.setOnClickListener {
            dismiss()
            iview.currentActivity.apply {
                finish()
                startActivity(intent)
            }
        }
    }

}