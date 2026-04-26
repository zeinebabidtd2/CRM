package com.example.crm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class add_user extends AppCompatActivity {

    private TextInputEditText etNom, etEmail;
    private Spinner spRole;
    private SwitchMaterial swActif;
    private MaterialButton btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);

        // 1. Initialisation des composants avec les bons IDs du XML
        etNom = findViewById(R.id.etNom);
        etEmail = findViewById(R.id.etEmail);
        spRole = findViewById(R.id.spRole);
        swActif = findViewById(R.id.swActif);
        btnSave = findViewById(R.id.btnSave);

       //liste de role
        String[] roles = {"Client", "Manager", "Admin", "Support"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 
                android.R.layout.simple_spinner_dropdown_item, roles);
        spRole.setAdapter(adapter);

        //  les  controle de saisir
        if (btnSave != null) {
            btnSave.setOnClickListener(v -> {
                String nom = etNom.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String role = spRole.getSelectedItem().toString();

                if (nom.isEmpty()) {
                    etNom.setError("Le nom est obligatoire");
                    etNom.requestFocus();
                    return;
                }

                if (email.isEmpty()) {
                    etEmail.setError("L'email est obligatoire");
                    etEmail.requestFocus();
                    return;
                }


                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmail.setError("Format email invalide (ex: nom@mail.com)");
                    etEmail.requestFocus();
                    return;
                }

                // Préparation des données pour la page liste_user
                String userData = nom + " (" + role + ")";

                // Redirection vers liste_user
                Intent intent = new Intent(add_user.this, liste_user.class);
                intent.putExtra("name", userData);
                startActivity(intent);

                Toast.makeText(this, "Utilisateur ajouté !", Toast.LENGTH_SHORT).show();
                finish();
            });
        }
    }
}