package com.example.marjella_security_idea_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController

class Forgot_password : Fragment() {

    private lateinit var redirectToLogin : LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)

        redirectToLogin = view.findViewById(R.id.redirect_to_login)

        redirectToLogin.setOnClickListener { _ ->
            findNavController().navigate(R.id.login)
        }

        return view
    }

}