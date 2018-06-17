package com.biu.leftover;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.biu.leftover.model.User;
import com.biu.leftover.utils.Constants;
import com.biu.leftover.utils.DBUtils;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    //firebase auth.
    private FirebaseAuth mAuth;

    //android fields.
    private EditText mName;
    private EditText mEmail;
    private EditText mPassword;
    private Button mRegBtn;

    //toolbar
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mToolBar = findViewById(R.id.register_toolbar);
        mAuth = FirebaseAuth.getInstance();
        mName = findViewById(R.id.register_name);
        mEmail = findViewById(R.id.register_email);
        mPassword = findViewById(R.id.register_password);
        mRegBtn = findViewById(R.id.register_reg_btn);

        //Toolbar set
        try {
            setSupportActionBar(mToolBar);
            getSupportActionBar().setTitle(Constants.TOOL_BAR_TITLE_REGISTER);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(this.getLocalClassName(), e.getMessage());
        }

        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mName.getText().toString();
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                //if one of them is empty - do not register.
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    return;
                }
                registerUser(name, email, password);
            }

            private void registerUser(final String name, final String email, String password) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                DBUtils.addObject(Constants.USERS, new User(FirebaseAuth.getInstance().getCurrentUser()));

                                Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(mainIntent);
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
}
