package com.nyoobie.tugasakhirhanif.awalan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.maps.errors.ApiException;
import com.nyoobie.tugasakhirhanif.DashboardActivity;
import com.nyoobie.tugasakhirhanif.R;
import com.nyoobie.tugasakhirhanif.data.AppState;

public class LoginActivity extends AppCompatActivity {
    private Button button;
    GoogleSignInClient googleSignInClient;
    int SIGN_IN = 0;
    private AppState appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        appState = AppState.getInstance();

        if (AppState.getInstance().isLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish();
        }

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        button = findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        button.setVisibility(View.INVISIBLE);
        Intent login = googleSignInClient.getSignInIntent();
        startActivityForResult(login, SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("OnActivityResult", "On Activity Result");

        if (requestCode == SIGN_IN) {
            Task<GoogleSignInAccount> taskLogin = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignIn(taskLogin);
            Log.d("OnActivityResult", "Success");
        } else {
            Log.d("OnActivityResult", "Failed");
        }
    }

    private void handleSignIn(Task<GoogleSignInAccount> taskLogin) {
        try {
            GoogleSignInAccount account = taskLogin.getResult(ApiException.class);
//            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(LoginActivity.this);
            Toast.makeText(LoginActivity.this,account.getDisplayName(),Toast.LENGTH_LONG).show();

            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);

//            if (acct != null) {
//                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
//                setUser();
//                signOut();
//                finish();
//            } else {
//                button.setVisibility(View.VISIBLE);
//                Toast.makeText(this, "Gagal Login", Toast.LENGTH_LONG).show();
//            }
        } catch (ApiException e) {
            e.printStackTrace();
            Log.d("DEBUG", e.getMessage());
        }
    }

    private void signOut() {
        googleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("KELUAR", "Revoke Account");
            }
        });
    }

    private void setUser() {
        appState.setIsLoggedIn(true);
    }

}
