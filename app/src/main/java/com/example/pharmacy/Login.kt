package com.example.pharmacy

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val loginButton: Button = findViewById(R.id.loginbtn)
        val signHereButton: Button = findViewById(R.id.registerRedirectbtn)
        val usernameTxt: TextView = findViewById(R.id.username)
        val passwordTxt: TextView = findViewById(R.id.password)

        val googleBtn: ImageButton = findViewById(R.id.googleRedirectBtn)
        val facebookBtn: ImageButton = findViewById(R.id.facebookRedirectBtn)
        val twitterBtn: ImageButton = findViewById(R.id.twitterRedirectBtn)
        loginButton.setOnClickListener() {
            if (TextUtils.isEmpty(usernameTxt.text) || TextUtils.isEmpty(passwordTxt.text)) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }
            println(usernameTxt.text)
            println(passwordTxt.text)
        }
        signHereButton.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)

            startActivity(Intent(intent))
        }
        googleBtn.setOnClickListener(){
            val intent2 = Intent(this, MenuEmployeeActivity::class.java)
            startActivity(Intent(intent2))
        }

    }
}