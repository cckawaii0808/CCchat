package com.ccplay.ccchat.ui.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

import com.tom.atm.MyMessage

class ChatRoomViewModel : ViewModel() {
    val chats = MutableLiveData<List<MyMessage>>()//帶去ＦＲＡＧ
    val getchat = mutableListOf<MyMessage>()//打包
    fun getMessage(json: String) {

        val response = Gson().fromJson(json, MyMessage::class.java)
        getchat.add(response)
        chats.postValue(getchat)
        Log.d(TAG, "我是getchat $getchat 哈")

    }
}

