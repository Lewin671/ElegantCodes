package com.example.elegantcode.android.permission

import Logger
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build

/**
 * 动态申请权限工具类
 */
class PermissionUtil(private val context: Context) {

    // 存储需要添加的权限map
    private var permissions = HashSet<Permission>()


    private lateinit var grantedAction: ((Collection<Permission>) -> Unit)
    private lateinit var deniedAction: ((Collection<Permission>) -> Unit)

    companion object {
        // 创建一个PermissionUtil
        fun with(context: Context): PermissionUtil {
            return PermissionUtil(context)
        }
    }


    // 添加申请的权限
    fun permission(permission: String,title:String? = null,message:String? = null): PermissionUtil {
        permissions.add(Permission(permission,title,message))
        return this
    }


    // 设置申请成功后的回调
    fun onGranted(action: ((Collection<Permission>) -> Unit)): PermissionUtil {
        grantedAction = action
        return this
    }

    // 设置申请失败后的回调
    fun onDenied(action: ((Collection<Permission>) -> Unit)): PermissionUtil {
        deniedAction = action
        return this
    }

    // 判断是否已经拥有此权限
    private fun checkPermission(permission: String): Boolean {
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }


    fun start() {
        if (Build.VERSION.SDK_INT < 23) {
            grantedAction.invoke(permissions)
        } else {
            val intent = Intent(context, ActivityWithoutUi::class.java)
            ActivityWithoutUi.deniedAction = deniedAction
            ActivityWithoutUi.grantedAction = grantedAction
            permissions = permissions
                .filter { permission -> !checkPermission(permission.name) }
                .toHashSet()
            ActivityWithoutUi.permissions = permissions


            if (ActivityWithoutUi.permissions.isNotEmpty()) {
                Logger.d("开始请求permissions")
                context.startActivity(intent)
            } else {
                Logger.d("已经拥有permissions")
                grantedAction.invoke(permissions)
            }
        }
    }
}