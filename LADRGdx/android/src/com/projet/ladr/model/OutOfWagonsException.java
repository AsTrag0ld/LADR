package com.projet.ladr.model;

public class OutOfWagonsException extends Exception {

    public String toString(){
        return "Le nombre de wagons est insuffisant";
    }
}