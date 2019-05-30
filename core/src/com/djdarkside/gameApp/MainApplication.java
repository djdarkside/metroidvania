package com.djdarkside.gameApp;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.djdarkside.gameApp.screens.LoadingScreen;
import com.djdarkside.gameApp.screens.MainMenu;
import com.djdarkside.gameApp.screens.TileTest;
import com.djdarkside.gameApp.utils.constants.Constants;

public class MainApplication extends Game 
{
	public SpriteBatch batch;
	public OrthographicCamera camera;
	public AssetManager manager;
	
	public MainMenu menu;
	
	public LoadingScreen lScreen;
	public TileTest testScreen;
	public BitmapFont font;
	
	@Override
	public void create () 
	{
		Gdx.app.setLogLevel(com.badlogic.gdx.Application.LOG_DEBUG);		
		
		camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.V_WIDTH / Constants.V_SCALE, Constants.V_HEIGHT / Constants.V_SCALE);        
        
        batch = new SpriteBatch();
	
        manager = new AssetManager();
        
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        
        testScreen = new TileTest(this);
        lScreen = new LoadingScreen(this);
        
        setScreen(lScreen);
	}

	@Override
	public void render () 
	{
		super.render();
		System.out.println(Constants.V_WIDTH);
	}
	
	@Override
	public void dispose () 
	{
		batch.dispose();
		font.dispose();
		manager.dispose();
	}
}
