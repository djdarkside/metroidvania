package com.djdarkside.gameApp.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.djdarkside.gameApp.MainApplication;
import com.djdarkside.gameApp.utils.box2d.WorldUtils;
import com.djdarkside.gameApp.utils.constants.Constants;

public class Samus 
{
	
	private final MainApplication app;
	private World world;
	private Body samusBody;		
	
	public static boolean movingRight = false;
    public static boolean movingLeft = false;
    public boolean isJumping = false;

	public enum playerState 
	{
		MOVING_LEFT, MOVING_RIGHT, JUMPING, STANDING, IDLE, WALKING, RUNNING, STOPPING, CROUCHING
	}
	
	public playerState currentState;
	
	public Samus (final MainApplication app, World world) 
	{
		this.app = app;
		this.world = world;	
		initBody();
		
	}
	
	public playerState getPlayerState() 
	{
		return currentState;
	}
	
	public void render(float delta) 
	{
		System.out.println("PlayPosX: " + samusBody.getPosition().x + ", PlayPosY: "+ samusBody.getPosition().y);
	}
	
	private void initBody() 
	{
		//samusBody = WorldUtils.createPlayer(world, 50, 80, 24, 48, false, true, 1.0f, false);
		samusBody = WorldUtils.createPlayer(world, 200, 500, 16, 32, false, true, 1.0f, false);
		samusBody.setUserData(null);		
    }
	
	public void update(float delta) {
        inputUpdate(delta);
        updateMotion(delta);
	}
	
    public void setLeftMove(boolean t) {
        if(movingRight && t) movingRight = false;
        movingLeft = t;
    }
    public void setRightMove(boolean t) {
        if(movingLeft && t) movingLeft = false;
        movingRight = t;
    }

    public void jump(float delta) {
        if (isJumping == true) {
            //if (app.contact.isPlayerOnGround()) {
                //samusBody.setLinearVelocity(samusBody.getLinearVelocity().x, 0);
                //samusBody.applyForceToCenter(0, 320f, true);
                //currentState = playerState.JUMPING;
            //}
        }
        isJumping = false;
    }

    public void inputUpdate(float delta) {
        currentState = playerState.STANDING;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            setLeftMove(true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            setRightMove(true);
        } else {
            setLeftMove(false);
            setRightMove(false);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
        	samusBody.setLinearVelocity(samusBody.getLinearVelocity().x, 0);
            samusBody.applyForceToCenter(0, 30f * Constants.PPM, true);
        	if (currentState != playerState.JUMPING) {
                isJumping = true;
                jump(delta);
            } else {
                isJumping = false;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            System.out.println("Exited");
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            //attack(samusBody);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            app.camera.zoom += .05f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            app.camera.zoom -= .05f;
        }
    }
    
    public void updateMotion(float delta) {
        float horizontalForce = 0;
        currentState = playerState.STANDING;
        if (movingLeft) {
            horizontalForce -= 2;
            currentState = playerState.WALKING;
            //index = 1;
        }
        if (movingRight) {
            horizontalForce += 2;
            currentState = playerState.WALKING;
            //index = 2;
        }
        samusBody.setLinearVelocity(horizontalForce * 5f, samusBody.getLinearVelocity().y);
    }
	
	public void dispose() 
	{
        world.dispose();
    }
	
	public Body getPlayerBody() 
	{
        return samusBody;
    }
	
	
	
	
	
}
