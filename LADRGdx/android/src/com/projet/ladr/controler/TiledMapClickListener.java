package com.projet.ladr.controler;

import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.projet.ladr.model.Partie;
import com.projet.ladr.model.Route;
import com.projet.ladr.model.Ville;

import java.util.*;
import java.util.Map;

public class TiledMapClickListener extends ClickListener{

    private TiledMapActor actor;

    public TiledMapClickListener(TiledMapActor actor) {
        this.actor = actor;
    }


    @Override
    public void clicked(InputEvent event, float x, float y) {
        System.out.println(actor.getCell() + " has been clicked.");
        //Route r = rechercherRoute(x, y);
        //System.out.println(r);
        //controlerPartie.prendreRoute(r);
    }
}