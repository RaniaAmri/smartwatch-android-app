package com.example.comexemplepi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText email;
    Button resetBtn;
    TextView retour;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.email_reset);
        resetBtn = findViewById(R.id.button_reset);
        retour = findViewById(R.id.retour);
        auth = FirebaseAuth.getInstance();

        // Ajouter les listeners pour le bouton et le texte de retour
        resetBtn.setOnClickListener(view -> resetPassword());
        retour.setOnClickListener(view -> startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class)));
    }

    // Implémenter la fonction de réinitialisation de mot de passe
    private void resetPassword() {
        String emailtxt = email.getText().toString().trim();

        if (TextUtils.isEmpty(emailtxt)) {
            email.setError("Email cannot be empty");
            email.requestFocus();
        } else {
            auth.sendPasswordResetEmail(emailtxt).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

