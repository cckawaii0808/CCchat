package com.ccplay.ccchat.ui.Profife

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ccplay.ccchat.MainActivity
import com.ccplay.ccchat.R
import com.ccplay.ccchat.databinding.FragmentProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class ProfileFragment : Fragment() {

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
        regist()

        return root
    }

    fun regist() {

        binding.bRegis.setOnClickListener(Navigation.createNavigateOnClickListener
            (R.id.action_navigation_notifications_to_signup_Fragment))//指定跳轉頁面
        Log.d(TAG,"點擊註冊")

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}