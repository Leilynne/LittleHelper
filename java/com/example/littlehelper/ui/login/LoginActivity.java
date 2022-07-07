package com.example.littlehelper.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.littlehelper.MainActivity;
import com.example.littlehelper.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private EditText edLogin, edPassword;
    private FirebaseAuth mAuth;
    private TextView textHi;
    private Button buttonToStart, buttonToLogin, buttonRegistration, buttonAutorization;
    private ImageView profileImage;
    private static String email;
    public static String getEmail() {
        return email;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        init();
    }
    private void init(){
        edLogin = findViewById(R.id.textEmailAddress);
        edPassword = findViewById(R.id.textPassword);
        mAuth = FirebaseAuth.getInstance();
        textHi = findViewById(R.id.text_Hello);
        buttonToStart = findViewById(R.id.button_Hello);
        buttonToLogin = findViewById(R.id.button_notHello);
        buttonAutorization = findViewById(R.id.button_autorization);
        buttonRegistration = findViewById(R.id.button_registration);
        profileImage = findViewById(R.id.imageView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if (cUser!= null){
            showSigned();
        } else{
            notShow();
        }
    }

    public void onClickRegistration(View view) {
        if(!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString())){
            mAuth.createUserWithEmailAndPassword(edLogin.getText().toString(), edPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        showSigned();
                        sendEmailVer();

                    } else {
                        notShow();
                        Toast.makeText(getApplicationContext(), "Пользователь не зарегистрирован!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickAutorization(View view) {
        if(!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString())){
        mAuth.signInWithEmailAndPassword(edLogin.getText().toString(), edPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Добро пожаловать!", Toast.LENGTH_SHORT).show();

                    showSigned();
                } else {
                    Toast.makeText(getApplicationContext(), "Неверный логин и/или пароль!", Toast.LENGTH_SHORT).show();
                    notShow();
                }

            }
        });

    }
    }

    public void onClickToStartAd(View view) {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }

    public void onClickToBackAutorization(View view) {
        FirebaseAuth.getInstance().signOut();
        notShow();
    }
    private void showSigned() {
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        if (user.isEmailVerified()) {
            email = Objects.requireNonNull(user.getEmail()).replaceAll("\\.", " ");

            buttonToStart.setVisibility(View.VISIBLE);
            buttonToLogin.setVisibility(View.VISIBLE);
            textHi.setVisibility(View.VISIBLE);
            String hi = "Вы вошли как " + user.getEmail();
            textHi.setText(hi);
            edPassword.setVisibility(View.GONE);
            edLogin.setVisibility(View.GONE);
            profileImage.setVisibility(View.GONE);
            buttonRegistration.setVisibility(View.GONE);
            buttonAutorization.setVisibility(View.GONE);
        } else  {
            Toast.makeText(getApplicationContext(), "Пожалуйста, подтвердите вашу почту", Toast.LENGTH_SHORT).show();
        }
    }
    private void notShow(){
        buttonToStart.setVisibility(View.GONE);
        buttonToLogin.setVisibility(View.GONE);
        textHi.setVisibility(View.GONE);
        edPassword.setVisibility(View.VISIBLE);
        edLogin.setVisibility(View.VISIBLE);
        profileImage.setVisibility(View.VISIBLE);
        buttonRegistration.setVisibility(View.VISIBLE);
        buttonAutorization.setVisibility(View.VISIBLE);
    }
    private void sendEmailVer(){
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Мы выслали ссылку для подтверждения вашего аккуннта на указанный адрес", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Что-то пошло не так. Попробуйте еще раз", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}

