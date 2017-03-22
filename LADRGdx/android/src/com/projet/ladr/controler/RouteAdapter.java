package com.projet.ladr.controler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.projet.ladr.R;
import com.projet.ladr.model.Route;

import java.util.ArrayList;

public class RouteAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Route> routesList = null;

    public RouteAdapter(Context aContext) {
        super();

        context = aContext;
        inflater = LayoutInflater.from(context);
        routesList = new ArrayList<Route>();

    }

    public void setRoutesList(ArrayList<Route> list) {
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
        TextView taille = (TextView) layoutItem.findViewById(R.id.tvTailleRoute);
        taille.setText(Integer.toString(this.routesList.get(arg0).getTaille()));
        TextView villeA = (TextView) layoutItem.findViewById(R.id.tvVilleARoute);
        villeA.setText(this.routesList.get(arg0).getVilleA().getNom());
        TextView villeB = (TextView) layoutItem.findViewById(R.id.tvVilleBRoute);
        villeB.setText(this.routesList.get(arg0).getVilleB().getNom());
        return layoutItem;
    }
}


