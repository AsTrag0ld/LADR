package com.projet.ladr.controler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.projet.ladr.R;
import com.projet.ladr.model.CarteDestination;
import com.projet.ladr.model.CarteWagon;
import com.projet.ladr.model.Joueur;
import com.projet.ladr.model.OutOfCardsException;
import com.projet.ladr.model.Partie;
import com.projet.ladr.model.PiocheDestination;
import com.projet.ladr.model.Route;
import com.projet.ladr.model.Ville;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class ControlerPartie extends FragmentActivity implements AndroidFragmentApplication.Callbacks {
    ArrayList<String> infosJoueurs;
    Partie partie;
    String [] listItems;
    boolean [] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    ArrayList<CarteDestination> cartesPiochees = new ArrayList<>();
    TextView temps;
    Joueur joueurTour;
    int nbCartes;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        initialiserActivity();
        setContentView(R.layout.partie_bis);
        changerTour();

        MapLauncher fragment = new MapLauncher();
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.flMap, fragment);
        trans.commit();
    }

    private void initialiserActivity() {
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
        joueurTour = partie.getJoueurs().get(joueurs.size()-1);
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
     * Initialise le plateau de jeu
     */
    public void initialiserMap() {
        partie.getMap().initialiserVilles(this);
        partie.getMap().initialiserRoutes(this);
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

    public void changerTour() {
        this.nbCartes = 0;
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
        refreshFragmentGauche();
        refreshMain();
    }

    private void refreshFragmentGauche() {
        TextView score = (TextView) findViewById(R.id.tvScore);
        score.setText("Score : " + joueurTour.getScore());

        TextView wagons = (TextView) findViewById(R.id.tvWagonsRestants);
        wagons.setText("Wagons restants : " + joueurTour.getNbWagons());
    }

    private void refreshMain() {
        TextView blanc = (TextView) findViewById(R.id.tvCarteBlanche);
        TextView rouge = (TextView) findViewById(R.id.tvCarteRouge);
        TextView bleu = (TextView) findViewById(R.id.tvCarteBleue);
        TextView vert = (TextView) findViewById(R.id.tvCarteVerte);
        TextView jaune = (TextView) findViewById(R.id.tvCarteJaune);
        TextView violet = (TextView) findViewById(R.id.tvCarteViolette);
        TextView noir = (TextView) findViewById(R.id.tvCarteNoire);
        TextView orange = (TextView) findViewById(R.id.tvCarteOrange);
        TextView loco = (TextView) findViewById(R.id.tvCarteLoco);

        LinkedList<CarteWagon> cartes = joueurTour.getCartesWagon();
        int cmpRouge = 0, cmpBleu=0, cmpBlanc=0, cmpVert=0, cmpNoir=0, cmpOrange=0, cmpViolet=0, cmpJaune=0, cmpLoco=0;
        for (CarteWagon c : cartes) {
            if (c.getCouleur().equals("rouge")) {
                cmpRouge++;
            } else if (c.getCouleur().equals("bleu")) {
                cmpBleu++;
            } else if (c.getCouleur().equals("vert")) {
                cmpVert++;
            } else if (c.getCouleur().equals("blanc")) {
                cmpBlanc++;
            } else if (c.getCouleur().equals("violet")) {
                cmpViolet++;
            } else if (c.getCouleur().equals("jaune")) {
                cmpJaune++;
            } else if (c.getCouleur().equals("noir")) {
                cmpNoir++;
            } else if (c.getCouleur().equals("orange")) {
                cmpOrange++;
            } else if (c.getCouleur().equals("locomotive")) {
                cmpLoco++;
            }

            blanc.setText("x"+ cmpBlanc);
            rouge.setText("x"+cmpRouge);
            bleu.setText("x"+cmpBleu);
            vert.setText("x"+cmpVert);
            orange.setText("x"+cmpOrange);
            jaune.setText("x"+cmpJaune);
            noir.setText("x"+cmpNoir);
            violet.setText("x"+cmpViolet);
            loco.setText("x"+cmpLoco);
        }


    }

    private void refreshPiocheVisible(int indice) {
        if (indice == 0) {
            ImageView img1 = (ImageView) findViewById(R.id.imgPiocheVisible1);
            String image1 = partie.getPiocheWagon().getCartesVisibles().get(0).getCouleur();
            if (image1.equals("rouge")) {
                img1.setImageResource(R.drawable.carte_wagon_rouge);
            } else if (image1.equals("orange")) {
                img1.setImageResource(R.drawable.carte_wagon_orange);
            } else if (image1.equals("bleu")) {
                img1.setImageResource(R.drawable.carte_wagon_bleu);
            } else if (image1.equals("vert")) {
                img1.setImageResource(R.drawable.carte_wagon_vert);
            } else if (image1.equals("violet")) {
                img1.setImageResource(R.drawable.carte_wagon_violet);
            } else if (image1.equals("jaune")) {
                img1.setImageResource(R.drawable.carte_wagon_jaune);
            } else if (image1.equals("noir")) {
                img1.setImageResource(R.drawable.carte_wagon_noir);
            } else if (image1.equals("blanc")) {
                img1.setImageResource(R.drawable.carte_wagon_blanc);
            } else if (image1.equals("locomotive")) {
                img1.setImageResource(R.drawable.carte_wagon_loco);
            }
        } else if (indice == 1) {
            ImageView img2 = (ImageView) findViewById(R.id.imgPiocheVisible2);
            String image2 = partie.getPiocheWagon().getCartesVisibles().get(1).getCouleur();
            if (image2.equals("rouge")) {
                img2.setImageResource(R.drawable.carte_wagon_rouge);
            } else if (image2.equals("orange")) {
                img2.setImageResource(R.drawable.carte_wagon_orange);
            } else if (image2.equals("bleu")) {
                img2.setImageResource(R.drawable.carte_wagon_bleu);
            } else if (image2.equals("vert")) {
                img2.setImageResource(R.drawable.carte_wagon_vert);
            } else if (image2.equals("violet")) {
                img2.setImageResource(R.drawable.carte_wagon_violet);
            } else if (image2.equals("jaune")) {
                img2.setImageResource(R.drawable.carte_wagon_jaune);
            } else if (image2.equals("noir")) {
                img2.setImageResource(R.drawable.carte_wagon_noir);
            } else if (image2.equals("blanc")) {
                img2.setImageResource(R.drawable.carte_wagon_blanc);
            } else if (image2.equals("locomotive")) {
                img2.setImageResource(R.drawable.carte_wagon_loco);
            }
        } else if (indice == 2) {
            ImageView img3 = (ImageView) findViewById(R.id.imgPiocheVisible3);
            String image3 = partie.getPiocheWagon().getCartesVisibles().get(2).getCouleur();
            if (image3.equals("rouge")) {
                img3.setImageResource(R.drawable.carte_wagon_rouge);
            } else if (image3.equals("orange")) {
                img3.setImageResource(R.drawable.carte_wagon_orange);
            } else if (image3.equals("bleu")) {
                img3.setImageResource(R.drawable.carte_wagon_bleu);
            } else if (image3.equals("vert")) {
                img3.setImageResource(R.drawable.carte_wagon_vert);
            } else if (image3.equals("violet")) {
                img3.setImageResource(R.drawable.carte_wagon_violet);
            } else if (image3.equals("jaune")) {
                img3.setImageResource(R.drawable.carte_wagon_jaune);
            } else if (image3.equals("noir")) {
                img3.setImageResource(R.drawable.carte_wagon_noir);
            } else if (image3.equals("blanc")) {
                img3.setImageResource(R.drawable.carte_wagon_blanc);
            } else if (image3.equals("locomotive")) {
                img3.setImageResource(R.drawable.carte_wagon_loco);
            }
        } else if (indice == 3) {
            ImageView img4 = (ImageView) findViewById(R.id.imgPiocheVisible4);
            String image4 = partie.getPiocheWagon().getCartesVisibles().get(3).getCouleur();
            if (image4.equals("rouge")) {
                img4.setImageResource(R.drawable.carte_wagon_rouge);
            } else if (image4.equals("orange")) {
                img4.setImageResource(R.drawable.carte_wagon_orange);
            } else if (image4.equals("bleu")) {
                img4.setImageResource(R.drawable.carte_wagon_bleu);
            } else if (image4.equals("vert")) {
                img4.setImageResource(R.drawable.carte_wagon_vert);
            } else if (image4.equals("violet")) {
                img4.setImageResource(R.drawable.carte_wagon_violet);
            } else if (image4.equals("jaune")) {
                img4.setImageResource(R.drawable.carte_wagon_jaune);
            } else if (image4.equals("noir")) {
                img4.setImageResource(R.drawable.carte_wagon_noir);
            } else if (image4.equals("blanc")) {
                img4.setImageResource(R.drawable.carte_wagon_blanc);
            } else if (image4.equals("locomotive")) {
                img4.setImageResource(R.drawable.carte_wagon_loco);
            }
        } else if (indice == 4) {
            ImageView img5 = (ImageView) findViewById(R.id.imgPiocheVisible5);
            String image5 = partie.getPiocheWagon().getCartesVisibles().get(4).getCouleur();
            if (image5.equals("rouge")) {
                img5.setImageResource(R.drawable.carte_wagon_rouge);
            } else if (image5.equals("orange")) {
                img5.setImageResource(R.drawable.carte_wagon_orange);
            } else if (image5.equals("bleu")) {
                img5.setImageResource(R.drawable.carte_wagon_bleu);
            } else if (image5.equals("vert")) {
                img5.setImageResource(R.drawable.carte_wagon_vert);
            } else if (image5.equals("violet")) {
                img5.setImageResource(R.drawable.carte_wagon_violet);
            } else if (image5.equals("jaune")) {
                img5.setImageResource(R.drawable.carte_wagon_jaune);
            } else if (image5.equals("noir")) {
                img5.setImageResource(R.drawable.carte_wagon_noir);
            } else if (image5.equals("blanc")) {
                img5.setImageResource(R.drawable.carte_wagon_blanc);
            } else if (image5.equals("locomotive")) {
                img5.setImageResource(R.drawable.carte_wagon_loco);
            }
        }
    }

    /*
    public ListView afficherRoutesDisponibles() {
        LinearLayout routes = (LinearLayout) findViewById(R.id.llRoutes);
        RouteAdapter adapter = new RouteAdapter(getApplicationContext());
        adapter.setRoutesList(partie.getMap().getRoutesDisponibles());
        ListView listView = (ListView) findViewById(R.id.lvRoutes);
        listView.setAdapter(adapter);
        return listView;
    }
    */

    public void prendreRoute(Route r) {
        boolean flag = true;
        try {
            joueurTour.prendreRoute(r);
        } catch (OutOfCardsException e) {
            flag = false;
            new AlertDialog.Builder(ControlerPartie.this)
                    .setTitle("Informations")
                    .setMessage("Vous n'avez pas assez de cartes " + r.getCouleur() + " pour prendre cette route")
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
        if (flag) {
            changerTour();
        }
    }

    public void piocherCarteVisible(int indice) {
        if (nbCartes < 2) {
            try {
                joueurTour.piocherWagonVisible(indice, partie.getPiocheWagon(), partie.getDefausseWagon());
                if (joueurTour.getCartesWagon().getLast().getCouleur().equals("locomotive")) {
                    nbCartes = nbCartes + 2;
                } else {
                    nbCartes++;
                }
                refreshPiocheVisible(indice);
                refreshMain();
                if (nbCartes >= 2) {
                    nbCartes = 0;
                    changerTour();
                }
            } catch (OutOfCardsException e) {
                e.printStackTrace();
            }
        }
    }

    public void piocherCarteCachee() {
        if (nbCartes < 2) {
            try {
                joueurTour.piocherWagonAveugle(partie.getPiocheWagon(), partie.getDefausseWagon());
                nbCartes++;
                refreshMain();
                if (nbCartes == 2) {
                    nbCartes = 0;
                    changerTour();
                }
            } catch (OutOfCardsException e) {
                e.printStackTrace();
            }
        }
    }

    public void piocherCarteDestination() {
        try {
            fenetreDistribution(joueurTour, 1);
        } catch (RuntimeException e) {
            e.printStackTrace();
            changerTour();
        }

    }

    public void afficherRoutesPossedees() {
        RouteAdapter adapter = new RouteAdapter(getApplicationContext());
        adapter.setRoutesList(joueurTour.getRoutesPrises());
        ListView listView = (ListView) findViewById(R.id.lvRoutes);
        listView.setAdapter(adapter);

        new AlertDialog.Builder(ControlerPartie.this)
                .setTitle("Vos routes")
                .setView(R.layout.liste_routes)
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
    @Override
    public void exit() {

    }
}