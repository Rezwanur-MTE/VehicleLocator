package com.example.vehiclelocator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverActivity extends AppCompatActivity implements View.OnClickListener {

     private EditText signInEmailEditTextId,signInPasswordEditTextId;
     private TextView signUpTextViewId;
     private Button signInButtonId;
     private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        this.setTitle("Sign In and Start");


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        signInEmailEditTextId=(EditText)findViewById(R.id.signInEmailEditTextId);
        signInPasswordEditTextId=(EditText)findViewById(R.id.signInPasswordEditTextId);
        signInButtonId=(Button)findViewById(R.id.signInButtonId);
        signUpTextViewId=(TextView)findViewById(R.id.signUpTextViewId);

     signUpTextViewId.setOnClickListener(this);
     signInButtonId.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.signInButtonId:
                userLogin();

                break;

            case R.id.signUpTextViewId:

                Intent intent=new Intent(getApplicationContext(),DriverSignup.class);
                 startActivity(intent);


                break;




        }


    }

    private void userLogin() {

        String email=signInEmailEditTextId.getText().toString().trim();
        String password=signInPasswordEditTextId.getText().toString().trim();

        // Checking the validity of the email..........

        if (email.isEmpty()){

            signInEmailEditTextId.setError("Enter an Email plz..");
            signInEmailEditTextId.requestFocus();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signInEmailEditTextId.setError("Enter a Valid Email..");
            signInEmailEditTextId.requestFocus();
            return;
        }
// now we will check the validity of password

        if (password.isEmpty()){

            signInPasswordEditTextId.setError("Enter passwords plz_");
            signInPasswordEditTextId.requestFocus();
            return;
        }

        if (password.length()<6){

            signInPasswordEditTextId.setError("Enter at least 6 digits_");
            signInPasswordEditTextId.requestFocus();
            return;

        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressBar.setVisibility(View.GONE);

                if(task.isSuccessful()){


                   Intent intent=new Intent(getApplicationContext(),MapActivity.class);
                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(),"Login Unsuccessful",Toast.LENGTH_SHORT).show();
                }



            }
        });



    }
}
