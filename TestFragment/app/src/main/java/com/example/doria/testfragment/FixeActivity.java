package com.example.doria.testfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class FixeActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_fragment_fixe);
    }

    public void changerTexte() {
        TextView textView = (TextView) findViewById(R.id.tvChange);
        textView.setText("Nouveau texte");
    }
}
