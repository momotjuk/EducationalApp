package com.educationalapp.features.profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.educationalapp.R
import com.educationalapp.core.presentation.BaseFragment
import com.educationalapp.core.presentation.UiState
import com.educationalapp.databinding.FragmentProfileBinding
import com.educationalapp.features.profile.logic.Profile
import com.educationalapp.features.profile.logic.User
import com.firebase.ui.auth.AuthUI
import org.koin.core.component.KoinComponent

class ProfileFragment : BaseFragment(), KoinComponent {

    private lateinit var binding: FragmentProfileBinding
    private val userId: Long = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initUI() {
        initToolbar()
        initPreferences()
        initLogout()
    }

    private fun initToolbar() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.saveButton.setOnClickListener {
            saveProfileSettings()
        }
    }

    override fun initObservers() {

    }

    private fun initPreferences(){

    }

    private fun initLogout() {
        binding.logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun saveProfileSettings() {
        Profile(
            userId,
            binding.pref1Button.isChecked,
            binding.pref2Button.isChecked,
            binding.pref3Button.isChecked,
        )
        User(
            userId,
            "",
            "",
            ""
        )
    }

    private fun logout() {
        AuthUI.getInstance()
            .signOut(requireContext())
            .addOnCompleteListener {
                findNavController().navigate(R.id.navigateToLoginFragment)

            }
            .addOnFailureListener {
                showError(UiState.Error(it))
            }
    }
}