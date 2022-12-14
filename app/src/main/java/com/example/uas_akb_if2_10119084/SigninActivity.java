package com.example.uas_akb_if2_10119084;
/**
 * Nama : Muhammad Idris M
 * Kelas : IF2
 * NIM :10119084
 * Email : idris.10119084@mahasiswa.unikom.ac.id
 * **/
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uas_akb_if2_10119084.ui.daily.FragmentNote;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {
    EditText signinEmail, signinPass;
    Button sigin;
    TextView siginLogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signinEmail = findViewById(R.id.editText3);
        signinPass = findViewById(R.id.editText4);
        sigin = findViewById(R.id.btnSignin);
        siginLogin = (TextView) findViewById(R.id.btnDontHave);

        fAuth = FirebaseAuth.getInstance();

        sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signinEmail.getText().toString().trim();
                String pass = signinPass.getText().toString().trim();
                if (TextUtils.isEmpty(email)){
                    signinEmail.setError("Masukkan Email !");
                }
                if (TextUtils.isEmpty(pass)){
                    signinPass.setError("Masukkan Pass. ");
                }
                if (pass.length()<6){
                    signinPass.setError("Pass harus lebih dari 6 karakter");
                }else {
                    fAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(SigninActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SigninActivity.this, "Kamu berhasil login", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                            } else {
                                Toast.makeText(SigninActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
        siginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SigninActivity.this, SignupActivity.class));
            }
        });

    }

}
