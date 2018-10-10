package com.kodebonek.qurannotes.util

import android.annotation.SuppressLint
import android.content.Context
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AlertDialog
import android.util.TypedValue

class UiHelper {

    companion object {

        @SuppressLint("RestrictedApi")
        fun disableShiftMode(view: BottomNavigationView) {
            val menuView = view.getChildAt(0) as BottomNavigationMenuView
            try {
                val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
                shiftingMode.isAccessible = true
                shiftingMode.setBoolean(menuView, false)
                shiftingMode.isAccessible = false
                for (i in 0 until menuView.childCount) {
                    val item = menuView.getChildAt(i) as BottomNavigationItemView
                    item.setShiftingMode(false)
                    // set once again checked value, so view will be updated
                    item.setChecked(item.itemData.isChecked)
                }
            } catch (e: NoSuchFieldException) {
                //Timber.e(e, "Unable to get shift mode field");
            } catch (e: IllegalAccessException) {
                //Timber.e(e, "Unable to change value of shift mode");
            }
        }

        fun showAlertDialog(context: Context, title: String, message: String) {
            val alertDialog = AlertDialog.Builder(context).create()

            alertDialog.setTitle(title)
            alertDialog.setMessage(message)
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK"
            ) { dialog, _ -> dialog.dismiss() }

            alertDialog.show()
        }

        fun dpToPx(context: Context, dp: Float): Float {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
        }

        fun pxToDp(context: Context, pixel: Float): Float {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixel, context.resources.displayMetrics)
        }

        fun spToDp(context: Context, pixel: Float): Float {
            return pixel / context.resources.displayMetrics.scaledDensity
        }

        fun showConfirm(context: Context?, text: String, callback: (Boolean) -> Unit) {
            if (context == null) return

            val dialog = AlertDialog.Builder(context)
                    .setMessage(text)
                    .setPositiveButton(android.R.string.ok, { _, _ -> callback.invoke(true) })
                    .setNegativeButton(android.R.string.cancel, { _, _ -> callback.invoke(false) })
                    .create()
            dialog.show()
        }
    }
}