package com.yuejianzhong.latte_core.delegate.web.event

interface IEvent {
    fun execute(params:String):String?
}