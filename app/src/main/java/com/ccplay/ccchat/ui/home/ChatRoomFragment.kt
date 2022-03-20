package com.ccplay.ccchat.ui.home

import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ccplay.ccchat.MainActivity
import com.ccplay.ccchat.R
import com.ccplay.ccchat.databinding.FragmentChatRoomBinding
import com.ccplay.ccchat.databinding.FragmentProfileBinding
import com.ccplay.ccchat.ui.Profife.LoginViewModel
import okhttp3.WebSocket


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

//            val item = LayoutInflater.from(this).inflate(R.layout.heart, null)
//            androidx.appcompat.app.AlertDialog.Builder(this)
//                .setView(item)

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
