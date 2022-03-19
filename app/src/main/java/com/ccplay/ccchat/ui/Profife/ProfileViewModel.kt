package com.ccplay.ccchat.ui.Profife

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ccplay.ccchat.ui.Profife.data.Member

class ProfileViewModel : ViewModel() {

     val users: MutableLiveData<List<Member>> by lazy {
        MutableLiveData<List<Member>>().also {
            loadUsers()
        }
    }

    fun getUsers(): LiveData<List<Member>> {
        return users
    }

    private fun loadUsers() {
        Log.d(TAG,"讀取會員")
    }
}



