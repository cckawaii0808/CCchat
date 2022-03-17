package com.ccplay.ccchat.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ccplay.ccchat.databinding.FragmentHomeBinding
import com.ccplay.ccchat.databinding.RowChatroomBinding
import com.tom.atm.Lightyear
import okhttp3.*

class HomeFragment : Fragment() {
    val viewModel by viewModels<HomeViewModel>()//繼承
    private lateinit var adapter: ChatRoomAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    val chatRooms = mutableListOf<Lightyear>()
    lateinit var websocket: WebSocket//使用插件
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        /*val HomeViewModel=
            ViewModelProvider(this).get(HomeViewModel::class.java)*/
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*  binding.buttonSecond.setOnClickListener {
              findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
          }*/
/*//Web socket
val client = OkHttpClient.Builder()
    .readTimeout(3, TimeUnit.SECONDS)
    .build()
val request = Request.Builder()
    .url("wss://lott-dev.lottcube.asia/ws/chat/chat:app_test?nickname=cc")
    .build()
  websocket = client.newWebSocket(request, object : bSocketListener() {
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
    }
})*/
        binding.recycler.setHasFixedSize(true)
        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = ChatRoomAdapter()
        binding.recycler.adapter = adapter
        viewModel.chatRooms.observe(viewLifecycleOwner) { rooms ->
            adapter.submitRooms(rooms)
        }
        viewModel.getAllRooms()
    }

    inner class ChatRoomAdapter : RecyclerView.Adapter<BindingViewHolder>() {
        //繼承
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
            val binding = RowChatroomBinding.inflate(layoutInflater, parent, false)
            return BindingViewHolder(binding)
        }

        override fun getItemCount(): Int {//取得房間數量
            return chatRooms.size
        }

        override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
            val lightYear = chatRooms[position]
            holder.host.setText(lightYear.stream_title)
            holder.title.setText(lightYear.nickname)
            Glide.with(this@HomeFragment).load(lightYear.head_photo).into(holder.binding.headShot)
        }

        fun submitRooms(rooms: List<Lightyear>) {
            chatRooms.clear()
            chatRooms.addAll(rooms)
            notifyDataSetChanged()
        }
    }
    /*   holder.itemView.setOnClickListener {
           chatRoomClicked(lightYear)
   }
   private fun chatRoomClicked(lightYear: Lightyear) {
   }
   */

    inner class BindingViewHolder(val binding: RowChatroomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val host = binding.tvChatroomHostTitle
        val title = binding.tvChatroomTitle
        val headshot = binding.headShot
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
