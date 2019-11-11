package com.example.elab.ui.login;

import android.app.Activity;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elab.R;
import com.example.elab.SplashAct;
import com.example.elab.ui.login.LoginViewModel;
import com.example.elab.ui.login.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    ImageView ivLogo;
    TextView passwordEditText, usernameEditText;
    Button loginButton;
    Animation fromuptodown, fromdowntoup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

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
                        Intent goToNextActivity = new Intent(getApplicationContext(), SplashAct.class);
                        startActivity(goToNextActivity);
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
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
