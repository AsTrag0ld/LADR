package com.projet.ladr.controler;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.projet.ladr.R;



public class Options extends Activity {

    MediaPlayer mySound;
    Switch switchMusique;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.options);

        switchMusique =(Switch) findViewById(R.id.switchMusique);


        switchMusique.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked && mySound==null){
                    spec();
                    mySound.start();
                    mySound.setLooping(true);

                }else{

                    mySound.release();
                    mySound=null;



                }

            }
        });

    }


    public void spec(){
        mySound= MediaPlayer.create(this,R.raw.music);
    }

}

