package com.projet.ladr.controler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projet.ladr.R;
import com.projet.ladr.model.CarteDestination;
import com.projet.ladr.model.CarteWagon;
import com.projet.ladr.model.Joueur;
import com.projet.ladr.model.OutOfCardsException;
import com.projet.ladr.model.Partie;
import com.projet.ladr.model.PiocheDestination;
import com.projet.ladr.model.PiocheWagon;
import com.projet.ladr.model.Ville;
import com.projet.ladr.model.Wagon;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;


public class ControlerPartie extends Activity {
    ArrayList<String> infosJoueurs;
    Partie partie;
    String [] listItems;
    boolean [] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    ArrayList<CarteDestination> cartesPiochees = new ArrayList<>();
    TextView temps;
    Joueur joueurTour;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.partie);

        temps = (TextView) findViewById(R.id.textView3);

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
        joueurTour = partie.getJoueurs().get(0);

        Button btnPiocheVisible = (Button) findViewById(R.id.btnPiocheVisible);
        btnPiocheVisible.setOnClickListener(new View.OnClickListener() {
            int nbCartes = 0;
            @Override
            public void onClick(View v) {
                if (nbCartes < 2) {
                    try {
                        Log.d("Main avant : " , ""+joueurTour.getCartesWagon());
                        joueurTour.piocherWagonVisible(1, partie.getPiocheWagon(), partie.getDefausseWagon());
                        Log.d("Main apres : " , ""+joueurTour.getCartesWagon());
                        nbCartes++;
                        if (nbCartes == 2) {
                            nbCartes = 0;
                            changerTour();
                        }
                    } catch (OutOfCardsException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Button btnPiocheAveugle = (Button) findViewById(R.id.btnPiocheAveugle);
        btnPiocheAveugle.setOnClickListener(new View.OnClickListener() {
            int nbCartes = 0;
            @Override
            public void onClick(View v) {
                if (nbCartes < 2) {
                    try {
                        Log.d("Main avant : " , ""+joueurTour.getCartesWagon());
                        joueurTour.piocherWagonAveugle(partie.getPiocheWagon(), partie.getDefausseWagon());
                        Log.d("Main apres : " , ""+joueurTour.getCartesWagon());
                        nbCartes++;
                        if (nbCartes == 2) {
                            nbCartes = 0;
                            changerTour();
                        }
                    } catch (OutOfCardsException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Button btnPiocheDestination = (Button) findViewById(R.id.btnPiocheDestination);
        btnPiocheDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                piocherCartesDestination(joueurTour, 1);
            }
        });

        LinearLayout routes = (LinearLayout) findViewById(R.id.llRoutes);

    }

    /*
     * Initialise toutes les composantes d'une partie
     */
    public void initialiserPartie() {
        distribuerCartesDestination();
        initialiserMap();
        partie.distribuerCartesWagon();
        partie.distribuerWagons();
        partie.setEnCours(true);
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
     * Distribue � chaque joueur au moins 2 cartes destinations parmi 3
     */
    public void distribuerCartesDestination() {
        initialiserCartesDestination();										//On cr�e les cartes Destination depuis la BDD
        partie.getPiocheDestination().melanger();									//On m�lange le paquet
        List<Joueur> lesJoueurs = partie.getJoueurs();
        for (Joueur j : lesJoueurs) {
            fenetreDistribution(j, 2);
            try {
                Looper.getMainLooper().loop();
            } catch (RuntimeException e2) {
            }
        }
    }

    public void fenetreDistribution(final Joueur joueur, final int limite)  {
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
                if (mCartes.size() > limite) {
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

                if (mCartes.size() < limite ) {
                    for (int i = cartesPiochees.size()-1; i >= 0; i--) {
                        partie.getPiocheDestination().getCarteDestination().addFirst(cartesPiochees.get(i));					//On remet chaque carte dans du paquet pour recommencer la méthode de pioche
                    }
                    cartesPiochees = new ArrayList<CarteDestination>();
                    fenetreDistribution(joueur, limite);
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

    public void piocherCartesDestination(Joueur j, int limite) {
        fenetreDistribution(j, limite);
        try {
            Looper.getMainLooper().loop();
        } catch (RuntimeException e2) {
            changerTour();
        }
    }

    public void changerTour() {
        int i = partie.getJoueurs().indexOf(joueurTour);
        if (i != partie.getJoueurs().size()-1) {
            Joueur j = partie.getJoueurs().get(i+1);
            joueurTour = j;
        } else {
           joueurTour = partie.getJoueurs().get(0);
        }
        new AlertDialog.Builder(ControlerPartie.this)
                .setTitle("Informations")
                .setMessage("C'est à " + joueurTour.getNom() + " de jouer !")
                .setCancelable(true)
                .setNegativeButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
