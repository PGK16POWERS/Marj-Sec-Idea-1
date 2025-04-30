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
import com.example.marjella_security_idea_1.models.createAccount
import com.example.marjella_security_idea_1.network.retrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class SIgn_up : Fragment() {

    private lateinit var loginRedirect : LinearLayout
    private lateinit var emailInput : EditText
    private lateinit var passwordInput : EditText
    private lateinit var confirmPasswordInput : EditText
    private lateinit var createAccountButton : Button

    var inputtedEmail = ""
    var inputtedpassword = ""
    var inputtedConfirmPassword = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_s_ign_up, container, false)

        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()

        fragmentTransaction.setCustomAnimations(
            R.anim.fragment_exit,
            R.anim.fragment_enter
        )

        loginRedirect = view.findViewById(R.id.redirect_to_login)
        emailInput = view.findViewById(R.id.email_input)
        passwordInput = view.findViewById(R.id.password_input)
        confirmPasswordInput = view.findViewById(R.id.confirm_password_input)
        createAccountButton = view.findViewById(R.id.create_account_button)

        loginRedirect.setOnClickListener { _ ->
            fragmentTransaction.replace(R.id.nav_host_container, Login())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        emailInput.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                inputtedEmail = s.toString()
            }
        })

        passwordInput.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                inputtedpassword = s.toString()
            }
        })

        confirmPasswordInput.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                inputtedConfirmPassword = s.toString()
            }
        })

        createAccountButton.setOnClickListener { _ ->
            if (inputtedEmail.isNotEmpty() && inputtedpassword.isNotEmpty() && inputtedConfirmPassword.isNotEmpty()) {
                createAccountFunc(inputtedEmail, inputtedpassword)
            } else {
                Toast.makeText(requireContext(), "Complete form", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun createAccountFunc(email: String, password: String) {

        val createAccountBody = createAccount(
            email = email,
            password = password
        )

        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    retrofitClient.apiService.createAccount(createAccountBody)
                }

                val responseBody = response.body()

                if (responseBody != null && responseBody.status == 200) {
                    Toast.makeText(requireContext(), "Authenticate your account" ,Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            } catch (e: IOException) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            } catch (e: HttpException) {
                Toast.makeText(requireContext(), "Internal server error", Toast.LENGTH_SHORT).show()
            }
        }
    }

}