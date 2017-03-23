package com.projet.ladr.controler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.projet.ladr.R;
import com.projet.ladr.model.Partie;

/**
 * Created by Rapto on 22/03/2017.
 */

public class Map extends Game {
    TiledMap map;
    OrthogonalTiledMapRenderer mapRenderer;
    OrthographicCamera camera;
    Stage stage;

    @Override
    public void create () {
        map = new TmxMapLoader().load("map.tmx");
		stage = new TiledMapStage(map);
		Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render () {
        //stage.act();
        float unitScale = 1 / 10f;
        mapRenderer = new OrthogonalTiledMapRenderer(map, unitScale);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 205, 132);
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    @Override
    public void dispose () {

    }
}
