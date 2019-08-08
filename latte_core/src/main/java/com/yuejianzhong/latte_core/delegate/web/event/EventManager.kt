package com.yuejianzhong.latte_core.delegate.web.event

import android.support.annotation.NonNull

class EventManager private constructor(){
    companion object{
        val EVENTS = HashMap<String,Event>()

        val instance = Holder.holder

    }

    private object Holder{
        val holder = EventManager()
    }

    fun addEvent(name: String, event: Event):EventManager {
        EVENTS.put(name, event)
        return this
    }

    fun createEvent(@NonNull action: String): Event {
        return EVENTS[action] ?: return UndefineEvent()
    }

}