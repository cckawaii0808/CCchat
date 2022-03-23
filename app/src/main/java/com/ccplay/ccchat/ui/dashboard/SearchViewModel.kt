package com.ccplay.ccchat.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.tom.atm.ChatRooms
import com.tom.atm.Lightyear
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class SearchViewModel : ViewModel() {
    val chatRooms = MutableLiveData<List<Lightyear>>()
    fun getAllRooms() {
        viewModelScope.launch(Dispatchers.IO) {
            val json = URL("https://api.jsonserve.com/qHsaqy").readText()
            val response = Gson().fromJson(json, ChatRooms::class.java)
            chatRooms.postValue(response.result.lightyear_list)
        }
    }
}