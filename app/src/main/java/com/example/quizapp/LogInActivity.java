package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizapp.databinding.ActivityLogInBinding;
import com.example.quizapp.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {

    ActivityLogInBinding binding;
    Button btLogIn;
    Button btSignUp;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() !=null){
           startActivity(new Intent(LogInActivity.this,MainActivity.class));
           finish();
        }



       binding.btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,pass;
              email = binding.emailBox.getText().toString();
              pass = binding.passwordBox.getText().toString();

              auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()){
                          startActivity(new Intent(LogInActivity.this,MainActivity.class));
                          finish();
                      }else{
                          Toast.makeText(LogInActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                      }

                  }
              });

            }
        });


        binding.btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });


    }
}