package com.djdarkside.gameApp.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.djdarkside.gameApp.MainApplication;
import com.djdarkside.gameApp.entities.Samus;
import com.djdarkside.gameApp.utils.box2d.TiledMapObjectUtils;
import com.djdarkside.gameApp.utils.box2d.WorldUtils;
import com.djdarkside.gameApp.utils.camera.CameraStyles;
import com.djdarkside.gameApp.utils.constants.Constants;

public class TileTest implements Screen
{
	private final MainApplication app;
	private Stage stage;
	private OrthogonalTiledMapRenderer tmr;
	private TiledMap tMap;
	private World world;
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera b2dCam;
	
	private TextureRegion bkg;
	
	public int levelWidth = 0;
    public int levelHeight = 0;
		
    private Samus samus;
    private DebugHUD hud;
    
	public TileTest(final MainApplication app) 
	{
		this.app = app;
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, (Constants.V_WIDTH / Constants.PPM) / Constants.V_SCALE, (Constants.V_HEIGHT / Constants.PPM) / Constants.V_SCALE);
		stage = new Stage(new FitViewport(Constants.V_WIDTH / Constants.V_SCALE, Constants.V_HEIGHT / Constants.V_SCALE));
        //stage = new Stage(new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT));
		loadAssets();
	}
	
	private void loadAssets() 
	{

	}

	@Override
	public void show() 
	{		
		Gdx.input.setInputProcessor(stage);
        stage.clear();
        
        world = WorldUtils.createWorld();
       
        b2dr = new Box2DDebugRenderer();         
        hud = new DebugHUD(app, app.batch);
        samus = new Samus(app, world);
        
        tMap = app.manager.get(LoadingScreen.MAP, TiledMap.class);      
        MapProperties props = tMap.getProperties();
        levelWidth = props.get("width", Integer.class);
        levelHeight = props.get("height", Integer.class);

        TiledMapObjectUtils.buildShapes(tMap, Constants.PPM, world, "boarder");
        
        tmr = new OrthogonalTiledMapRenderer(tMap, app.batch);     
        tmr.setView(app.camera);
        
        bkg = new TextureRegion(app.manager.get(LoadingScreen.BKG, Texture.class));
        
        WorldUtils.createBox(world, 100, 500, 8, 8, false, true, 1.0f);
	}

	@Override
	public void render(float delta) 
	{
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(Gdx.graphics.getDeltaTime());
        stage.draw();             
               
        app.batch.begin();
        //app.batch.draw(bkg, 0, 0, Constants.V_WIDTH / Constants.V_SCALE, Constants.V_HEIGHT / Constants.V_SCALE);
        app.batch.draw(bkg, 0, 0, Constants.V_WIDTH, Constants.V_HEIGHT);
        app.batch.end();        
        
        b2dr.render(world, app.camera.combined.scl(Constants.PPM));
        
        tmr.render();        

        samus.render(delta);
        
        hud.render(delta);
       
	}
	
    public void update(float delta) 
    {
        world.step(1 / 60f, 6, 2);
        stage.act(delta);
        cameraUpdate(delta); 
        
        samus.update(delta);
        app.batch.setProjectionMatrix(app.camera.combined);    
        tmr.setView(app.camera);
    }
    
    public void cameraUpdate(float delta) 
    {
    	//CameraStyles.lerpToTarget(app.camera, samus.getPlayerBody().getPosition().scl(Constants.PPM));
        //float startX = app.camera.viewportWidth / Constants.V_SCALE;
        //float startY = app.camera.viewportHeight / Constants.V_SCALE;
        float startX = samus.getPlayerBody().getPosition().x;
        float startY = samus.getPlayerBody().getPosition().y;
        CameraStyles.asymptoticAverage(app.camera, samus.getPlayerBody().getPosition().scl(Constants.PPM));
        //CameraStyles.camBoundry(app.camera, startX, startY, levelWidth * Constants.PPM - startX * 2, levelHeight * Constants.PPM - startY * 2);
        CameraStyles.camBoundry(app.camera, 0, 0, 1280, 1280);

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
        world.dispose();
        b2dr.dispose();
        stage.dispose();		
	}
	
	
}
