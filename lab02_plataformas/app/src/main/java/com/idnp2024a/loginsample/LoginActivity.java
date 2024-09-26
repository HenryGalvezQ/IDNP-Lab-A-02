package com.idnp2024a.loginsample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.idnp2024a.loginsample.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        EditText edtUsername = binding.edtUsername;
        EditText edtPassword = binding.edtPassword;
        Button btnLogin = binding.btnLogin;
        Button btnAddAccount = binding.btnAddAccount;

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                if (checkLogin(username, password)) {
                    //mensaje de bienvenida
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("USERNAME", username); // Pasar el nombre de usuario
                    startActivity(intent);
                    finish(); // Termina MainActivity
                } else {
                    Toast.makeText(getApplicationContext(), "Cuenta no encontrada", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Cuenta no encontrada");
                }
            }
        });

        btnAddAccount.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),AccountActivity.class);
            startActivity(intent);
        });

    }

    // Método para leer el archivo y verificar las credenciales
    private boolean checkLogin(String username, String password) {
        try {
            InputStream is = getAssets().open("cuentas.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            Log.e(TAG, "Cuenta no encontrada", e);
        }
        return false;
    }

}
