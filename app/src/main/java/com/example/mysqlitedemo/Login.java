package com.example.mysqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysqlitedemo.helper.DatabaseHelper;
import com.google.android.material.snackbar.Snackbar;

public class Login extends AppCompatActivity {
    private TextView textView;
    private Context context;
    private EditText et_email, et_password;
    private Button bt_submit;
    private String email, password;
    private DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        textView=findViewById (R.id.textview);
        et_email = findViewById(R.id.et_login_email);
        et_password = findViewById(R.id.et_login_password);
        bt_submit = findViewById(R.id.bt_login);

        databaseHelper = new DatabaseHelper(this);


        context = getApplicationContext();

        textView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i =new Intent (getApplicationContext (), Signup.class);
                startActivity (i);
            }
        });

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = et_email.getText().toString().trim();
                password = et_password.getText().toString().trim();

                //validate fields
                if (!email.contains("@")|| !email.contains(".com")|| email.isEmpty()){
                    et_email.setError("error");
                    return;
                }
                if (password.isEmpty()){
                    et_password.setError("password empty");
                    return;
                }
                // check if email and password exist in the database
                if (databaseHelper.checkUser(email, password)){

                    //if email and password exist, redirect user to the dashboard
                    Intent accountsIntent = new Intent(Login.this, Dashboard.class);

                    //it will pass user email to the dashboard activity
                    accountsIntent.putExtra("EMAIL", email);
                    et_email.setText("");
                    et_password.setText("");
                    startActivity(accountsIntent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"incorrect email or password",Toast.LENGTH_LONG).show();
                }

            }
        });
    }




    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
