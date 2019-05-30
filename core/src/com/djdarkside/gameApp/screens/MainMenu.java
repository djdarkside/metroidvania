package com.djdarkside.gameApp.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.djdarkside.gameApp.MainApplication;
import com.djdarkside.gameApp.utils.constants.Constants;

public class MainMenu implements Screen {
	
	private final MainApplication app;
	private Stage stage;
	
	public MainMenu (final MainApplication app) {
		this.app = app;
		stage = new Stage(new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT));
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
        stage.clear();	
	}
	
    private void update(float delta) {
        stage.act(delta);
    }

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        app.batch.begin();
        app.font.draw(app.batch, "Main Menu Screen...", Constants.V_WIDTH / 2 - 24, 564);
        app.batch.end();

        update(delta);

        stage.draw();
		
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
		stage.dispose();		
	}

}
