package com.example.maganaactivity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Loginpage extends AppCompatActivity {

    EditText text_username1, text_password1;
    Button button_login,button_signup;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        text_username1 = (EditText) findViewById(R.id.txt_username2);
        text_password1 = (EditText) findViewById(R.id.txt_pass2);
        button_login = (Button) findViewById(R.id.btn_login);
        button_signup = (Button) findViewById(R.id.btn_signup);
        DB = new DBHelper(this);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = text_username1.getText().toString();
                String pass = text_password1.getText().toString();
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Loginpage.this, "Not all fields are answered", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(Loginpage.this, "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Loginpage.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


    }
}