package com.ccplay.ccchat.ui.Profife

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    fun loginState(
        account: String?,//帳號
        username: String?,//輸入的帳號
        password: String?,//密碼
        userpass: String?//輸入的密碼
    ): Boolean {
        return if (account == username && password == userpass) {
            Log.d(TAG, "已登入")
            true
        } else false
    }
}










