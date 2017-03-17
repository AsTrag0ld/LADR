package com.projet.ladr.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.projet.ladr.R;
import com.projet.ladr.model.CarteDestination;
import com.projet.ladr.model.CarteWagon;
import com.projet.ladr.model.Joueur;
import com.projet.ladr.model.Partie;
import com.projet.ladr.model.PiocheDestination;
import com.projet.ladr.model.PiocheWagon;
import com.projet.ladr.model.Ville;
import com.projet.ladr.model.Wagon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class InitialisationPartie extends Activity {
    ArrayList<String> infosJoueurs;
    Partie partie;
    String [] listItems;
    boolean [] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    ArrayList<CarteDestination> cartesPiochees = new ArrayList<>();

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.initialisation_partie);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            infosJoueurs = extra.getStringArrayList("lesJoueurs");
        }
        ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
        for (int i = 0; i < infosJoueurs.size(); i = i + 2) {
            joueurs.add(new Joueur(infosJoueurs.get(i), infosJoueurs.get(i+1)));
        }
        partie = new Partie(joueurs);
        listItems = new String[3];
        checkedItems = new boolean[3];
        initialiserPartie();
    }

    /*
     * Initialise toutes les composantes d'une partie
     */
    public void initialiserPartie() {
        distribuerCartesWagon();
        distribuerWagons();
        distribuerCartesDestination();
        initialiserMap();
        partie.setEnCours(true);
        TextView tv = (TextView) findViewById(R.id.tvInitialisation);
        tv.setText("Partie initialisée !");
    }

    /*
     * Cr�e les 110 cartes wagons (12 par couleur + 14 locomotives)
     */
    private void initialiserCartesWagon(String[] couleurs) {
        LinkedList<CarteWagon> cartesWagon = new LinkedList<CarteWagon>();
        for (String s : couleurs) {											//Pour chaque couleur pr�sente dans le jeu
            for (int j = 0; j < 12; j++) {
                cartesWagon.add(new CarteWagon(s));							//On cr�e 12 cartes wagon de cette couleur
            }
        }
        for (int i = 0; i < 14; i++) {										//Ensuite on cr�e 14 cartes locomotives
            cartesWagon.add(new CarteWagon("Locomotive"));
        }
        partie.setPiocheWagon(PiocheWagon.getInstance(cartesWagon));
        Collections.shuffle(partie.getPiocheWagon().getPioche());
        partie.getPiocheWagon().preparerPiocheVisible(partie.getDefausseWagon());			//On sort 5 cartes du paquets pour en faire la pioche visible
    }


    /*
     * R�cup�re et cr�e les 30 cartes Destination depuis la base de donn�es
     */
    public void initialiserCartesDestination() {
        LinkedList<CarteDestination> cartesDestination = new LinkedList<CarteDestination>();
        DatabaseHandler db = new DatabaseHandler(this);
        cartesDestination = db.getAllCartesDestination();
        partie.setPiocheDestination(PiocheDestination.getInstance(cartesDestination));
    }

    /*
     * Cr�e 45 wagons pour la couleur pass�e en argument
     */
    LinkedList<Wagon> initialiserWagons(String couleur) {
        LinkedList<Wagon> lesWagons = new LinkedList<Wagon>();
        for (int j = 0; j < 45; j++) {
            lesWagons.add(new Wagon(couleur));
        }
        return lesWagons;
    }

    /*
     * Distribue 4 cartes wagon � chaque joueur apr�s avoir m�langer le paquet
     */
    public void distribuerCartesWagon() {
        initialiserCartesWagon(partie.getCouleurs());
        partie.getPiocheWagon().melanger();
        for (Joueur j : partie.getJoueurs()) {
            j.setCartesWagon(partie.getPiocheWagon().distribuer());
        }
    }

    /*
     * Distribue � chaque joueur les wagons correspondants � sa couleur
     */
    public void distribuerWagons() {
        for (Joueur j : partie.getJoueurs()) {										//Pour chaque joueur
            j.setWagons(initialiserWagons(j.getCouleur()));					//On cr�e et on lui distibue 45 wagons de sa couleur
        }
    }

    /*
     * Distribue � chaque joueur au moins 2 cartes destinations parmi 3
     */
    public void distribuerCartesDestination() {
        initialiserCartesDestination();										//On cr�e les cartes Destination depuis la BDD
        partie.getPiocheDestination().melanger();									//On m�lange le paquet
        List<Joueur> lesJoueurs = partie.getJoueurs();
        for (Joueur j : partie.getJoueurs()) {
            fenetreDistribution(j);
            try {
                Looper.getMainLooper().loop();
            } catch (RuntimeException e2) {
            }
        }
    }

    public void fenetreDistribution(final Joueur joueur)  {
        cartesPiochees = partie.getPiocheDestination().retirerTroisPremieres();
        for (int i = 0; i < 3; i++) {
            listItems[i] = cartesPiochees.get(i).toString();
        }
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle("Choix des cartes destination pour " + joueur.getNom());
        mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    if (!mUserItems.contains(which)) {
                        mUserItems.add(which);
                    }
                } else if (mUserItems.contains(which)) {
                    mUserItems.remove(which);
                }
            }
        });
        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LinkedList<CarteDestination> mCartes = new LinkedList<CarteDestination>();
                for (int i = 0; i < mUserItems.size(); i++) {
                    String item = "";
                    item += listItems[mUserItems.get(i)];
                    String[] parts = item.split("-");
                    mCartes.add(new CarteDestination(Integer.parseInt(parts[0]), new Ville(parts[1]), new Ville(parts[2])));
                }
                if (mCartes.size() > 2) {
                    for (CarteDestination c : cartesPiochees) {
                        if (!mCartes.contains(c)) {
                            partie.getPiocheDestination().getCarteDestination().addLast(c);
                        }
                    }
                    joueur.setCartesDestination(mCartes);
                    cartesPiochees = new ArrayList<CarteDestination>();
                }

                listItems = new String[3];
                checkedItems = new boolean[3];
                mUserItems = new ArrayList<Integer>();

                if (mCartes.size() < 2 ) {
                    for (int i = cartesPiochees.size()-1; i >= 0; i--) {
                        partie.getPiocheDestination().getCarteDestination().addFirst(cartesPiochees.get(i));					//On remet chaque carte dans du paquet pour recommencer la méthode de pioche
                    }
                    cartesPiochees = new ArrayList<CarteDestination>();
                    fenetreDistribution(joueur);
                } else {
                    throw new RuntimeException();
                }

            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    /*
     * Initialise le plateau de jeu
     */
    public void initialiserMap() {
        partie.getMap().initialiserVilles(this);
        partie.getMap().initialiserRoutes(this);
    }
}
