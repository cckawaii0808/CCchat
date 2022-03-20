package com.ccplay.ccchat.ui.Profife

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ccplay.ccchat.R
import com.ccplay.ccchat.databinding.FragmentMemberInfoBinding


class MemberInfoFragment : Fragment() {
    lateinit var binding: FragmentMemberInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMemberInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = requireContext().getSharedPreferences("chat", Context.MODE_PRIVATE)
        binding.tvInfoNick.setText("暱稱:${pref.getString("NICKNAME", "")}")
        binding.tvInfoUser.setText("帳號:${pref.getString("USERNAME", "")}")
        binding.bLogup.setOnClickListener {
            pref.edit()
                .putBoolean("login_state", false)
                .apply()
            findNavController().navigate(R.id.navigation_profile)
        }
    }

}