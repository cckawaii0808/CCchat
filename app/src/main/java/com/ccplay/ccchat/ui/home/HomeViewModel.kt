package com.ccplay.ccchat.ui.home

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.tom.atm.ChatRooms
import com.tom.atm.Lightyear
import java.net.URL
import kotlin.concurrent.thread

class HomeViewModel : ViewModel() {
   private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    }

fun getrooms(){
    val rooms = mutableListOf<Lightyear>()

    thread {
        val json = URL("https://api.jsonserve.com/qHsaqy").readText()
        val chatRooms = Gson().fromJson(json, ChatRooms::class.java)
        Log.d(ContentValues.TAG, "rooms: ${chatRooms.result.lightyear_list.size}");
        //fill list with new coming data
        rooms.clear()
        rooms.addAll(chatRooms.result.lightyear_list)
        //List<LightYear>
        /*activity?.runOnUiThread {
            adapter.notifyDataSetChanged()
        }*/
    }
}
