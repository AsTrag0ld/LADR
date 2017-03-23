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
    private Hashtable tableCoord;
    private ControlerPartie controlerPartie;

    public TiledMapClickListener(TiledMapActor actor) {
        this.actor = actor;
        this.tableCoord = new Hashtable();
        this.controlerPartie = MapLauncher.getControlerPartie();
        System.out.println("C'EST ICI >>>>>>>" + controlerPartie);
        remplirTable();
    }

    private ArrayList<int[]> construireListeRoute(int[] coordonnees) {
        ArrayList<int[]> res = new ArrayList<>();
        for (int i = 0; i < coordonnees.length; i = i + 2) {
            int[] tmp = new int[2];
            tmp[0] = i;
            tmp[1] = i + 1;
            res.add(tmp);
        }
        return res;
    }

    private void remplirTable() {
        int[] coordonnees = {34,23,34,24,33,25,34,25,32,26,33,26,30,27,31,27,29,28,30,28,28,29,29,29,30,29,28,30,28,30};
        ArrayList<int[]> tmp = construireListeRoute(coordonnees);
        this.tableCoord.put(tmp, controlerPartie.partie.getMap().getRoutes().get(0));
    }

    private Route rechercherRoute(float x, float y) {
        int[] tmp = new int[2];
        tmp[0] = (int) x;
        tmp[1] = (int) y;

        for (Enumeration<ArrayList<int[]>> e = tableCoord.keys(); e.hasMoreElements();) {
            System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEe : " + e.nextElement());
            if (e.nextElement().contains(tmp)) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> DANS LE IF <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                return (Route) tableCoord.get(e);
            }
        }
        return null;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        System.out.println(actor.getCell() + " has been clicked.");
        Route r = rechercherRoute(x, y);
        System.out.println(r);
        //controlerPartie.prendreRoute(r);
    }
}