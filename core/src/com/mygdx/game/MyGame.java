package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends Game {
	SpriteBatch batch;
    public BitmapFont font;

	@Override
	public void create () {
		Settings settings = new Settings();
		batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(1,1);
        this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
        font.dispose();
	}
}
