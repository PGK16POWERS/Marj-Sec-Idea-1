package com.example.marjella_security_idea_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.marjella_security_idea_1.models.login
import com.example.marjella_security_idea_1.network.retrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

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

    private fun loginFunc(email: String, password: String) {

        val loginBody = login(
            email = email,
            password = password
        )

        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    retrofitClient.apiService.login(loginBody)
                }

                val responseBody = response.body()

                if ( responseBody != null && responseBody.status == 200 ) {
                    Toast.makeText(requireContext(),"Its a fucking Glad bag",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(),"",Toast.LENGTH_SHORT).show()
                }

            } catch (e: IOException) {
                Toast.makeText(requireContext(),"Network Connection error", Toast.LENGTH_SHORT).show()
            } catch (e: HttpException) {
                Toast.makeText(requireContext(),"Server Errors",Toast.LENGTH_SHORT).show()
            }
        }
    }

}