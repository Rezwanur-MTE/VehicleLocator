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

public class StudentSignup extends AppCompatActivity implements View.OnClickListener {

    private EditText signUpEmailEditTextId2,signUpPasswordEditTextId2;
    private TextView signInTextViewId2;
    private Button signUpButtonId2;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);
        this.setTitle("Student sign up");

        mAuth = FirebaseAuth.getInstance();

        signUpEmailEditTextId2=(EditText)findViewById(R.id.signUpEmailEditTextId2);
        signUpPasswordEditTextId2=(EditText)findViewById(R.id.signUpPasswordEditTextId2);
        signInTextViewId2=(TextView)findViewById(R.id.signInTextViewId2);
        signUpButtonId2=(Button)findViewById(R.id.signUpButtonId2);

        progressBar4=(ProgressBar)findViewById(R.id.progressBar4);

        signInTextViewId2.setOnClickListener(this);
        signUpButtonId2.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.signUpButtonId2:

                stuRegister();
                break;

            case R.id.signInTextViewId2:

                Intent intent = new Intent(getApplicationContext(),StudentActivity.class);
                startActivity(intent);
                break;


        }



    }

    private void stuRegister() {

     String email= signUpEmailEditTextId2.getText().toString().trim();
     String password= signUpPasswordEditTextId2.getText().toString().trim();

     if(email.isEmpty()){

         signUpEmailEditTextId2.setError("Dude Enter an Email plz");
         signUpEmailEditTextId2.requestFocus();
         return;

     }
     if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

         signUpEmailEditTextId2.setError("Enter a Valid Email");
         signUpEmailEditTextId2.requestFocus();
         return;

     }
     if (password.isEmpty()){

         signUpPasswordEditTextId2.setError("Enter a password");
         signUpPasswordEditTextId2.requestFocus();
         return;

     }
     if (password.length()<6){

         signUpPasswordEditTextId2.setError("Enter at least 6 numbers");
         signUpPasswordEditTextId2.requestFocus();
         return;

     }
        progressBar4.setVisibility(View.VISIBLE);


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressBar4.setVisibility(View.GONE);

                        if (task.isSuccessful()) {


                            Toast.makeText(getApplicationContext(),"Register is successful",Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(getApplicationContext(),"Register is not successful",Toast.LENGTH_SHORT).show();

                        }


                    }
                });


    }
}
