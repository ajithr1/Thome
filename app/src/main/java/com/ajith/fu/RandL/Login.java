package com.ajith.fu.RandL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.ajith.fu.MainActivity;
import com.ajith.fu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Login extends AppCompatActivity {

    private static final String TAG = "ajju";
    private FirebaseAuth mAuth;

    private TextInputEditText userNameLogin, passWordLogin;
    private ProgressBar progressLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        userNameLogin = findViewById(R.id.userLogin);
        passWordLogin = findViewById(R.id.passLogin);
        progressLogin = findViewById(R.id.progressLogin);

        progressLogin.setVisibility(View.INVISIBLE);
        Log.d(TAG, "onCreate: Login - ");
    }

    public void loginFunction(View view) {

        progressLogin.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(Objects.requireNonNull(userNameLogin.getText()).toString(),
                Objects.requireNonNull(passWordLogin.getText()).toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {

        if (user != null){
            Log.d(TAG, "Success: "+user.toString());
            startActivity(new Intent(this, MainActivity.class));
            progressLogin.setVisibility(View.INVISIBLE);
            finish();
        }else {
            Log.d(TAG, "Failure: ");
            progressLogin.setVisibility(View.INVISIBLE);
        }
    }

    public void registerMove(View view) {
        startActivity(new Intent(this, Register.class));
    }
}
