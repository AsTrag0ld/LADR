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
        float unitScale = 1 / 13f;
        mapRenderer = new OrthogonalTiledMapRenderer(map, unitScale);
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 155, 102);
        stage = new TiledMapStage(map);
        Gdx.input.setInputProcessor(stage);
        mapRenderer.setView(camera);
    }

    @Override
    public void render () {
        mapRenderer.render();
        stage.getViewport().setCamera(camera);
        stage.act();
    }

    @Override
    public void dispose () {

    }
}
