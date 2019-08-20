package com.yuejianzhong.push

import android.support.v4.content.ContextCompat
import android.content.Intent
import android.os.Bundle
import cn.jpush.android.api.JPushInterface
import android.content.BroadcastReceiver
import android.content.Context
import com.alibaba.fastjson.JSONObject
import com.blankj.utilcode.util.LogUtils
import com.yuejianzhong.lattefragemt.ExampleActivity


class PushReceiver : BroadcastReceiver() {

    //广播接收后 执行的回调
    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras
        val keys = bundle!!.keySet()
        val json = JSONObject()
        for (key in keys) {
            val `val` = bundle.get(key)
            json.put(key, `val`)
        }

        LogUtils.json("PushReceiver", json.toJSONString())

        val pushAction = intent.action
        if (pushAction == JPushInterface.ACTION_NOTIFICATION_RECEIVED) {
            //处理接收到的消息类型的推送
            onReceivedMessage(bundle)

        } else if (pushAction == JPushInterface.ACTION_NOTIFICATION_OPENED) {
            //打开相应的Notification类型的通知推送
            onOpenNotification(context, bundle)
        }
    }

    private fun onReceivedMessage(bundle: Bundle) {
        val title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE)
        val msgId = bundle.getString(JPushInterface.EXTRA_MSG_ID)
        val notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID)
        val message = bundle.getString(JPushInterface.EXTRA_MESSAGE)
        val extra = bundle.getString(JPushInterface.EXTRA_EXTRA)
        val alert = bundle.getString(JPushInterface.EXTRA_ALERT)
    }

    private fun onOpenNotification(context: Context, bundle: Bundle) {
        val extra = bundle.getString(JPushInterface.EXTRA_EXTRA)
        val openActivityBundle = Bundle()
        val intent = Intent(context, ExampleActivity::class.java)
        intent.putExtras(openActivityBundle)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        ContextCompat.startActivity(context, intent, null)
    }

}
