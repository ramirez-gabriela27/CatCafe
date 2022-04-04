package com.catcafe.main;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.*;

public class CatCafe extends Game {
	SpriteBatch batch;
	Texture img; //texture for the sprite
	Sprite sprite; //sprite for background

	OrthographicCamera camera; //set window size/resolution
	final float GAME_WORLD_WIDTH = 640;//px
	final float GAME_WORLD_HEIGHT = 360;//px

	Viewport viewport;

	//called once at load
	@Override
	public void create () {
		batch = new SpriteBatch();
		//a texture is NOT a sprite
		img = new Texture("background_main_menu.png");
		sprite = new Sprite(img);

		//to set up resolution as well window size
		camera = new OrthographicCamera();//resolutions set by the viewport
		viewport = new StretchViewport(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, camera);
		viewport.apply();
		camera.position.set(GAME_WORLD_WIDTH/2, GAME_WORLD_HEIGHT/2, 0);
	}

	@Override
	public void resize(int width, int height){
		viewport.update(width,height);
		camera.position.set(GAME_WORLD_WIDTH/2, GAME_WORLD_HEIGHT/2, 0);
	}

	//this is where we draw (called every single frame)
	@Override
	public void render () {
		//background color matches palette
		ScreenUtils.clear(0.2470588235294118f, 0.1568627450980392f, 0.196078431372549f, 1);

		camera.update();

		batch.begin();
		batch.setProjectionMatrix(camera.combined); //set the camera

		batch.draw(sprite, 0, 0); //sprite test
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
