package com.projet.ladr.controler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.projet.ladr.model.Partie;
import com.projet.ladr.model.Route;
import com.projet.ladr.model.Ville;

import java.util.*;
import java.util.Map;

public class TiledMapClickListener extends ClickListener{

    private ListCoordRoutes tab;
    private ControlerPartie controlerPartie;
    private TiledMapActor actor;

    public TiledMapClickListener(TiledMapActor actor) {
        this.actor = actor;
        controlerPartie = MapLauncher.getControlerPartie();
        this.tab = new ListCoordRoutes(controlerPartie.partie);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {

        int a = (int) actor.getX();
        int b = (int) actor.getY();

        System.out.println(actor.getCell() + " has been clicked.");
        System.out.println("x =  " + a +  "y = " + b );

        Route r = tab.rechercherRoute(a, b);
        System.out.println(r);

        controlerPartie.prendreRoute(r);
    }
}