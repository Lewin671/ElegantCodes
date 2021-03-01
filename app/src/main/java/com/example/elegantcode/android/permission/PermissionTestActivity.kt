package com.example.elegantcode.android.permission

import Logger
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.elegantcode.R

class PermissionTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission_test)

        findViewById<Button>(R.id.requestPermissionButton).setOnClickListener {
            Logger.d("开始使用PermissionUtil")
            PermissionUtil.with(this)
                .permission(Permission.READ_CONTACTS)
                .onGranted { Logger.d("onGranted $it") }
                .onDenied { Logger.d("onDenied $it") }
                .start()
        }
    }
}