package com.projet.ladr.controler;


import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.projet.ladr.R;


public class MainActivity extends AppCompatActivity {

        private static String TAG = MainActivity.class.getSimpleName();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Button button = (Button) this.findViewById(R.id.btnConsulterStatistiques);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Statistiques.class);
                    MainActivity.this.startActivity(intent);
                }
            });

            Button button2 = (Button) this.findViewById(R.id.btnCommencerPartie);
            button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ChoixJoueurs.class);
                    MainActivity.this.startActivity(intent);
                }
            });

            Button button3 = (Button) this.findViewById(R.id.btnConsulterRegles);
            button3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ConsulterRegles.class);
                    MainActivity.this.startActivity(intent);
                }
            });

            Button button4 = (Button) this.findViewById(R.id.btnOptions);
            button4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Options.class);
                    MainActivity.this.startActivity(intent);
                }
            });
        }

        @Override
        protected void onStart() {
            super.onStart();
            Log.v(TAG, "onStart()");
        }

        @Override
        protected void onResume() {
            super.onResume();
            Log.v(TAG, "onResume()");
        }

        @Override
        protected void onPause() {
            super.onPause();
            Log.v(TAG, "onPause()");
        }

        @Override
        protected void onStop() {
            super.onStop();
            Log.v(TAG, "onStop()");
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            Log.v(TAG, "onDestroy()");
        }
}

