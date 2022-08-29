package my.edu.utar.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText input_username, input_email, input_password;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        input_username = findViewById(R.id.inputUsername);
        input_email = findViewById(R.id.inputEmail);
        input_password = findViewById(R.id.inputPassword2);

        progress = findViewById(R.id.progressBar2);

        Button register = findViewById(R.id.Register);
        register.setOnClickListener(view -> registerUser());
    }

    private void registerUser() {
        String username = input_username.getText().toString().trim();
        String email = input_email.getText().toString().trim();
        String password = input_password.getText().toString().trim();

        if(username.isEmpty()) {
            input_username.setError("Username is required!");
            input_username.requestFocus();
            return;
        }

        if(email.isEmpty()) {
            input_email.setError("Email is required!");
            input_email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            input_email.setError("Please provide valid email address!");
            input_email.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            input_password.setError("Password is required!");
            input_password.requestFocus();
            return;
        }

        if(password.length() < 6) {
            input_password.setError("Password should be at least 6 characters!");
            input_password.requestFocus();
            return;
        }

        progress.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            User user = new User(username, email);
                            String mUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(mUid)
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(Register.this, "User has been registered successfully!",
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(Register.this, "Failed to register, please try again!",
                                                Toast.LENGTH_LONG).show();
                                    }
                                    progress.setVisibility(View.GONE);
                                }
                            });
                        } else {
                            Toast.makeText(Register.this, "Failed to register, please try again!",
                                    Toast.LENGTH_LONG).show();
                            progress.setVisibility(View.GONE);
                        }
                    }
                });
    }
}