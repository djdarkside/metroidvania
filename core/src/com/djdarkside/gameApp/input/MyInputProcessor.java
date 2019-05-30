package com.djdarkside.gameApp.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.djdarkside.gameApp.MainApplication;
import com.djdarkside.gameApp.entities.Samus;

public class MyInputProcessor implements InputProcessor, ControllerListener {

    private final MainApplication app;
    private Body body;
    private Stage stage;
    private Samus samus;

    public MyInputProcessor(final MainApplication app, Body body, Stage stage) {
        this.app = app;
        this.body = body;
        this.stage = stage;
        Gdx.input.setInputProcessor(stage);
    }
    
    /*
    public void updateMotion(float delta) {
        float horizontalForce = 0;
        samus.currentState = samus.playerState.STANDING;
        if (samus.movingLeft) {
            horizontalForce -= 1;
            samus.currentState = samus.playerState.WALKING;
            //player.index = 1;
        }
        if (samus.movingRight) {
            horizontalForce += 1;
            samus.currentState = samus.playerState.WALKING;
            //player.index = 2;
        }
        samus.getPlayerBody().setLinearVelocity(horizontalForce * 5f, samus.getPlayerBody().getLinearVelocity().y);
    }
    */
    
	@Override
	public void connected(Controller controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnected(Controller controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean povMoved(Controller controller, int povCode, PovDirection value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
