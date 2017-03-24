package com.projet.ladr.controler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.projet.ladr.R;
import com.projet.ladr.model.Route;


public class GestionRoute extends Fragment {
    View myView;
    ImageView img;
    RelativeLayout rl;
    ListView list;
    ControlerPartie contPartie;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.gestion_route, container, false);
        contPartie = (ControlerPartie) getActivity();

        img = (ImageView) myView.findViewById(R.id.imgMap);
        rl = (RelativeLayout) myView.findViewById(R.id.rlRoutes);
        list = (ListView) myView.findViewById(R.id.lvRoutes);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setVisibility(View.GONE);
                rl.setVisibility(View.VISIBLE);
                RouteAdapter adapter = new RouteAdapter(contPartie.getApplicationContext());
                adapter.setRoutesList(contPartie.partie.getMap().getRoutesDisponibles());
                list.setAdapter(adapter);
            }
        });

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
                Route route = (Route) parent.getItemAtPosition(position);
                contPartie.prendreRoute(route);
            }
        };
        list.setOnItemClickListener(itemClickListener);

        return myView;
    }

    public void resetFragment() {
        img = (ImageView) myView.findViewById(R.id.imgMap);
        rl = (RelativeLayout) myView.findViewById(R.id.rlRoutes);
        img.setVisibility(View.VISIBLE);
        rl.setVisibility(View.GONE);
    }
}
