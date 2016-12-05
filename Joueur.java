package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Joueur extends Observable {
    private String nom;
    private String couleur;
    private int score;
    private int nbWagons;
    private List<Wagon> wagon = new ArrayList<Wagon> ();
    private PiocheWagon piocheWagon;
    private PiocheDestination piocheDestination;
    private DefausseWagon defausseWagon;

    public Joueur() {
    	
    }
  
    public void poserWagon() {
    }

    public void passerSonTour() {
    }

    public void modifierOptions() {
    }

    public void consulterRegles() {
    }

    public void piocher() {
    }

    public void consulterStatistiques() {
    }

}
