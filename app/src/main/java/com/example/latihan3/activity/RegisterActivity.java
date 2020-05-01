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
    Deskripsi   : Membuat halaman Register dari segi logika pemrogramannya beserta dengan desain tampilannya.


 */

public class RegisterActivity extends AppCompatActivity {

    TextView textViewDaftar;
    EditText editTextUsername, editTextPassword, editTextRetypePassword, editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bindViews();

        textViewDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiRegister();
            }
        });
    }

    private void bindViews() {
        textViewDaftar = findViewById(R.id.txt_reg_masuk);
        editTextUsername = findViewById(R.id.edt_reg_username);
        editTextPassword = findViewById(R.id.edt_reg_password);
        editTextRetypePassword = findViewById(R.id.edt_reg_password_confirm);
        editTextPhone = findViewById(R.id.edt_reg_phone);
    }

    private void validasiRegister() {

        editTextUsername.setError(null);
        editTextPassword.setError(null);
        editTextRetypePassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        String userName = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String rePassword = editTextRetypePassword.getText().toString();
        String phoneNumber = editTextPhone.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            editTextUsername.setError("Harus diisi");
            fokus = editTextUsername;
            cancel = true;
        } else if (cekUser(userName)) {
            editTextUsername.setError("Username sudah terdaftar");
            fokus = editTextUsername;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Harus Diisi");
            fokus = editTextPassword;
            cancel = true;
        } else if (!cekPassword(password, rePassword)) {
            editTextPassword.setError("Password yang dimasukkan tidak sesuai");
            fokus = editTextPassword;
            cancel = true;
        }

        if (cancel) {
            fokus.requestFocus();
        } else {

            User user = new User();
            user.setUsername(userName);
            user.setPassword(password);
            user.setPhone(phoneNumber);

            Preferences.setUserPreferences(getBaseContext(), user);
            Preferences.setLoggedInStatus(getBaseContext(), true);

            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }

    }

    private boolean cekUser(String username) {
        return username.equals(Preferences.getRegisteredUser(getBaseContext()));
    }

    private boolean cekPassword(String password, String repassword) {
        return password.equals(repassword);
    }
}
