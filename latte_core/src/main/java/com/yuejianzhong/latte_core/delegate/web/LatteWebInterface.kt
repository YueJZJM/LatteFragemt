package com.yuejianzhong.latte_core.delegate.web

import com.alibaba.fastjson.JSON

class LatteWebInterface private constructor(webDelegate: WebDelegate){


    companion object{
        fun create(webDelegate: WebDelegate): LatteWebInterface {
            return LatteWebInterface(webDelegate)
        }
    }

    fun event(params: String):String? {
        val action = JSON.parseObject(params).getString("action");
        return null
    }

}