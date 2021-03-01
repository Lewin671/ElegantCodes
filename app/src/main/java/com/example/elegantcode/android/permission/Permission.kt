package com.example.elegantcode.android.permission

data class Permission(val name: String, val title: String? = null, val message: String? = null) {

    // 常用危险权限
    companion object {
        const val READ_CALENDAR = "android.permission.READ_CALENDAR"
        const val WRITE_CALENDAR = "android.permission.WRITE_CALENDAR"

        const val CAMERA = "android.permission.CAMERA"

        const val READ_CONTACTS = "android.permission.READ_CONTACTS"
        const val WRITE_CONTACTS = "android.permission.WRITE_CONTACTS"
        const val GET_ACCOUNTS = "android.permission.GET_ACCOUNTS"

        const val ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION"
        const val ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION"
        const val ACCESS_BACKGROUND_LOCATION = "android.permission.ACCESS_BACKGROUND_LOCATION"

        const val RECORD_AUDIO = "android.permission.RECORD_AUDIO"

        const val READ_PHONE_STATE = "android.permission.READ_PHONE_STATE"
        const val CALL_PHONE = "android.permission.CALL_PHONE"
        const val USE_SIP = "android.permission.USE_SIP"
        const val READ_PHONE_NUMBERS = "android.permission.READ_PHONE_NUMBERS"
        const val ANSWER_PHONE_CALLS = "android.permission.ANSWER_PHONE_CALLS"
        const val ADD_VOICEMAIL = "com.android.voicemail.permission.ADD_VOICEMAIL"

        const val READ_CALL_LOG = "android.permission.READ_CALL_LOG"
        const val WRITE_CALL_LOG = "android.permission.WRITE_CALL_LOG"
        const val PROCESS_OUTGOING_CALLS = "android.permission.PROCESS_OUTGOING_CALLS"

        const val BODY_SENSORS = "android.permission.BODY_SENSORS"
        const val ACTIVITY_RECOGNITION = "android.permission.ACTIVITY_RECOGNITION"

        const val SEND_SMS = "android.permission.SEND_SMS"
        const val RECEIVE_SMS = "android.permission.RECEIVE_SMS"
        const val READ_SMS = "android.permission.READ_SMS"
        const val RECEIVE_WAP_PUSH = "android.permission.RECEIVE_WAP_PUSH"
        const val RECEIVE_MMS = "android.permission.RECEIVE_MMS"

        const val READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE"
        const val WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE"

    }
}