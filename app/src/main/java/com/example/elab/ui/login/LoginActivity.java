package com.example.elab.ui.login;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elab.main.activities.InfoActivity;
import com.example.elab.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    ImageView ivLogo;
    TextView passwordEditText, usernameEditText;
    Button loginButton;
    Animation fromuptodown, fromdowntoup;

    private FirebaseAuth mAuth;
    public Boolean existInDatabase = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        mAuth = FirebaseAuth.getInstance();

        fromuptodown = AnimationUtils.loadAnimation(this, R.anim.fromuptodown);
        fromdowntoup = AnimationUtils.loadAnimation(this, R.anim.fromdowntoup);

        ivLogo = findViewById(R.id.ivLogo);

        usernameEditText    = findViewById(R.id.username);
        passwordEditText    = findViewById(R.id.password);
        loginButton         = findViewById(R.id.login);

        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        final Handler handler = new Handler();



        loginButton.setAlpha(0);
        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    System.out.println("El form está correcto");
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                loginButton.setAlpha(1);
                if (loginFormState.getUsernameError() != null) {
                    System.out.println("El Usuario tiene un error");
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                    loginButton.setAlpha(0);
                }
                if (loginFormState.getPasswordError() != null) {
                    System.out.println("El password tiene un error");
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                    loginButton.setAlpha(0);
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                //loadingProgressBar.setVisibility(View.GONE);
                loadingProgressBar.setTranslationY(50);
                loadingProgressBar.setVisibility(View.VISIBLE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                    final String username = usernameEditText.getText().toString();
                    final String password = passwordEditText.getText().toString();

                    mAuth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    loadingProgressBar.setVisibility(View.GONE);
                                    if (!task.isSuccessful()) {
                                        // there was an error
                                        existInDatabase = false;
                                        Toast.makeText(LoginActivity.this, "Error en el usuario o contraseña...", Toast.LENGTH_LONG).show();
                                        return;
                                    } else {
                                        existInDatabase = true;
                                        Intent goToNextActivity = new Intent(getApplicationContext(), InfoActivity.SplashAct.class);
                                        startActivity(goToNextActivity);
                                        finish();
                                    }
                                }
                            });
                }
                setResult(Activity.RESULT_OK);

                loginButton.animate().alpha(0).setDuration(800).setStartDelay(100).start();
                ivLogo.animate().translationY(400).setDuration(800).setStartDelay(400).start();
                usernameEditText.animate().translationY(-400).alpha(0).setDuration(800).setStartDelay(400).start();
                passwordEditText.animate().translationY(-400).alpha(0).setDuration(800).setStartDelay(400).start();

                ivLogo.animate().translationZ(90).setDuration(900).setStartDelay(420).start();


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        loadingProgressBar.setVisibility(View.GONE);
                        /*if(existInDatabase){
                            Intent goToNextActivity = new Intent(getApplicationContext(), InfoActivity.SplashAct.class);
                            startActivity(goToNextActivity);
                        }*/
                    }
                }, 5000);

                //Complete and destroy login activity once successful
                //finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        /*passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });*/

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                /*loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());*/

                //authenticate user
                final String username = usernameEditText.getText().toString();
                final String password = passwordEditText.getText().toString();

                mAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                loadingProgressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    existInDatabase = false;
                                    Toast.makeText(LoginActivity.this, "Error en el usuario o contraseña...", Toast.LENGTH_LONG).show();
                                } else {
                                    existInDatabase = true;
                                    Intent goToNextActivity = new Intent(getApplicationContext(), InfoActivity.SplashAct.class);
                                    startActivity(goToNextActivity);
                                    finish();
                                }
                            }
                        });


            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = "Welcome  " + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
