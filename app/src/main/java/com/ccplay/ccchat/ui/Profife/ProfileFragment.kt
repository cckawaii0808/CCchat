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
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ccplay.ccchat.R
import com.ccplay.ccchat.databinding.FragmentProfileBinding
import com.ccplay.ccchat.ui.home.HomeFragment

class ProfileFragment : Fragment() {
    var remember = false
    val viewModel by viewModels<ProfileViewModel>()
    val loginViewModel by viewModels<LoginViewModel>()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        check()

        val root: View = binding.root
        return root
    }

    private fun check() {
        val pref = requireContext().getSharedPreferences("chat", Context.MODE_PRIVATE)
        if (pref.getBoolean("login_state", true)) {
            val nickname=pref.getString("NICKNAME","")
            pref.edit().putString("NICKNAME",nickname).apply()
            Log.d(TAG,"得到的暱稱為$nickname")
            findNavController().navigate(R.id.memberInfoFragment)
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Text changed
        val pref = requireContext().getSharedPreferences("chat", Context.MODE_PRIVATE)
        val checked = pref.getBoolean("rem_username", false)

        binding.ckRemember.isChecked = checked
        binding.ckRemember.setOnCheckedChangeListener { compoundButton, checked ->
            remember = checked
            pref.edit().putBoolean("rem_username", remember).apply()
            if (!checked) {
                pref.edit().putString("USERNAME", "").apply()
            }
        }
        val UserName = pref.getString("USERNAME", "")
        val UserPass = pref.getString("PASSWORD", "")
        val nickname = pref.getString("NICKNAME", "")


        if (UserName != "") {
            binding.tvLoginName.setText(UserName)
        }
        binding.bLogin.setOnClickListener {
            //Login stuff
            val username = binding.tvLoginName.text.toString()
            val password = binding.tvLoginPass.text.toString()
            //  viewModel.getUsers().observe(viewLifecycleOwner) { user ->
            //}

            if (loginViewModel.loginState(UserName, username, UserPass, password)) {

                Log.d(TAG, "$UserName,$UserPass")
                Log.d(TAG,"登入暱稱$nickname")
                pref.edit().putBoolean("login_state", true)
                    .putString("NICKNAME", nickname).apply()


                if (remember) {
                    pref.edit()
                        .putString("USERNAME", username)
                        .putInt("LEVEL", 3)
                        .apply() //.commit()

                }
                findNavController().navigate(R.id.navigation_home)
            } else {

                // error
//                AlertDialog.Builder(requireContext())
//                    .setTitle("Login")
//                    .setMessage("Login Failed")
//                    .setPositiveButton("OK", null)
//                    .show()
                Toast.makeText(requireContext(), "帳號或密碼錯誤", Toast.LENGTH_LONG).show()

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

