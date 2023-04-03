package com.educationalapp.features.splash.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.educationalapp.R
import com.educationalapp.core.presentation.BaseFragment
import com.educationalapp.databinding.FragmentSplashScreenBinding
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : BaseFragment() {

    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initUI() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            findNavController().navigate(R.id.navigateToHomeFragment)
        } else {
            findNavController().navigate(R.id.navigateToLoginFragment)
        }
    }

    override fun initObservers() {
        // no need to implement right now
    }
}