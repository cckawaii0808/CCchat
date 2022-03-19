package com.ccplay.ccchat.ui.Profife
import androidx.lifecycle.ViewModel
class SignUpViewModel  : ViewModel() {
    fun accountrule (UserId : String) : Boolean{
        return (UserId.length in 20 downTo 4)
    }
    fun passwordrule (UserPass : String) : Boolean{
        return (UserPass.length in 20 downTo 4)
    }
}
