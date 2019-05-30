package com.djdarkside.gameApp.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;
import com.djdarkside.gameApp.MainApplication;
import com.djdarkside.gameApp.utils.constants.Constants;

//Loads All Assets

public class LoadingScreen implements Screen 
{
	private final MainApplication app;
	private float progress;
    private ShapeRenderer renderer;
    
    //public static final String MAP = "TestMap.tmx";
    public static final String MAP = "Metroid.tmx";
    public static final String BKG = "bg_volcano.png";
    

    public LoadingScreen(final MainApplication app) 
    {
        this.app = app;
        renderer = new ShapeRenderer();
    }
    
    public void update(float delta) 
    {
        progress = MathUtils.lerp(progress, app.manager.getProgress(), .1f);
        
        if (app.manager.update() && progress >= app.manager.getProgress() - .01f) 
        {
            app.setScreen(app.testScreen);
        }
    }

	@Override
	public void show() {
		progress = 0f;
        queueAssets();		
	}

	private void queueAssets() 
	{
		app.manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        app.manager.load(MAP, TiledMap.class);   
        app.manager.load(BKG, Texture.class);
        app.manager.finishLoading();
	}

	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);
        // Loading Bar
        // Empty
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.BLACK);
        renderer.rect(32, app.camera.viewportHeight / 10 - 8, Constants.V_WIDTH - 64, 16);
        //Full - Fills when assets are loaded
        renderer.setColor(Color.BLUE);
        renderer.rect(32, app.camera.viewportHeight / 10 - 8, progress * (Constants.V_WIDTH - 64), 16);
        renderer.end();
        //End Loading Bar

        app.batch.begin();
        app.font.draw(app.batch, "Loading..." + app.manager.getAssetNames(), Constants.V_WIDTH / 2 - 24, 64);
        app.batch.end();					
	}

	@Override
	public void resize(int width, int height) {
				
	}

	@Override
	public void pause() {
				
	}

	@Override
	public void resume() {
				
	}

	@Override
	public void hide() {
				
	}

	@Override
	public void dispose() 
	{
		renderer.dispose();
	}

	
}
