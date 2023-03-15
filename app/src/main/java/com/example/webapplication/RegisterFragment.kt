package com.example.webapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
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
                    tilEmail.error = getString(R.string.password_or_email_is_empty)
                    tilPassword.error = getString(R.string.password_or_email_is_empty)
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
                openMainScreen()
            } else {
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

    private fun openMainScreen() {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra("registered", true)
        startActivity(intent)
    }

    private fun openLoginPage() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.login_container, LoginFragment.newInstance())
            .commit()
        //    findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
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