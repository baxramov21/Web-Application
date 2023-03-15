package com.example.webapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.webapplication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent = intent
        val registered = intent.getBooleanExtra("registered", false)
        val login = intent.getBooleanExtra("login", false)
        if (registered) {
            showToast(
                SweetAlertDialog.SUCCESS_TYPE,
                getString(R.string.succes),
                getString(R.string.succes_in_register)
            )
        } else if (login) {
            showToast(
                SweetAlertDialog.SUCCESS_TYPE,
                getString(R.string.succes),
                getString(R.string.succes_in_login)
            )
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content_container, ContentListFragment.newInstance())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sign_out) {
            signOut()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showToast(message_type: Int, title: String, message: String) {
        SweetAlertDialog(this, message_type)
            .setTitleText(title)
            .setContentText(message)
            .show()
    }

    private fun signOut() {
        binding.mainProgressBar.visibility = ProgressBar.VISIBLE
        FirebaseAuth.getInstance().signOut()
        openRegistrationPage()
    }

    private fun openRegistrationPage() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}