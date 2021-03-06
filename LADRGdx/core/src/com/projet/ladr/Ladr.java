package com.projet.ladr;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ladr extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	public static AssetManager manager;
    private Music music;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        manager = new AssetManager();
        manager.load("audio/music/FT.mp3", Music.class);
        manager.finishLoading();
        music = Ladr.manager.get("audio/music/FT.mp3", Music.class);
        music.setLooping(true);
        music.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
        manager.dispose();
		batch.dispose();
		img.dispose();

	}
}
