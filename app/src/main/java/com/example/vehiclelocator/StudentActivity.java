package com.example.vehiclelocator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {

      private EditText signInEmailEditTextId2,signInPasswordEditTextId2;
      private TextView signUpTextViewId2;
      private ImageButton signInButtonId2;
      private ProgressBar progressBar3;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        mAuth = FirebaseAuth.getInstance();

        signInEmailEditTextId2=(EditText)findViewById(R.id.signInEmailEditTextId2);
                signInPasswordEditTextId2=(EditText)findViewById(R.id.signInPasswordEditTextId2);
        signUpTextViewId2=(TextView)findViewById(R.id.signUpTextViewId2);
                signInButtonId2=(ImageButton)findViewById(R.id.signInButtonId2);
        progressBar3=(ProgressBar)findViewById(R.id.progressBar3);

        signUpTextViewId2.setOnClickListener(this);
        signInButtonId2.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.signInButtonId2:

             stuLogin();
                break;

            case R.id.signUpTextViewId2:

                Intent intent = new Intent(getApplicationContext(),StudentSignup.class);
                startActivity(intent);
                break;


        }



    }

    private void stuLogin() {

        String email= signInEmailEditTextId2.getText().toString().trim();
        String password= signInPasswordEditTextId2.getText().toString().trim();

        if(email.isEmpty()){

            signInEmailEditTextId2.setError("Dude Enter an Email plz");
            signInEmailEditTextId2.requestFocus();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            signInEmailEditTextId2.setError("Enter a Valid Email");
            signInEmailEditTextId2.requestFocus();
            return;

        }
        if (password.isEmpty()){

            signInPasswordEditTextId2.setError("Enter a password");
            signInPasswordEditTextId2.requestFocus();
            return;

        }
        if (password.length()<6){

            signInPasswordEditTextId2.setError("Enter at least 6 numbers");
            signInPasswordEditTextId2.requestFocus();
            return;

        }

        progressBar3.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressBar3.setVisibility(View.GONE);

                        if (task.isSuccessful()) {

                         Intent intent= new Intent(getApplicationContext(),StudentMap.class);
                         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                         startActivity(intent);

                        } else {


                            Toast.makeText(getApplicationContext(),"Login unsuccessful _ Check everything once more",Toast.LENGTH_SHORT).show();

                        }


                    }
                });


    }
}
