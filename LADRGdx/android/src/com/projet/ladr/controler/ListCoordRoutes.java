package com.projet.ladr.controler;

import com.projet.ladr.model.Partie;
import com.projet.ladr.model.Route;

import java.util.ArrayList;

public class ListCoordRoutes {
    public ArrayList<ArrayList<CoordTiles>> lt;
    private Partie partie;

    public ListCoordRoutes(Partie partie){
        this.partie = partie;
        ArrayList<CoordTiles> tmp = new ArrayList<CoordTiles>(){};

        tmp.add(new CoordTiles(23, 24));
        tmp.add(new CoordTiles(22, 24));
        tmp.add(new CoordTiles(21, 24));
        tmp.add(new CoordTiles(20, 24));
        tmp.add(new CoordTiles(19, 24));
        tmp.add(new CoordTiles(18, 24));
        tmp.add(new CoordTiles(17, 24));
        tmp.add(new CoordTiles(16, 24));
        tmp.add(new CoordTiles(15, 24));
        tmp.add(new CoordTiles(23, 35));
        tmp.add(new CoordTiles(21, 35));
        tmp.add(new CoordTiles(20, 35));
        tmp.add( new CoordTiles(19, 35));
        tmp.add( new CoordTiles(18, 35));
        tmp.add( new CoordTiles(17, 35));
        tmp.add( new CoordTiles(16, 35));

        lt = new ArrayList<ArrayList<CoordTiles>>();
        lt.add(tmp);
    }

    public Route rechercherRoute(int x, int y){
        Route res = null;
        CoordTiles r = new CoordTiles(x, y);
        for(int i=0; i<lt.size(); i++){
            if(lt.get(i).contains(r)){
                res = partie.getMap().getRoutes().get(i);
            }
        }
        System.out.println("C EST PASSE DANS RECHERCHE()!");
        return res;
    }
}
