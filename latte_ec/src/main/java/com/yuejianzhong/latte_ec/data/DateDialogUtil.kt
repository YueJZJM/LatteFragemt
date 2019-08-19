package com.yuejianzhong.latte_ec.data

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.widget.DatePicker
import android.view.ViewGroup
import android.widget.LinearLayout
import java.text.SimpleDateFormat
import java.util.*


class DateDialogUtil {

    private var mDateListener: IDateListener? = null

    interface IDateListener {

        fun onDateChange(date: String)
    }

    fun setDateListener(listener: IDateListener) {
        this.mDateListener = listener
    }

    fun showDialog(context: Context) {
        val ll = LinearLayout(context)
        val picker = DatePicker(context)
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)

        picker.layoutParams = lp

        picker.init(1990, 1, 1) { view, year, monthOfYear, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, monthOfYear, dayOfMonth)
            val format = SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault())
            val data = format.format(calendar.getTime())
            if (mDateListener != null) {
                mDateListener!!.onDateChange(data)
            }
        }

        ll.addView(picker)

        AlertDialog.Builder(context)
                .setTitle("选择日期")
                .setView(ll)
                .setPositiveButton("确定") { dialog, which -> }
                .setNegativeButton("取消", { dialog, which -> })
                .show()
    }

}
