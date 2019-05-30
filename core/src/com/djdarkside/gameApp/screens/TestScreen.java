package com.djdarkside.gameApp.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.djdarkside.gameApp.MainApplication;
import com.djdarkside.gameApp.utils.box2d.WorldUtils;
import com.djdarkside.gameApp.utils.constants.Constants;

public class TestScreen implements Screen
{

	private final MainApplication app;
    private World world;
    private Body player;

    private Box2DDebugRenderer b2dr;

    private OrthogonalTiledMapRenderer tmr;
    private TiledMap map;
    private Stage stage;
    //private Player player;

    public int levelWidth = 0;
    public int levelHeight = 0;
	
	public TestScreen (final MainApplication app)
	{
		this.app = app;
		stage = new Stage(new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT));
	}
	
	
	@Override
	public void show() 
	{
		Gdx.input.setInputProcessor(stage);
        stage.clear();
        
        world = WorldUtils.createWorld();
        b2dr = new Box2DDebugRenderer();
        
        player = WorldUtils.createPlayer(world, levelHeight, levelHeight, levelHeight, levelHeight, false, false, levelHeight, null);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
