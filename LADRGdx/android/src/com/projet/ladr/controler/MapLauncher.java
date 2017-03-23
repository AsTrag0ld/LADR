package com.projet.ladr.controler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;


public class MapLauncher extends AndroidFragmentApplication {
    static ControlerPartie test;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ControlerPartie cont = (ControlerPartie) getActivity();
        test = cont;
        return initializeForView(new Map());
    }

    public static ControlerPartie getControlerPartie() {
        return test;
    }

    @Override
    public void exit() {

    }
}