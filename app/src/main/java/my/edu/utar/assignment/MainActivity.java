package my.edu.utar.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText input_ID, input_password;
    private ProgressBar progress;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_ID = findViewById(R.id.InputID);
        input_password = findViewById(R.id.InputPassword);
        mAuth = FirebaseAuth.getInstance();
        progress = findViewById(R.id.progressBar);

        TextView reset = findViewById(R.id.forget_password);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ForgetPassword.class));
            }
        });

        Button register = findViewById(R.id.RegisterPage);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });

        Button login = findViewById(R.id.Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

    }

    private void userLogin() {
        String id = input_ID.getText().toString().trim();
        String password = input_password.getText().toString().trim();

        if(id.isEmpty()) {
            input_ID.setError("Email is required!");
            input_ID.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            input_password.setError("Password is required!");
            input_password.requestFocus();
            return;
        }

        progress.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(id, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()) {
                        startActivity(new Intent(MainActivity.this, MainApp.class));
                    } else {
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this, "Please verifty your email! verification mail has been sent!",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Failed to log in, please try again!",
                            Toast.LENGTH_LONG).show();
                }
                progress.setVisibility(View.GONE);
            }
        });
    }
}