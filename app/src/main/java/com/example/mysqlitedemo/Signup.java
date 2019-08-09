package com.example.mysqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mysqlitedemo.helper.DatabaseHelper;
import com.example.mysqlitedemo.helper.User;

public class Signup extends AppCompatActivity {
    private EditText et_name, et_password, et_email, et_confirm_password, et_hobby;
    private Button submit_form;
    private DatabaseHelper databaseHelper;
    private User user;
    private String name, password, email, cpassword, hobby;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_sign_up);

        et_name = findViewById(R.id.et_register_name);
        et_password = findViewById(R.id.et_register_password);
        et_email = findViewById(R.id.et_register_email);
        et_confirm_password = findViewById(R.id.et_register_cpassword);
        et_hobby = findViewById(R.id.et_register_hobby);
        submit_form = findViewById(R.id.bt_register);

        databaseHelper = new DatabaseHelper(this);
        user = new User();

        submit_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get user iputed text from the xml file
                name = et_name.getText().toString().trim();
                password = et_password.getText().toString().trim();
                email = et_email.getText().toString().trim();
                cpassword = et_confirm_password.getText().toString().trim();
                hobby = et_hobby.getText().toString().trim();

                //Validate the user input
                if (name.isEmpty()){
                    et_name.setError("name empty");
                    return;
                }
                if (!email.contains("@")|| !email.contains(".com")|| email.isEmpty()){
                    et_email.setError("error");
                    return;
                }
                if (password.isEmpty()||password.length()<5){
                    et_password.setError("password empty");
                    return;
                }
                if (!password.matches(cpassword)){
                    et_confirm_password.setError("password mismatch");
                    return;
                }
                if (hobby.isEmpty()){
                    et_hobby.setError("hobby empty");
                    return;
                }
                //checks if user email is not in the database
                if (!databaseHelper.checkUser(email)){
                    //sets the user's data
                    user.setName(name);
                    user.setEmail(email);
                    user.setHobby(hobby);
                    user.setPassword(password);

                    //adds user's data to the database
                    databaseHelper.addUser(user);

                    //displays a toast that user was added successfully to the database
                    Toast.makeText(getApplicationContext(),"User Created Successfully",Toast.LENGTH_LONG).show();

                    //redirect user to the Login
                    Intent accountsIntent = new Intent(Signup.this, Login.class);
                    accountsIntent.putExtra("EMAIL", email);
                    //empty the text fields
                    et_email.setText("");
                    et_password.setText("");
                    et_hobby.setText("");
                    et_name.setText("");

                    startActivity(accountsIntent);
                    finish();


                } else {
                    //if user's email already exist data will not be added
                    Toast.makeText(getApplicationContext(),"User already Exist",Toast.LENGTH_LONG).show();

                }

            }
        });

    }
}
