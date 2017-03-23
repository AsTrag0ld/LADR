package com.projet.ladr.controler;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
/**
 * Created by Rapto on 22/03/2017.
 */

public class TiledMapClickListener extends ClickListener{

    private TiledMapActor actor;

    public TiledMapClickListener(TiledMapActor actor) {
        this.actor = actor;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        System.out.println(actor.getCell() + " has been clicked.");
    }
}
