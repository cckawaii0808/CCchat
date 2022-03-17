package com.ccplay.ccchat.ui.Profife

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ccplay.ccchat.R
import com.ccplay.ccchat.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    var remember = false
    private var login_state = 0
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Text changed


        val pref = requireContext().getSharedPreferences("atm", Context.MODE_PRIVATE)
        val checked = pref.getBoolean("rem_username", false)
        binding.ckRemember.isChecked = checked
        binding.ckRemember.setOnCheckedChangeListener { compoundButton, checked ->
            remember = checked
            pref.edit().putBoolean("rem_username", remember).apply()
            if (!checked) {
                pref.edit().putString("USER", "").apply()
            }
        }
        val prefUser = pref.getString("USER", "")
        if (prefUser != "") {
            binding.tvLoginName.setText(prefUser)
        }
        binding.bLogin.setOnClickListener {
            //Login stuff
            val username = binding.tvLoginName.text.toString()
            val password = binding.tvLoginPass.text.toString()
            if (username == "cc" && password == "1234") {
                //save username to preferences
                //val pref = requireContext().getSharedPreferences("atm", Context.MODE_PRIVATE)
                if (remember) {
                    pref.edit()
                        .putString("USER", username)
                        .putInt("LEVEL", 3)
                        .apply() //.commit()
                }
                findNavController().navigate(R.id.navigation_home)
            } else {
                // error
                AlertDialog.Builder(requireContext())
                    .setTitle("Login")
                    .setMessage("Login Failed")
                    .setPositiveButton("OK", null)
                    .show()
            }

        }

        binding.bRegis.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_notifications_to_signup_Fragment)
            Log.d(TAG, "點擊註冊")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

/*
    private fun login() {
        val pref = requireContext().getSharedPreferences("atm", Context.MODE_PRIVATE)
        val checked = pref.getBoolean("rem_username", false)
        binding.bLogin.setOnClickListener {
            val username = binding.tvLoginName.text.toString()
            val password = binding.tvLoginPass.text.toString()
            if (username == "cc" && password == "1234") {
                Log.d(TAG, "登入成功")
                findNavController().navigate(R.id.action_signup_Fragment_to_navigation_home)

            } else
                Toast.makeText(context, "        帳號或密碼錯誤        ",
                    Toast.LENGTH_SHORT).show()
        }
    }

fun regist() {
    binding.bRegis.setOnClickListener(Navigation.createNavigateOnClickListener
        (R.id.action_navigation_notifications_to_signup_Fragment))//指定跳轉頁面
    Log.d(TAG, "點擊註冊")

}
*/