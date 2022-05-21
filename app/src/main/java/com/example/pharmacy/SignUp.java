package com.example.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pharmacy.network.JsonPlaceHolderApi;
import com.example.pharmacy.network.JsonUserApi;
import com.example.pharmacy.network.NullOnEmptyConverterFactory;
import com.example.pharmacy.network.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp extends AppCompatActivity {

    EditText firstName, lastName, password1, repassword, email;
    Button signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        signUp = findViewById(R.id.registerbtn);
        password1 = findViewById(R.id.password1);
        repassword = findViewById(R.id.password2);
        email = findViewById(R.id.usernameSignUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailText = email.getText().toString();
                String firstNameText = firstName.getText().toString();
                String lastNameText = lastName.getText().toString();
                String passwordText1 = password1.getText().toString();
                String passwordText2 = repassword.getText().toString();
                if (TextUtils.isEmpty(emailText) || TextUtils.isEmpty(firstNameText) || TextUtils.isEmpty(lastNameText)
                        || TextUtils.isEmpty(passwordText1) || TextUtils.isEmpty(passwordText2)) {
                    Toast.makeText(SignUp.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    if (passwordText1.equals(passwordText2)) {
                        Log.d("success", emailText + " " + firstNameText + " " + lastNameText);

                        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/api/v1/")
                                .addConverterFactory(new NullOnEmptyConverterFactory())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        JsonUserApi jsonUserApi = retrofit.create(JsonUserApi.class);
                        String client = "CLIENT";
                        Call<User> call = jsonUserApi.addUser(firstNameText, lastNameText, emailText, passwordText1, client);
                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                Log.d("ONRESPONSE", String.valueOf(response.body()));
                                Toast.makeText(SignUp.this, "Registered successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this, Login.class));
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Log.d("ONFAILURE", emailText + " " + firstNameText + " " + lastNameText);
                                t.printStackTrace();
                            }
                        });

                    } else {
                        Toast.makeText(SignUp.this, "The passwords does not match", Toast.LENGTH_SHORT).show();
                        Log.d("FAIL -> password", emailText + " " + firstNameText + " " + lastNameText);
                    }
                }


            }
        });

    }
}