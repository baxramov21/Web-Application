package com.example.webapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        supportFragmentManager.beginTransaction()
            .replace(R.id.login_container,RegistrationFragment.newInstance())
            .commit()

    }
}