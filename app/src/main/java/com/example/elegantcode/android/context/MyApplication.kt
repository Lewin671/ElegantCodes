package com.example.elegantcode.android.context

import android.app.Application
import android.content.Context

// 获取全局context
class MyApplication : Application() {
    companion object {
        private lateinit var context_: Context
        val context: Context
            get() = context_
    }

    override fun onCreate() {
        super.onCreate()
        context_ = applicationContext
    }

}