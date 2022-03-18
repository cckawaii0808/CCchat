package com.ccplay.ccchat.ui.Profife
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ccplay.ccchat.R
import com.ccplay.ccchat.databinding.FragmentProfileBinding
import com.ccplay.ccchat.databinding.FragmentSignupBinding


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
        val nickname = binding.tvSignupNickname.text.toString()
        val username = binding.tvSignupUsername.text.toString()
        val password = binding.tvSignupPassword.text.toString()
    }

}



