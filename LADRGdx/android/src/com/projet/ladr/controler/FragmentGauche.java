package com.projet.ladr.controler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.projet.ladr.R;

public class FragmentGauche extends Fragment {
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.layout_gauche, container, false);

        Button button = (Button) myView.findViewById(R.id.btnPasserTour);
        button.setOnClickListener(new View.OnClickListener() {
            ControlerPartie cont = (ControlerPartie) getActivity();
            @Override
            public void onClick(View v) {
                cont.changerTour();
            }
        });

        Button button2 = (Button) myView.findViewById(R.id.btnRoutesPossedees);
        button2.setOnClickListener(new View.OnClickListener() {
            ControlerPartie cont = (ControlerPartie) getActivity();
            @Override
            public void onClick(View v) {
                cont.afficherRoutesPossedees();
            }
        });

        ImageView imageView = (ImageView) myView.findViewById(R.id.imgCarteDestination);
        imageView.setOnClickListener(new View.OnClickListener() {
            ControlerPartie cont = (ControlerPartie) getActivity();
            @Override
            public void onClick(View v) {
                cont.afficherCartesDestination();
            }
        });
        return myView;
    }

}
