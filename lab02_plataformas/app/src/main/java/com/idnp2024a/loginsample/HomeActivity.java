package com.idnp2024a.loginsample;

import android.os.Bundle;
import android.widget.TextView; // Importa TextView
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvWelcome = findViewById(R.id.tvWelcome);

        // Recibir el nombre de usuario del Intent
        String username = getIntent().getStringExtra("USERNAME");
        if (username != null) {
            tvWelcome.setText("Bienvenido " + username); // Se muestra el mensaje
        }
    }
}

