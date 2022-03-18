package com.ccplay.ccchat.ui.Profife

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.ccplay.ccchat.databinding.FragmentSignupBinding
import com.ccplay.ccchat.ui.Profife.data.Getmember
import java.lang.reflect.Member

class Signup_Fragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = requireContext().getSharedPreferences("userdata", Context.MODE_PRIVATE)
        binding.bSend.setOnClickListener {
            val nickname = binding.tvSignupNickname.text.toString()
            val username = binding.tvSignupUsername.text.toString()
            val password = binding.tvSignupPassword.text.toString()
            pref.edit().putString("$nickname", "$username$password").apply()
            Log.d(TAG, "註冊資料如下 暱稱$nickname 帳號$username 密碼$password")
            val db = Room.databaseBuilder(
                requireContext(),
                Getmember::class.java, "member_name"
            ).build()
            val member =
                com.ccplay.ccchat.ui.Profife.data.Member("$nickname", "$username", "$password")
            Thread {
                db.memberDao().insert(member)
            }.start()
            Log.d(TAG, "資料庫$db")
        }


    }

}



