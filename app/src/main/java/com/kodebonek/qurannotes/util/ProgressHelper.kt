package com.djakartalloyd.dlmarket.util

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import com.kodebonek.qurannotes.R

class ProgressHelper(val activity: Activity) {

    private var dialog: Dialog? = null

    fun show(message: String?) {
        if (dialog == null) {
            dialog = Dialog(activity)
            dialog!!.setCancelable(false)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setContentView(R.layout.dialog_progress)
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

            val progressBar = dialog!!.findViewById(R.id.progressBar) as ProgressBar
            //progressBar.indeterminateDrawable.setColorFilter(-0x1, android.graphics.PorterDuff.Mode.MULTIPLY)
            progressBar.indeterminateDrawable.setColorFilter(ContextCompat.getColor(activity, R.color.green), PorterDuff.Mode.SRC_IN)
        }

        if (dialog!!.isShowing)
            return

        if (!message.isNullOrEmpty()) {
            val editText = dialog!!.findViewById(R.id.textview) as TextView
            editText.text = message
        }
        dialog!!.show()
    }

    fun dismiss() {
        if (dialog?.isShowing == false)
            return
        dialog?.dismiss()
    }
}