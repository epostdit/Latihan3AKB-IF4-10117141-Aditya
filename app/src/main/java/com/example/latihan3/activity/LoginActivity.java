package com.example.latihan3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.latihan3.R;
import com.example.latihan3.activity.model.User;
import com.example.latihan3.activity.sharedpreferences.Preferences;

/*
    Nama        : Aditya
    NIM         : 10117141
    Kelas       : IF4
    Tanggal     : 1 Mei 2020
    Deskripsi   : Membuat halaman Login dari segi logika pemrogramannya beserta dengan desain tampilannya.

 */

public class LoginActivity extends AppCompatActivity {

    TextView textViewMasuk, textViewDaftar;
    EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindViews();

        textViewMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiLogin();
            }
        });

        textViewDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
            }
        });
    }

    private void bindViews(){

        textViewMasuk = findViewById(R.id.txt_login_masuk);
        textViewDaftar = findViewById(R.id.txt_login_register);
        editTextUsername = findViewById(R.id.edt_login_username);
        editTextPassword = findViewById(R.id.edt_login_password);
    }

    private void validasiLogin(){

        editTextUsername.setError(null);
        editTextPassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        String username = editTextUsername.getText().toString();
        String password = editTextUsername.getText().toString();


        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Harus diisi");
            fokus = editTextUsername;
            cancel = true;
        } else if (!cekUser(username)) {
            editTextUsername.setError("Username Tidak Ditemukan");
            fokus = editTextUsername;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Harus Diisi");
            fokus = editTextPassword;
            cancel = true;
        } else if (!cekPassword(password)) {
            editTextPassword.setError("Data yang dimasukkan tidak sesuai");
            fokus = editTextPassword;
            cancel = true;
        }

        if (cancel) {
            fokus.requestFocus();
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            Preferences.setUserPreferences(getBaseContext(), user);
            Preferences.setLoggedInStatus(getBaseContext(), true);

            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }
    }

    private boolean cekUser(String username){
        return username.equals(Preferences.getRegisteredUser(getBaseContext()));
    }

    private boolean cekPassword(String password){
        return password.equals(Preferences.getRegisteredPassword(getBaseContext()));
    }
}
