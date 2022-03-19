package com.ccplay.ccchat.ui.home

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ccplay.ccchat.MainActivity
import com.ccplay.ccchat.R
import com.ccplay.ccchat.databinding.FragmentChatRoomBinding
import com.ccplay.ccchat.databinding.FragmentProfileBinding


class ChatRoomFragment : Fragment() {
    private var _binding: FragmentChatRoomBinding? = null
    private val binding get() = _binding!!
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