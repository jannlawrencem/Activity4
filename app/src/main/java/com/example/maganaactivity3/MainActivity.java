package com.example.maganaactivity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText text_username, text_pass, text_retype;
    Button button_register, button_return, button_login;
    DBHelper DB;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            text_username = (EditText) findViewById(R.id.txt_username);
            text_pass = (EditText) findViewById(R.id.txt_pass);
            text_retype = (EditText) findViewById(R.id.txt_retype);
            button_register = (Button) findViewById(R.id.btn_register);
            button_return = (Button) findViewById(R.id.btn_return);
            button_login = (Button) findViewById(R.id.btn_login);
            DB = new DBHelper(this);
            button_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String user = text_username.getText().toString();
                    String pass = text_pass.getText().toString();
                    String retype = text_retype.getText().toString();

                    if(user.equals("")||pass.equals("")||retype.equals(""))
                        Toast.makeText(MainActivity.this,"Not all fields are answered", Toast.LENGTH_SHORT).show();
                    else{
                        if(pass.equals(retype)){
                            Boolean checkuser = DB.checkusername(user);

                            if(checkuser==false){
                                Boolean insert = DB.insertData(user,pass);

                                if(insert==true){
                                    Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),Loginpage.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(MainActivity.this,"Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(MainActivity.this,"User Already Registered Please Sign In", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this,"Password does not match", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

            button_return.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),Loginpage.class);
                    startActivity(intent);

                }
            });
        }
}