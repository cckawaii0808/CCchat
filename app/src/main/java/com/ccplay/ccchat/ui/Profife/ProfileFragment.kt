package com.ccplay.ccchat.ui.Profife

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.ccplay.ccchat.R
import com.ccplay.ccchat.databinding.FragmentProfileBinding
import com.ccplay.ccchat.ui.home.HomeFragment

class ProfileFragment : Fragment() {
    var remember = false
    private var login_state = 0
    val viewModel by viewModels<ProfileViewModel>()
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
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Text changed
        val pref = requireContext().getSharedPreferences("userdata", Context.MODE_PRIVATE)
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
        val prefUsername = pref.getString("", "")
        if (prefUser != "") {
            binding.tvLoginName.setText(prefUser)
        }
        binding.bLogin.setOnClickListener {
            //Login stuff
            val username = binding.tvLoginName.text.toString()
            val password = binding.tvLoginPass.text.toString()


            viewModel.getUsers().observe(viewLifecycleOwner) { user ->

            }





            if ("$username$password"=="$prefUsername") {
                //save username to preferences
                //   val pref = requireContext().getSharedPreferences("atm", Context.MODE_PRIVATE)
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

