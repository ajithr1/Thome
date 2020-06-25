package com.ajith.fu.RandL;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ajith.fu.MainActivity;
import com.ajith.fu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Register extends AppCompatActivity {

    private static final String TAG = "ajju";
    private TextInputEditText userNameRegister, passWordRegister;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userNameRegister = findViewById(R.id.username);
        passWordRegister = findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
    }

    public void loginFunction(View view) {

        mAuth.createUserWithEmailAndPassword(Objects.requireNonNull(userNameRegister.getText()).toString(),
                Objects.requireNonNull(passWordRegister.getText()).toString()).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: ");
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {

        if (user != null){
            Log.d(TAG, "Success: "+user.toString());
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }else {
            Log.d(TAG, "Failure: ");
        }
    }
}
