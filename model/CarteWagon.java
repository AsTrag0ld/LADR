package com.example.doria.ladrandr.model;

public class CarteWagon {
    private String couleur;

    public CarteWagon() {
    	this.couleur = "couleur";
    }
    
    public CarteWagon(String s) {
    	this.couleur = s;
    }

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	@Override
	public String toString() {
		return "CarteWagon : " + couleur;
	}
   
    
}
