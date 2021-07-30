package com.example.loginpagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    private Button signInButton,signUpHereButton;
    private EditText userNameEditText,userPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db  = mDatabaseHelper.getWritableDatabase();

                //Finding the View ID
        signInButton = findViewById(R.id.signInId);
        signUpHereButton = findViewById(R.id.signUpHereId);
        userNameEditText = findViewById(R.id.editTextUserNameId);
        userPasswordEditText = findViewById(R.id.editTextUserPasswordId);


        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String username = userNameEditText.getText().toString();
                String password = userPasswordEditText.getText().toString();

                if(view.getId() == R.id.signInId){

                    Boolean result =  mDatabaseHelper.findPassword(username,password);

                    if(result==true){
                        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"Incorrect username and password",Toast.LENGTH_LONG).show();
                    }



                }


            }
        });



        signUpHereButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);

            }
        });


    }
}