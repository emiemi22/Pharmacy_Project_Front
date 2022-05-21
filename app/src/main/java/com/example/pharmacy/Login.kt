package com.example.pharmacy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pharmacy.network.JsonUserApi
import com.example.pharmacy.network.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

            val email = usernameTxt.text.toString()
            val password = passwordTxt.text.toString()
            val intentClient = Intent(this, ClientActivity::class.java)
            val intentEmployee = Intent(this, MenuEmployeeActivity::class.java)
            Log.d("Login input", email)

            if (TextUtils.isEmpty(usernameTxt.text) || TextUtils.isEmpty(passwordTxt.text)) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }
            else {
                val retrofitBuilder =
                    Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("http://10.0.2.2:8080/api/v1/")
                        .build()
                        .create(JsonUserApi::class.java)
                val data = retrofitBuilder.getUsersList()
                data.enqueue(object : Callback<List<User>?>{
                    override fun onResponse(call: Call<List<User>?>, response: Response<List<User>?>) {
                        val responseBody = response.body()!!
                        var found = 0
                        for (myUser in responseBody){
                            Log.d("Login",myUser.emailAddress)
                            if(myUser.emailAddress.equals(email)  && myUser.password.equals(password)){
                                if (myUser.role.equals("EMPLOYEE"))
                                {
                                    Log.d("Login","Employee activity")
                                    startActivity(Intent(intentEmployee))
                                    found = 1
                                }
                                else if (myUser.role.equals("CLIENT")){
                                    startActivity(Intent(intentClient))
                                    found = 1
                                }
                            }
                        }
                        if (found == 0){
                            Toast.makeText(this@Login, "Username or password wrong!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<List<User>?>, t: Throwable) {
                        Log.d("Login", "Error while fetching " + t.message)
                        t.printStackTrace()
                    }

                })
            }


        }
        signHereButton.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)

            startActivity(Intent(intent))
        }
        googleBtn.setOnClickListener() {
            val intent2 = Intent(this, MenuEmployeeActivity::class.java)
            startActivity(Intent(intent2))
        }
        twitterBtn.setOnClickListener() {
            val viewIntent = Intent(
                "android.intent.action.VIEW",
                Uri.parse("https://www.youtube.com/watch?v=3qyZWDepPLI&ab_channel=AdrianMinune")
            )
            startActivity(viewIntent)
        }

    }
}