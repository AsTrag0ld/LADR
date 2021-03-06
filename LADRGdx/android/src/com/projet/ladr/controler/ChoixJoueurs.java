package com.projet.ladr.controler;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.RadioGroup;

import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.projet.ladr.R;



import java.util.ArrayList;


public class ChoixJoueurs extends Activity implements RadioGroup.OnCheckedChangeListener {
    RadioGroup group;
    ArrayAdapter spinnerAdapter;
    ArrayList<Spinner> lesSpinnersJ;
    ArrayList<Spinner> lesSpinnersC;
    TableLayout table;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.choix_joueurs);
        group = (RadioGroup) findViewById(R.id.radioGroupJoueur);
        group.setOnCheckedChangeListener(this);

        table = (TableLayout) findViewById(R.id.idTable);

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getPlayerNamesFromDatabase());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Button button = (Button) findViewById(R.id.btnValiderJoueurs);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> infosJoueur = new ArrayList<String>();
                boolean flagJ = true, flagC = true;
                for (int i = 0; i < lesSpinnersJ.size(); i++) {
                    String nom = lesSpinnersJ.get(i).getSelectedItem().toString();
                    String couleur = lesSpinnersC.get(i).getSelectedItem().toString();
                    if (!infosJoueur.contains(nom)) {
                        infosJoueur.add(nom);
                    } else {
                        flagJ = false;
                    }
                    if (!infosJoueur.contains(couleur)) {
                        infosJoueur.add(couleur);
                    } else {
                        flagC = false;
                    }
                }
                if (!flagJ) {
                    new AlertDialog.Builder(ChoixJoueurs.this).setTitle("Informations").setMessage("Les profils doivent être différents").setCancelable(true)
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                            }).setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else if (!flagC){
                    new AlertDialog.Builder(ChoixJoueurs.this).setTitle("Informations").setMessage("Les couleurs doivent être différents").setCancelable(true)
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                            }).setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else if (flagJ && flagC) {
                    Intent myIntent = new Intent(ChoixJoueurs.this, ControlerPartie.class);
                    myIntent.putStringArrayListExtra("lesJoueurs", infosJoueur);
                    ChoixJoueurs.this.startActivity(myIntent);
                }
            }
        });

        Button button2 = (Button) this.findViewById(R.id.btnCreerProfil);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ChoixJoueurs.this, NouveauJoueur.class);
                ChoixJoueurs.this.startActivity(intent);
            }
        });

    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        table.removeAllViewsInLayout();

        if (group == this.group) {
            if (checkedId == R.id.radioBtn2) {
                String[] col1 = {"Joueur 1", "Joueur 2"};
                ajouterItems(col1);
            } else if (checkedId == R.id.radioBtn3) {
                String[] col1 = {"Joueur 1", "Joueur 2", "Joueur 3"};
                ajouterItems(col1);
            } else if (checkedId == R.id.radioBtn4) {
                String[] col1 = {"Joueur 1", "Joueur 2", "Joueur 3", "Joueur 4"};
                ajouterItems(col1);
            } else if (checkedId == R.id.radioBtn5) {
                String[] col1 = {"Joueur 1", "Joueur 2", "Joueur 3", "Joueur 4", "Joueur 5"};
                ajouterItems(col1);
            }
        }
    }

    private ArrayList<String> getPlayerNamesFromDatabase() {
        ArrayList<String> res = new ArrayList<String>();
        DatabaseHandler dbh = new DatabaseHandler(this);
        String query = "SELECT nom FROM Joueur ORDER BY idJoueur";
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                res.add(new String(c.getString(c.getColumnIndex("nom"))));
            } while (c.moveToNext());
        }
        return res;
    }

    private void ajouterItems(String[] joueurs) {
        TableRow row;
        TextView tv1;
        Spinner spinJ, spinC;
        lesSpinnersJ = new ArrayList<Spinner>();
        lesSpinnersC = new ArrayList<Spinner>();

        for(int i=0;i<joueurs.length;i++) {
            row = new TableRow(this);

            tv1 = new TextView(this);
            tv1.setText(joueurs[i]);
            tv1.setTextSize(20);
            tv1.setTextColor(getResources().getColor(R.color.rouge));
            tv1.setTypeface(null, Typeface.BOLD);
            tv1.setGravity(Gravity.CENTER);
            tv1.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );

            spinJ = new Spinner(this);
            spinJ.setAdapter(spinnerAdapter);
            spinJ.setGravity(Gravity.CENTER);
            spinJ.setBackgroundResource(R.drawable.spinner_bg);
            spinJ.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
            lesSpinnersJ.add(spinJ);

            spinC = new Spinner(this);
            String [] list = {"Rouge", "Bleu", "Jaune", "Vert", "Noir"};
            ArrayAdapter<String> adapterC = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            adapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinC.setAdapter(adapterC);
            spinC.setGravity(Gravity.CENTER);
            spinC.setBackgroundResource(R.drawable.spinner_bg);
            spinC.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
            lesSpinnersC.add(spinC);

            row.addView(tv1);
            row.addView(spinJ);
            row.addView(spinC);

            table.addView(row);
        }
    }


}
