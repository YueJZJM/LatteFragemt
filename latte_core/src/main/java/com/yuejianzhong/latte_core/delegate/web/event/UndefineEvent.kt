package com.yuejianzhong.latte_core.delegate.web.event

import android.util.Log

class UndefineEvent : Event() {
    override fun execute(params: String): String? {
        Log.d("UndefineEvent",params)
        return null
    }

}