package com.educationalapp.features.login.presentation

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.educationalapp.R
import com.educationalapp.core.presentation.BaseFragment
import com.educationalapp.core.presentation.UiState
import com.educationalapp.databinding.FragmentLoginBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult

class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding

    private val providers = arrayListOf(
        AuthUI.IdpConfig.GoogleBuilder().build(),
    )

    private val signInIntent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(providers)
        .build()

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        onSignInResult(res)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    override fun initUI() {
        binding.loginWithGoogleButton.setOnClickListener {
            signInLauncher.launch(signInIntent)
        }
    }

    override fun initObservers() {
        // no need to implement right now
    }

    private fun onSignInResult(
        result: FirebaseAuthUIAuthenticationResult,
    ) {
        val response = result.idpResponse
        if (result.resultCode == Activity.RESULT_OK) {
            findNavController().navigate(R.id.navigateToHomeFragment)
        } else {
            showError(UiState.Error(response?.error?.cause))
        }
    }
}