package com.example.marjella_security_idea_1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.marjella_security_idea_1.models.Login
import com.example.marjella_security_idea_1.network.retrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class Login : Fragment() {

    private lateinit var createAccountRedirect : LinearLayout
    private lateinit var forgotPassword : LinearLayout
    private lateinit var emailInputField : EditText
    private lateinit var passwordInputField : EditText
    private lateinit var loginButton : Button

    var inputedEmail = ""
    var inputedPassword = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        forgotPassword = view.findViewById(R.id.forgot_password_redirect)
        createAccountRedirect = view.findViewById(R.id.redirect_to_sign_up)
        emailInputField = view.findViewById(R.id.email_input_field)
        passwordInputField = view.findViewById(R.id.password_input_field)
        loginButton = view.findViewById(R.id.login_button)

        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()

        fragmentTransaction.setCustomAnimations (
            R.anim.fragment_enter,
            R.anim.fragment_exit
        )

        createAccountRedirect.setOnClickListener { _ ->
            fragmentTransaction.replace(R.id.nav_host_container, SIgn_up())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        forgotPassword.setOnClickListener { _ ->
            findNavController().navigate(R.id.forgot_password)
        }

        emailInputField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                inputedEmail = s.toString()
            }

        })

        passwordInputField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                inputedPassword = s.toString()
            }

        })

        loginButton.setOnClickListener { _ ->

            if (inputedEmail.isEmpty() || inputedPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Form is incomplete", Toast.LENGTH_SHORT).show()
            } else {
                loginFunc(inputedEmail, inputedPassword)
            }

        }

        return view
    }

    private fun loginFunc(email: String, password: String) {

        val loginBody = Login(
            email = email,
            password = password
        )

        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    retrofitClient.apiService.login(loginBody)
                }

                val responseBody = response.body()

                if (responseBody?.status == 200) {
                    val message = response.body()
                    Toast.makeText(requireContext(), responseBody.message, Toast.LENGTH_SHORT).show()

                    findNavController().navigate(R.id.verify_login)
                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }

            } catch (e: IOException) {
                Toast.makeText(requireContext(), "Network Connection error", Toast.LENGTH_SHORT).show()
            } catch (e: HttpException) {
                Toast.makeText(requireContext(), "Server error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun checkFormValidity() {

    }

}