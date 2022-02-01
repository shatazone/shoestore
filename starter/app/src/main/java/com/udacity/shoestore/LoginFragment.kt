package com.udacity.shoestore

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentLoginBinding>(layoutInflater, R.layout.fragment_login, container, false)

        binding.signinButton.setOnClickListener {
            navigateToWelcomeScreen(it)
        }

        binding.signupButton.setOnClickListener {
            navigateToWelcomeScreen(it)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun navigateToWelcomeScreen(it: View) {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())

        val imm: InputMethodManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}