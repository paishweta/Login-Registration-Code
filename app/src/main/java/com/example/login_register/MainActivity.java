package com.example.login_register;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText memail,mpassword;
    Button button;
    DBHelper DB;
    private Object view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memail = (EditText) findViewById(R.id.email);
        mpassword= (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.button_login);
        DB = new DBHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = memail.getText().toString();
                String password = mpassword.getText().toString();
                if(TextUtils.isEmpty(email)  && TextUtils.isEmpty(password)){
                    memail.setError("Email is required");
                    mpassword.setError("Password is required");
                }
                else {
                    Boolean checkUserPass = DB.checkUserPass(email,password);
                    if(checkUserPass == true) {
                            Toast.makeText(getApplicationContext(),
                                    "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this, Homepage.class);
                            startActivity(i);
                        }else{
                        Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


//-------------------------------------------------------------------------------------------------------------
//For New Signup page intent

        View signup = findViewById(R.id.textView5);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(MainActivity.this, SignUp.class);
                startActivity(homeIntent);
            }
        });
    }
}
