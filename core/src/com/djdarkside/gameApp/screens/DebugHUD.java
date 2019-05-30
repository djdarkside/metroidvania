package com.djdarkside.gameApp.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.djdarkside.gameApp.MainApplication;
import com.djdarkside.gameApp.utils.constants.Constants;

public class DebugHUD implements Disposable, Screen {

	private final MainApplication app;
	private Stage stage;
	private OrthographicCamera hudCam;
	private SpriteBatch batch;
	private Viewport viewport;
	
	public DebugHUD(final MainApplication app, SpriteBatch batch) 
	{
		this.app = app;
		this.batch = batch;
		hudCam = new OrthographicCamera();
		viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, hudCam);
		stage = new Stage(viewport, batch);	
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void show() {
					
	}

	@Override
	public void render(float delta) 
	{
		stage.draw();
		batch.begin();
		app.font.draw(batch, "CamX: " + app.camera.position.x + ", CamY: " + app.camera.position.y, 50, 600);
		app.font.draw(batch, "HUDCamX: " + hudCam.position.x + ", HUDCamY: " + hudCam.position.y, 50, 620);
		batch.end();		
	}
	
	public void updateCam(float delta) {
		//hudCam.position.x = app.camera.position.x;
		//hudCam.position.y = app.camera.position.y;		
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
	
}
