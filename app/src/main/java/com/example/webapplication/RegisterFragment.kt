package com.example.webapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.webapplication.databinding.FragmentRegistrationBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding
        get() = _binding ?: throw java.lang.RuntimeException("FragmentRegistrationBinding is null")

//    private lateinit var navHostFragment: NavHostFragment
//    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = FirebaseAuth.getInstance()
        _binding = FragmentRegistrationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        initNavigation()

        binding.progressBar.visibility = ProgressBar.INVISIBLE
        binding.buttonSignUp.setOnClickListener {
            with(binding) {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()

                if (email.isNotEmpty() && password.isNotEmpty()) {
                    tilEmail.error = null
                    tilPassword.error = null
                    progressBar.visibility = ProgressBar.VISIBLE
                    registrateUser(email, password)
                } else {
                    tilEmail.error = "Password or email is empty"
                    tilPassword.error = "Password or email is empty"
                }
            }
        }

        binding.tvLogin.setOnClickListener {
            openLoginPage()
        }
    }

    private fun registrateUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                val user = auth.currentUser
                openMainScreen()
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(
                    requireContext(), "Authentication failed.",
                    Toast.LENGTH_SHORT
                ).show()
                openMainScreen()
            }
        }
    }

    private fun openMainScreen() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }

    private fun openLoginPage() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.login_container, LoginFragment.newInstance())
            .commit()
    }


//    private fun initNavigation() {
//        navHostFragment = requireActivity()
//            .supportFragmentManager
//            .findFragmentById(R.id.login_container) as NavHostFragment
//        navController = navHostFragment.navController
//    }

    override fun onDestroyView() {
        if (_binding != null) {
            _binding = null
        }
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = RegisterFragment()
        private const val TAG = "REGISTRATION_FRAGMENT"
    }
}