package com.example.webapplication.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.webapplication.R
import com.example.webapplication.databinding.FragmentLoginBinding
import com.example.webapplication.presentation.activity.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding ?: throw java.lang.RuntimeException("FragmentLoginBinding is null")

//    private lateinit var navHostFragment: NavHostFragment
//    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = FirebaseAuth.getInstance()
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        initNavigation()

        binding.loginProgressBar.visibility = ProgressBar.INVISIBLE
        binding.buttonLogin.setOnClickListener {
            with(binding) {
                val email = etLoginEmail.text.toString()
                val password = etLoginPassword.text.toString()

                if (email.isNotEmpty() && password.isNotEmpty()) {
                    tilEmail.error = null
                    tilPassword.error = null
                    loginProgressBar.visibility = ProgressBar.VISIBLE
                    logUserIn(email, password)
                } else {
                    tilEmail.error = "Password or email is empty"
                    tilPassword.error = "Password or email is empty"
                }
            }
        }

        binding.tvSignUp.setOnClickListener {
            openRegistrationPage()
        }
    }

    private fun logUserIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    openMainScreen()
                } else {
                    // If sign in fails, display a message to the user.
                    binding.loginProgressBar.visibility = ProgressBar.INVISIBLE
                    showToast(
                        SweetAlertDialog.ERROR_TYPE,
                        getString(R.string.error),
                        getString(R.string.auth_failed)
                    )
                }
            }
    }

    private fun showToast(message_type: Int, title: String, message: String) {
        SweetAlertDialog(context, message_type)
            .setTitleText(title)
            .setContentText(message)
            .show()
    }

    private fun openRegistrationPage() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.login_container, RegisterFragment.newInstance())
            .commit()
    }

    private fun openMainScreen() {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra("login", true)
        startActivity(intent)
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
        fun newInstance() = LoginFragment()
        private const val TAG = "LOGIN_FRAGMENT"
    }
}