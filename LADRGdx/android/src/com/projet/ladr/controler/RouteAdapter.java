package com.projet.ladr.controler;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.projet.ladr.R;
import com.projet.ladr.model.Route;

import java.util.ArrayList;
import java.util.LinkedList;

public class RouteAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private LinkedList<Route> routesList = null;

    public RouteAdapter(Context aContext) {
        super();

        context = aContext;
        inflater = LayoutInflater.from(context);
        routesList = new LinkedList<Route>();

    }

    public void setRoutesList(LinkedList<Route> list) {
        this.routesList = list;
    }

    @Override
    public int getCount() {
        return routesList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return routesList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View convertView, ViewGroup parent) {
        RelativeLayout layoutItem;

        if (convertView == null) {
            layoutItem = (RelativeLayout) inflater.inflate(
                    R.layout.row_route, parent, false);
        } else {
            layoutItem = (RelativeLayout) convertView;
        }

        TextView couleur = (TextView) layoutItem.findViewById(R.id.tvCouleurRoute);
        couleur.setText(this.routesList.get(arg0).getCouleur());
        couleur.setTextColor(Color.BLACK);
        TextView taille = (TextView) layoutItem.findViewById(R.id.tvTailleRoute);
        taille.setText(Integer.toString(this.routesList.get(arg0).getTaille()));
        taille.setTextColor(Color.BLACK);
        TextView villeA = (TextView) layoutItem.findViewById(R.id.tvVilleARoute);
        villeA.setText(this.routesList.get(arg0).getVilleA().getNom());
        villeA.setTextColor(Color.BLACK);
        TextView villeB = (TextView) layoutItem.findViewById(R.id.tvVilleBRoute);
        villeB.setText(this.routesList.get(arg0).getVilleB().getNom());
        villeB.setTextColor(Color.BLACK);
        return layoutItem;
    }
}


