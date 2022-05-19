package com.example.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                if(TextUtils.isEmpty(emailText) || TextUtils.isEmpty(firstNameText) || TextUtils.isEmpty(lastNameText) || TextUtils.isEmpty(passwordText1) || TextUtils.isEmpty(passwordText2)){
                    Toast.makeText(SignUp.this,"All fields are required" , Toast.LENGTH_SHORT).show();
                }
                else{
                    if (passwordText1.equals(passwordText2)){
                        //TODO WITH - BACKEND
                        System.out.println(emailText + " " + firstNameText + " " + lastNameText + " " + passwordText1);
                    }
                }
            }
        });

    }
}