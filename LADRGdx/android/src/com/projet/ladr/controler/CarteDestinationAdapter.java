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
import com.projet.ladr.model.CarteDestination;
import com.projet.ladr.model.Route;

import java.util.ArrayList;
import java.util.LinkedList;

public class CarteDestinationAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private LinkedList<CarteDestination> cartesList = null;

    public CarteDestinationAdapter(Context aContext) {
        super();

        context = aContext;
        inflater = LayoutInflater.from(context);
        cartesList = new LinkedList<CarteDestination>();

    }

    public void setCartesList(LinkedList<CarteDestination> list) {
        this.cartesList = list;
    }

    @Override
    public int getCount() {
        return cartesList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return cartesList.get(arg0);
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
                    R.layout.row_carte_destination, parent, false);
        } else {
            layoutItem = (RelativeLayout) convertView;
        }

        TextView taille = (TextView) layoutItem.findViewById(R.id.tvValeurCarte);
        taille.setText(Integer.toString(this.cartesList.get(arg0).getValeur()));
        taille.setTextColor(Color.BLACK);
        TextView villeA = (TextView) layoutItem.findViewById(R.id.tvVilleACarte);
        villeA.setText(this.cartesList.get(arg0).getVilleA().getNom());
        villeA.setTextColor(Color.BLACK);
        TextView villeB = (TextView) layoutItem.findViewById(R.id.tvVilleBCarte);
        villeB.setText(this.cartesList.get(arg0).getVilleB().getNom());
        villeB.setTextColor(Color.BLACK);
        return layoutItem;
    }
}


