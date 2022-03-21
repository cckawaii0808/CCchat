package com.ccplay.ccchat.ui.home

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.ccplay.ccchat.MainActivity
import com.ccplay.ccchat.R
import com.ccplay.ccchat.databinding.FragmentChatRoomBinding
import com.ccplay.ccchat.databinding.FragmentProfileBinding
import com.ccplay.ccchat.ui.Profife.LoginViewModel
import com.google.gson.Gson
import com.tom.atm.MessageSend
import okhttp3.*
import okio.ByteString
import java.util.concurrent.TimeUnit


class ChatRoomFragment : Fragment() {
    val loginViewModel by viewModels<LoginViewModel>()
    private var _binding: FragmentChatRoomBinding? = null
    private val binding get() = _binding!!
    lateinit var websocket: WebSocket//使用插件
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatRoomBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val pref = requireContext().getSharedPreferences("chat", Context.MODE_PRIVATE)
        var chatmember=""
        if (pref.getBoolean("login_state", true)) {

         chatmember= pref.getString("NICKNAME","").toString()
            Log.d(TAG, "發話者$chatmember 已經登入")
        }else{
             chatmember="訪客"
            Log.d(TAG, "發話者尚未登入")
        }







        val client = OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.SECONDS)
            .build()
        val request = okhttp3.Request.Builder()
            .url("wss://lott-dev.lottcube.asia/ws/chat/chat:app_test?nickname=$chatmember")
            .build()
        websocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
                Log.d(TAG, ": onClosed");
            }
            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosing(webSocket, code, reason)
                Log.d(TAG, ": onClosing");
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                Log.d(TAG, ": onFailure");
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                Log.d(TAG, ": onMessage $text");
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                super.onMessage(webSocket, bytes)
                Log.d(TAG, ": onMessage ${bytes.hex()}");
            }

            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                Log.d(TAG, ": onOpen");
//                webSocket.send("Hello, I am Hank")
            }})
        binding.bChatSend.setOnClickListener {
            val message = binding.tvchatmessage.text.toString()
            //val json = "{\"action\": \"N\", \"content\": \"$message\" }"
//            websocket.send(json)
            websocket.send(Gson().toJson(MessageSend("N", message)))
            binding.tvchatmessage.setText("")
        }
        binding.bExitroom.setOnClickListener {
            val item = LayoutInflater.from(requireContext()).inflate(R.layout.breakheart, null)
           androidx.appcompat.app.AlertDialog.Builder(requireContext())
            AlertDialog.Builder(requireContext()) .setView(item)
                .setTitle("確定要離開嗎")
                .setMessage("不再想想嗎")
                .setPositiveButton("狠心離開") { d, w ->
                    findNavController().navigate(R.id.navigation_home)
                }
                .setNegativeButton("留下") { d, w ->
                    null
                }.show()

        }
        super.onViewCreated(view, savedInstanceState)
        var videoview = binding.videoView
        var videoPath = "android.resource://" + requireContext().packageName + "/raw/video"
        var uri = Uri.parse(videoPath)
        videoview.setVideoURI(uri)
        videoview.setOnPreparedListener {
            videoview.start()
        }

    }

}
