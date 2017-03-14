package com.example.doria.ladrandr.view;


import android.app.Activity;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;

import com.example.doria.ladrandr.R;
import com.example.doria.ladrandr.controller.DatabaseHandler;

public class Statistiques extends Activity implements RadioGroup.OnCheckedChangeListener {
    RadioGroup group;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.statistiques);
        group = (RadioGroup) findViewById(R.id.radioGroupStat);
        group.setOnCheckedChangeListener(this);
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        DatabaseHandler dbh = new DatabaseHandler(this);
        String [] from = {"nom", "nbVictoires", "scoreTotal"};
        int [] to = {R.id.stats_nomJoueur, R.id.stats_nbVictoire, R.id.stats_nbPoint};

        if (group == this.group) {
            if (checkedId == R.id.radioBtnPoint) {
                String query = "SELECT j.rowid AS _id , nom, nbVictoires, sum(nbPoints) AS scoreTotal FROM Joueur j JOIN Classement c USING (idJoueur) GROUP BY nom, nbVictoires ORDER BY scoreTotal DESC;";
                SQLiteDatabase db = dbh.getReadableDatabase();
                Cursor c = db.rawQuery(query, null);
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.row_stats, c, from, to, 0);
                ListView lv = (ListView) findViewById(R.id.listStats);
                lv.setBackgroundResource(R.drawable.linearlayout_bg);
                lv.setAdapter(adapter);
            } else if (checkedId == R.id.radioBtnVictoire) {
                String query = "SELECT j.rowid AS _id , nom, nbVictoires, sum(nbPoints) AS scoreTotal FROM Joueur j JOIN Classement c USING (idJoueur) GROUP BY nom, nbVictoires ORDER BY nbVictoires DESC;";
                SQLiteDatabase db = dbh.getReadableDatabase();
                Cursor c = db.rawQuery(query, null);
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.row_stats, c, from, to, 0);
                ListView lv = (ListView) findViewById(R.id.listStats);
                lv.setBackgroundResource(R.drawable.linearlayout_bg);
                lv.setAdapter(adapter);
            }
        }
    }
}
