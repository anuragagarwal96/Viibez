package com.dbc.viibez_messagingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class LogInActivity extends AppCompatActivity {

    // TODO: Add member variables here:
    private FirebaseAuth mAuth;
    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mEmailView = (EditText) findViewById(R.id.login_email);
        mPasswordView = (EditText) findViewById(R.id.login_password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == 100 || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        // TODO: Grab an instance of FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

    }

    // Executed when Sign in button pressed
    public void logInButton(View view){

        attemptLogin();

    }
    // Executed when Register button pressed
    public void registerButton(View view){
        Intent intent = new Intent(this,RegistrationScreen.class);
        finish();
        startActivity(intent);
    }


    private void attemptLogin() {

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        if(email.equals("") || password.equals("")) return;

        Toast.makeText(getApplicationContext(),"LogIn in Progres...",Toast.LENGTH_SHORT).show();

        // TODO: Use FirebaseAuth to sign in with email & password

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                if(!task.isSuccessful()){
                    showErrorDialog("There was a problem in LogIn");

                }else{

                    Intent nextScreen = new Intent(LogInActivity.this, ChatList.class);
                    finish();
                    startActivity(nextScreen);
                }


            }
        });


    }

    // TODO: Show error on screen with an alert dialog

    private void showErrorDialog(String message){

        new AlertDialog.Builder(this)
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();




    }


}