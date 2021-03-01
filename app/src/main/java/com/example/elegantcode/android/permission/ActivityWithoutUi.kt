package com.example.elegantcode.android.permission

import Logger
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


// 这里不能继承AppCompatActivity，否则透明效果不好
class ActivityWithoutUi : AppCompatActivity() {
    companion object {
        lateinit var grantedAction: ((Collection<Permission>) -> Unit)
        lateinit var deniedAction: ((Collection<Permission>) -> Unit)
        lateinit var permissions: HashSet<Permission>
        private const val myRequestCode = 10086
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == myRequestCode) {
            if (grantResults.isNotEmpty() &&
                grantResults.all {
                    it == PackageManager.PERMISSION_GRANTED
                }
            ) {
                grantedAction.invoke(Companion.permissions)
                Logger.d("调用grantedAction")
            } else {
                deniedAction.invoke(Companion.permissions)
                Logger.d("调用deniedAction")


                Companion.permissions
                    // 过滤已经获得的permission
                    .filter { permission ->
                        checkSelfPermission(permission.name) != PackageManager.PERMISSION_GRANTED
                    }.forEach { permission ->
                        if (permission.title != null &&
                            permission.message != null &&
                            shouldShowRequestPermissionRationale(permission.name)
                        ) {
                            showRationaleDialog(permission)
                        }
                    }
            }

            Logger.d("得到返回结果onRequestPermissionsResult with requestCode = $requestCode")
        }

        // 结束activity
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions(permissions.map { it.name }.toTypedArray(), myRequestCode)
        Logger.d("启动ActivityWithoutUi")
    }

    private fun showRationaleDialog(permission: Permission) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(permission.title)
            .setMessage(permission.message)
            .setPositiveButton("Ok") { _, _ ->
                run {
                    requestPermissions(arrayOf(permission.name), myRequestCode)
                }
            }

        builder.create().show()

        //系统线程会阻塞
        try {
            Looper.loop()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}