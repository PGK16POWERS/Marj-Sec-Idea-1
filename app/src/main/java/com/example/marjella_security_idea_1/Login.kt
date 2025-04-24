package com.example.marjella_security_idea_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController

class Login : Fragment() {

    private lateinit var createAccountRedirect : LinearLayout
    private lateinit var forgotPassword : LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        forgotPassword = view.findViewById(R.id.forgot_password_redirect)
        createAccountRedirect = view.findViewById(R.id.redirect_to_sign_up);

        createAccountRedirect.setOnClickListener { _ ->
            findNavController().navigate(R.id.SIgn_up)
        }

        forgotPassword.setOnClickListener { _ ->
            findNavController().navigate(R.id.forgot_password)
        }

        return view
    }

}