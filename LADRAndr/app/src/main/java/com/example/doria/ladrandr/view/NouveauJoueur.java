package com.example.doria.ladrandr.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.doria.ladrandr.R;
import com.example.doria.ladrandr.controller.DatabaseHandler;
import com.example.doria.ladrandr.controller.MainActivity;

public class NouveauJoueur extends Activity {
    DatabaseHandler dbh;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.nouveau_joueur);
        dbh = new DatabaseHandler(this);

        Button button = (Button) findViewById(R.id.btnCreationJoueur);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText);
                String s = editText.getText().toString();
                dbh.newJoueur(s);

                Intent intent = new Intent(NouveauJoueur.this, ChoixJoueurs.class);
                NouveauJoueur.this.startActivity(intent);
            }
        });
    }
}
