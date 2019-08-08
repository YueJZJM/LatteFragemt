package com.yuejianzhong.latte_core.delegate.web.event

import android.content.Context
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_core.delegate.web.event.IEvent

abstract class Event : IEvent {
    lateinit var context:Context
    lateinit var action:String
    lateinit var delegate:LatteDelegate
    lateinit var url:String
}