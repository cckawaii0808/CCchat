package com.ccplay.ccchat.ui.Profife

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.ccplay.ccchat.R
import com.ccplay.ccchat.databinding.FragmentSignupBinding
import com.ccplay.ccchat.ui.Profife.data.Getmember
import com.google.android.material.snackbar.Snackbar
import java.lang.reflect.Member

class Signup_Fragment : Fragment() {
    val signUpViewModel by viewModels<SignUpViewModel>()
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
        binding.bBacktologin.setOnClickListener {
            findNavController().navigate(R.id.navigation_profile)

        }
        binding.bSend.setOnClickListener {
            val nickname = binding.tvSignupNickname.text.toString()
            val username = binding.tvSignupUsername.text.toString()
            val password = binding.tvSignupPassword.text.toString()

            if (signUpViewModel.accountrule(username) && signUpViewModel.passwordrule(password)) {
                val pref = requireContext().getSharedPreferences("chat", Context.MODE_PRIVATE)
                pref.edit()
                    .putString("NICKNAME", nickname)
                    .putString("USERNAME", username)
                    .putString("PASSWORD", password)
                    .putBoolean("login_state", true)
                    .apply()
                binding.tvSignupNickname.setText("")
                binding.tvSignupUsername.setText("")
                binding.tvSignupPassword.setText("")
                Log.d(TAG, " 註冊資料如下 暱稱$nickname 帳號$username 密碼$password")
                Toast.makeText(requireContext(), "$nickname ，註冊成功", Toast.LENGTH_LONG).show()
            } else {
                Log.d(TAG, " 請重新輸入")
                pref.edit()
                    .putString("NICKNAME", "訪客")
                    .putString("USERNAME", "")
                    .putString("PASSWORD", "")
                    .putBoolean("login_state", false)
                    .apply()
                Toast.makeText(requireContext(), "請輸入4-20位數字或字母", Toast.LENGTH_LONG).show()
            }


/*   val db = Room.databaseBuilder(
      requireContext(),
      Getmember::class.java, "member_name"
  ).build()
  val member
  com.ccplay.ccchat.ui.Prfe.data.Member("$nickname", "$username", "$password")
            Thread {
                db.memberDao().insert(member)
            }.start()
    Log.d(TAG, "資料庫$db")
    */

        }


    }

}



