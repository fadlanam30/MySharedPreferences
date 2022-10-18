package com.inditech.mysharedpreferences

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.inditech.mysharedpreferences.databinding.ActivityLoginBinding
import com.inditech.mysharedpreferences.helper.Constant
import com.inditech.mysharedpreferences.helper.PrefHelper

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefHelper = PrefHelper(this)

        val isLogin = prefHelper.getBoolean(Constant.PREF_IS_LOGIN)

        if (isLogin) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val password = binding.edPassword.text.toString()

            when {
                email.isEmpty() -> {
                    binding.txtLayEmail.error = "Mohon Masukkan Email"
                }
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    binding.txtLayEmail.error = "Mohon Masukkan Email dengan Benar"
                }
                password.isEmpty() -> {
                    binding.txtLayPassword.error = "Mohon Masukkan Password"
                }
                else -> {
                    binding.txtLayEmail.error = null
                    binding.txtLayPassword.error = null
                    Toast.makeText(this, "Email : $email Password : $password", Toast.LENGTH_SHORT)
                        .show()

                    val intentToMain = Intent(this, MainActivity::class.java)

                    prefHelper.put(Constant.PREF_IS_LOGIN, true)
                    prefHelper.put(Constant.PREF_EMAIL, email)
                    prefHelper.put(Constant.PREF_PASSWORD, password)

//                    intentToMain.putExtra(EXTRA_EMAIL_USER, email)
//                    intentToMain.putExtra(EXTRA_PASSWORD_USER, password)

                    startActivity(intentToMain)
                    finish()
                }
            }

        }

    }

    companion object {
        const val EXTRA_EMAIL_USER = "EXTRA_EMAIL_USER"
        const val EXTRA_PASSWORD_USER = "EXTRA_PASSWORD_USER"
    }

}