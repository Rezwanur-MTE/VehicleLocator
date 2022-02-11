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

public class DriverSignup extends AppCompatActivity implements View.OnClickListener {

    private EditText signUpEmailEditTextId,signUpPasswordEditTextId;
    private TextView signInTextViewId;
    private Button signUpButtonId;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_signup);
        this.setTitle("Sign Up and Welcome");
        mAuth = FirebaseAuth.getInstance();

        progressBar2=(ProgressBar)findViewById(R.id.progressBar2);
        signUpEmailEditTextId=(EditText)findViewById(R.id.signUpEmailEditTextId);
        signUpPasswordEditTextId=(EditText)findViewById(R.id.signUpPasswordEditTextId);
        signUpButtonId=(Button)findViewById(R.id.signUpButtonId);
        signInTextViewId=(TextView)findViewById(R.id.signInTextViewId);

        signInTextViewId.setOnClickListener(this);
        signUpButtonId.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.signUpButtonId:

                userRegister();

                break;

            case R.id.signInTextViewId:

                Intent intent=new Intent(getApplicationContext(),DriverActivity.class);
                startActivity(intent);


                break;




        }


    }

    private void userRegister() {
         progressBar2.setVisibility(View.VISIBLE);
        String email=signUpEmailEditTextId.getText().toString().trim();
        String password=signUpPasswordEditTextId.getText().toString().trim();

        // Checking the validity of the email..........

        if (email.isEmpty()){

            signUpEmailEditTextId.setError("Enter an Email plz..");
            signUpEmailEditTextId.requestFocus();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signUpEmailEditTextId.setError("Enter a Valid Email..");
            signUpEmailEditTextId.requestFocus();
            return;
        }
// now we will check the validity of password

        if (password.isEmpty()){

            signUpPasswordEditTextId.setError("Enter passwords plz_");
            signUpPasswordEditTextId.requestFocus();
            return;
        }

        if (password.length()<6){

            signUpPasswordEditTextId.setError("Enter at least 6 digits_");
            signUpPasswordEditTextId.requestFocus();
            return;

        }



        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                progressBar2.setVisibility(View.GONE);


                if (task.isSuccessful()) {

                    Toast.makeText(getApplicationContext(),"Register is Successful",Toast.LENGTH_SHORT).show();

                }
                else {

                    Toast.makeText(getApplicationContext(),"Register is not Successful",Toast.LENGTH_SHORT).show();

                }

            }
        });



    }
}
