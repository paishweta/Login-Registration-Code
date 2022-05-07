package com.example.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    public static String one;
    EditText mfirstName, mlastName, memail, mbirthday, mpassword;
    Button mregisterButton;
    DBHelper DB;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mfirstName = (EditText) findViewById(R.id.firstName);
        mlastName = (EditText) findViewById(R.id.lastName);
        memail = (EditText) findViewById(R.id.emailId);
        mpassword= (EditText) findViewById(R.id.password);
        mregisterButton = findViewById(R.id.registerBtn);
        DB = new DBHelper(this);

        // on signup btn click
        mregisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = mfirstName.getText().toString();
                String lastName = mlastName.getText().toString();
                String email = memail.getText().toString();
                String password = mpassword.getText().toString();
                if(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(email)  && TextUtils.isEmpty(password))
                {
                    mfirstName.setError("First name is required");
                    mlastName.setError("Last name is required");
                    memail.setError("Email is required");
                    mpassword.setError("Password is required");
                }else{
                    Boolean checkUser = DB.checkUser(email);
                    if(checkUser == false) {
                        Boolean insert = DB.insertData(firstName,lastName, email, password);
                        if(insert == true){
                            Toast.makeText(getApplicationContext(),
                                    "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                 Intent i = new Intent(SignUp.this, MainActivity.class);
                                 startActivity(i);
                        }else{
                            Toast.makeText(getApplicationContext(), "Registration Failed",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), "User Already exists! Please Login.",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        View login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent loginIntent = new Intent(SignUp.this, MainActivity.class);
                startActivity(loginIntent);
            }
        });
    }
}