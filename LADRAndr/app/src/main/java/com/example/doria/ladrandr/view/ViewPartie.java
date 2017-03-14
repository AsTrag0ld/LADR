package com.example.doria.ladrandr.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.doria.ladrandr.R;
import com.example.doria.ladrandr.model.Joueur;
import com.example.doria.ladrandr.model.Partie;

import java.util.ArrayList;


public class ViewPartie extends Activity {
    ArrayList<String> infosJoueurs;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.partie);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            infosJoueurs = extra.getStringArrayList("lesJoueurs");
        }
        ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
        for (int i = 0; i < infosJoueurs.size(); i = i + 2) {
            joueurs.add(new Joueur(infosJoueurs.get(i), infosJoueurs.get(i+1)));
        }
        Partie p = new Partie(joueurs, this);


    }
}
