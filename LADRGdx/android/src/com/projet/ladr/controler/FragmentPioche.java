package com.projet.ladr.controler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.projet.ladr.R;

public class FragmentPioche extends Fragment {
    View myView;
    ImageView piocheVisible1, piocheVisible2, piocheVisible3, piocheVisible4, piocheVisible5, piocheCachee, piocheDestination;
    ControlerPartie contPartie;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.pioche, container, false);
        contPartie = (ControlerPartie) getActivity();

        piocheVisible1 = (ImageView) myView.findViewById(R.id.imgPiocheVisible1);
        piocheVisible1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(">>>>>>>>>>>>>>Dans le 1");
                contPartie.piocherCarteVisible(0);
            }
        });
        String image1 = contPartie.partie.getPiocheWagon().getCartesVisibles().get(0).getCouleur();
        System.out.println(image1);
        if (image1.equals("rouge")) {
            piocheVisible1.setImageResource(R.drawable.carte_wagon_rouge);
        } else if (image1.equals("orange")) {
            piocheVisible1.setImageResource(R.drawable.carte_wagon_orange);
        } else if (image1.equals("bleu")) {
            piocheVisible1.setImageResource(R.drawable.carte_wagon_bleu);
        } else if (image1.equals("vert")) {
            piocheVisible1.setImageResource(R.drawable.carte_wagon_vert);
        } else if (image1.equals("violet")) {
            piocheVisible1.setImageResource(R.drawable.carte_wagon_violet);
        } else if (image1.equals("jaune")) {
            piocheVisible1.setImageResource(R.drawable.carte_wagon_jaune);
        } else if (image1.equals("noir")) {
            piocheVisible1.setImageResource(R.drawable.carte_wagon_noir);
        } else if (image1.equals("blanc")) {
            piocheVisible1.setImageResource(R.drawable.carte_wagon_blanc);
        } else if (image1.equals("locomotive")) {
            piocheVisible1.setImageResource(R.drawable.carte_wagon_loco);
        }

        piocheVisible2 = (ImageView) myView.findViewById(R.id.imgPiocheVisible2);
        piocheVisible2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(">>>>>>>>>>>>>>Dans le 2");
                contPartie.piocherCarteVisible(1);
            }
        });
        String image2 = contPartie.partie.getPiocheWagon().getCartesVisibles().get(1).getCouleur();
        System.out.println(image2);
        if (image2.equals("rouge")) {
            piocheVisible2.setImageResource(R.drawable.carte_wagon_rouge);
        } else if (image2.equals("orange")) {
            piocheVisible2.setImageResource(R.drawable.carte_wagon_orange);
        } else if (image2.equals("bleu")) {
            piocheVisible2.setImageResource(R.drawable.carte_wagon_bleu);
        } else if (image2.equals("vert")) {
            piocheVisible2.setImageResource(R.drawable.carte_wagon_vert);
        } else if (image2.equals("violet")) {
            piocheVisible2.setImageResource(R.drawable.carte_wagon_violet);
        } else if (image2.equals("jaune")) {
            piocheVisible2.setImageResource(R.drawable.carte_wagon_jaune);
        } else if (image2.equals("noir")) {
            piocheVisible2.setImageResource(R.drawable.carte_wagon_noir);
        } else if (image2.equals("blanc")) {
            piocheVisible2.setImageResource(R.drawable.carte_wagon_blanc);
        } else if (image2.equals("locomotive")) {
            piocheVisible2.setImageResource(R.drawable.carte_wagon_loco);
        }

        piocheVisible3 = (ImageView) myView.findViewById(R.id.imgPiocheVisible3);
        piocheVisible3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(">>>>>>>>>>>>>>Dans le 3");
                contPartie.piocherCarteVisible(2);
            }
        });
        String image3 = contPartie.partie.getPiocheWagon().getCartesVisibles().get(2).getCouleur();
        System.out.println(image3);
        if (image3.equals("rouge")) {
            piocheVisible3.setImageResource(R.drawable.carte_wagon_rouge);
        } else if (image3.equals("orange")) {
            piocheVisible3.setImageResource(R.drawable.carte_wagon_orange);
        } else if (image3.equals("bleu")) {
            piocheVisible3.setImageResource(R.drawable.carte_wagon_bleu);
        } else if (image3.equals("vert")) {
            piocheVisible3.setImageResource(R.drawable.carte_wagon_vert);
        } else if (image3.equals("violet")) {
            piocheVisible3.setImageResource(R.drawable.carte_wagon_violet);
        } else if (image3.equals("jaune")) {
            piocheVisible3.setImageResource(R.drawable.carte_wagon_jaune);
        } else if (image3.equals("noir")) {
            piocheVisible3.setImageResource(R.drawable.carte_wagon_noir);
        } else if (image3.equals("blanc")) {
            piocheVisible3.setImageResource(R.drawable.carte_wagon_blanc);
        } else if (image3.equals("locomotive")) {
            piocheVisible3.setImageResource(R.drawable.carte_wagon_loco);
        }

        piocheVisible4 = (ImageView) myView.findViewById(R.id.imgPiocheVisible4);
        piocheVisible4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(">>>>>>>>>>>>>>Dans le 4");
                contPartie.piocherCarteVisible(3);
            }
        });
        String image4 = contPartie.partie.getPiocheWagon().getCartesVisibles().get(3).getCouleur();
        System.out.println(image4);
        if (image4.equals("rouge")) {
            piocheVisible4.setImageResource(R.drawable.carte_wagon_rouge);
        } else if (image4.equals("orange")) {
            piocheVisible4.setImageResource(R.drawable.carte_wagon_orange);
        } else if (image4.equals("bleu")) {
            piocheVisible4.setImageResource(R.drawable.carte_wagon_bleu);
        } else if (image4.equals("vert")) {
            piocheVisible4.setImageResource(R.drawable.carte_wagon_vert);
        } else if (image4.equals("violet")) {
            piocheVisible4.setImageResource(R.drawable.carte_wagon_violet);
        } else if (image4.equals("jaune")) {
            piocheVisible4.setImageResource(R.drawable.carte_wagon_jaune);
        } else if (image4.equals("noir")) {
            piocheVisible4.setImageResource(R.drawable.carte_wagon_noir);
        } else if (image4.equals("blanc")) {
            piocheVisible4.setImageResource(R.drawable.carte_wagon_blanc);
        } else if (image4.equals("locomotive")) {
            piocheVisible4.setImageResource(R.drawable.carte_wagon_loco);
        }

        piocheVisible5 = (ImageView) myView.findViewById(R.id.imgPiocheVisible5);
        piocheVisible5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(">>>>>>>>>>>>>>Dans le 5");
                contPartie.piocherCarteVisible(4);
            }
        });
        String image5 = contPartie.partie.getPiocheWagon().getCartesVisibles().get(4).getCouleur();
        System.out.println(image5);
        if (image5.equals("rouge")) {
            piocheVisible5.setImageResource(R.drawable.carte_wagon_rouge);
        } else if (image5.equals("orange")) {
            piocheVisible5.setImageResource(R.drawable.carte_wagon_orange);
        } else if (image5.equals("bleu")) {
            piocheVisible5.setImageResource(R.drawable.carte_wagon_bleu);
        } else if (image5.equals("vert")) {
            piocheVisible5.setImageResource(R.drawable.carte_wagon_vert);
        } else if (image5.equals("violet")) {
            piocheVisible5.setImageResource(R.drawable.carte_wagon_violet);
        } else if (image5.equals("jaune")) {
            piocheVisible5.setImageResource(R.drawable.carte_wagon_jaune);
        } else if (image5.equals("noir")) {
            piocheVisible5.setImageResource(R.drawable.carte_wagon_noir);
        } else if (image5.equals("blanc")) {
            piocheVisible5.setImageResource(R.drawable.carte_wagon_blanc);
        } else if (image5.equals("locomotive")) {
            piocheVisible5.setImageResource(R.drawable.carte_wagon_loco);
        }

        piocheCachee = (ImageView) myView.findViewById(R.id.imgPiocheCachee);
        piocheCachee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contPartie.piocherCarteCachee();
            }
        });

        piocheDestination = (ImageView) myView.findViewById(R.id.imgPiocheDestination);
        piocheDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contPartie.piocherCarteDestination();
            }
        });

        return myView;
    }
}
