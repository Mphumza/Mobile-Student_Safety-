package com.example.student;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private ImageView passwordToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // UI Components
        emailEditText = findViewById(R.id.parentEmailEditText);
        passwordEditText = findViewById(R.id.parentPasswordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        passwordToggle = findViewById(R.id.passwordToggle);

        // Autofocus email
        emailEditText.requestFocus();

        // Enable paste/select manually
        enableCopyPaste(emailEditText);
        enableCopyPaste(passwordEditText);

        // Show/Hide Password
        passwordToggle.setOnClickListener(v -> togglePasswordVisibility());

        // Login Button
        loginButton.setOnClickListener(v -> loginParent());
    }

    private void togglePasswordVisibility() {
        int currentType = passwordEditText.getInputType();
        if (currentType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            // Show password
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            passwordToggle.setImageResource(R.drawable.baseline_visibility_24); // eye-open icon
        } else {
            // Hide password
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordToggle.setImageResource(R.drawable.baseline_visibility_off_24); // eye-closed icon
        }
        // Move cursor to end
        passwordEditText.setSelection(passwordEditText.getText().length());
    }

    private void loginParent() {
        String emailInput = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(emailInput)) {
            emailEditText.setError("Enter your email");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Enter your password");
            return;
        }

        String email = emailInput.toLowerCase();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Check if user exists in Firestore
                        Log.d("LOGIN_DEBUG", "Signed in. Checking Firestore for parent: " + email);
                        db.collection("parents").document(email).get()
                                .addOnCompleteListener(parentTask -> {
                                    if (parentTask.isSuccessful()) {
                                        DocumentSnapshot document = parentTask.getResult();
                                        if (document != null && document.exists()) {
                                            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(MainActivity.this, StudentDashboardActivity.class);
                                            intent.putExtra("parentEmail", email);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Log.w("LOGIN_DEBUG", "Parent document not found for: " + email);
                                            Toast.makeText(MainActivity.this, "Parent account not found in database", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Exception error = parentTask.getException();
                                        Log.e("FIRESTORE_ERROR", "Error accessing Firestore document for: " + email, error);
                                        Toast.makeText(MainActivity.this, "Error accessing database: " + (error != null ? error.getMessage() : "Unknown error"), Toast.LENGTH_LONG).show();
                                    }
                                });
                    } else {
                        Exception error = task.getException();
                        Toast.makeText(MainActivity.this, "Login failed: " + (error != null ? error.getMessage() : "Unknown error"), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void enableCopyPaste(EditText editText) {
        editText.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override public boolean onCreateActionMode(ActionMode mode, Menu menu) { return true; }
            @Override public boolean onPrepareActionMode(ActionMode mode, Menu menu) { return false; }
            @Override public boolean onActionItemClicked(ActionMode mode, MenuItem item) { return false; }
            @Override public void onDestroyActionMode(ActionMode mode) {}
        });
        editText.setLongClickable(true);
        editText.setTextIsSelectable(true);
    }
}
