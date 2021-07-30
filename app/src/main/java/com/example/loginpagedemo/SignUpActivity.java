package com.example.loginpagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private EditText nameEditText,emailEditText,userNameEditText,passwordEditText;
    private Button signUpButton;
    DatabaseHelper myDatabaseHelper;
    UserDetails userDetails = new UserDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameEditText = findViewById(R.id.userNameId);
        emailEditText = findViewById(R.id.userEmailId);
        userNameEditText = findViewById(R.id.userUsernameId);
        passwordEditText = findViewById(R.id.userPasswordId);

        //Button
        signUpButton = findViewById(R.id.signUpbuttonId);

        //database
        myDatabaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String username = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                userDetails.setName(name);
                userDetails.setEmail(email);
                userDetails.setUsername(username);
                userDetails.setPassword(password);

                if(name.equals("") || email.equals("") || username.equals("") || password.equals("")  ){
                    Toast.makeText(getApplicationContext(),"Please Enter all the data ",Toast.LENGTH_LONG).show();
                }else {

                    long rowId = myDatabaseHelper.insertData(userDetails);

                    if (rowId > 0) {

                        Toast.makeText(getApplicationContext(), "Successfully " + rowId + " no row is inserted", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Unsuccessfull", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });


    }
}