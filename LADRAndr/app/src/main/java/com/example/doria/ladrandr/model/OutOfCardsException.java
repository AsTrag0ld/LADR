package com.example.doria.ladrandr.model;

public class OutOfCardsException extends Exception {

	public String toString(){
		return "Le nombre de cartes est insuffisant";
	}
}