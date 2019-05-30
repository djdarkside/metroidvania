package com.djdarkside.gameApp.utils.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.djdarkside.gameApp.utils.constants.Constants;

public class WorldUtils 
{
	
	public static World createWorld() 
	{
        return new World(new Vector2(0, Constants.GRAVITY), true);
    }
	
    public static Body createBox(World world, int xPos, int yPos, int width, int height, 
    		boolean isStatic, boolean fixed, float density)
    {
       	Body pBody;
        BodyDef def = new BodyDef();

        if (isStatic) {
            def.type = BodyDef.BodyType.StaticBody;
        } else {
            def.type = BodyDef.BodyType.DynamicBody;
        }

        def.position.set(xPos / Constants.PPM, yPos / Constants.PPM);
        def.allowSleep = false;

        if (fixed) {
            def.fixedRotation = true;
        } else {
            def.fixedRotation = false;
        }

        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / Constants.PPM, height / 2 / Constants.PPM);
        FixtureDef fDefBody = new FixtureDef();
        fDefBody.shape = shape;
        fDefBody.density = density;
        pBody.createFixture(fDefBody);

        shape.dispose();
        return pBody;
    }

    public static Body createPlayer(World world, int xPos, int yPos, int width, int height, 
    		boolean isStatic, boolean fixed, float density, Boolean hasFeetSensor)
    {
        Body pBody;
        BodyDef def = new BodyDef();

        if (isStatic) {
            def.type = BodyDef.BodyType.StaticBody;
        } else {
            def.type = BodyDef.BodyType.DynamicBody;
        }

        def.position.set(xPos / Constants.PPM, yPos / Constants.PPM);
        def.allowSleep = false;

        if (fixed) {
            def.fixedRotation = true;
        } else {
            def.fixedRotation = false;
        }

        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / Constants.PPM, height / 2 / Constants.PPM);
        FixtureDef fDefBody = new FixtureDef();
        fDefBody.shape = shape;
        fDefBody.density = density;
        //fDefBody.filter.categoryBits = Constants.PLAYER_BIT;
        pBody.createFixture(fDefBody);
        shape.dispose();

        if (hasFeetSensor) {
            PolygonShape feet = new PolygonShape();
            feet.setAsBox(width / 4 / Constants.PPM, height / 8 / Constants.PPM, new Vector2(0, -14f / Constants.PPM), 0);
            FixtureDef fDefFoot = new FixtureDef();
            fDefFoot.shape = feet;
            fDefFoot.isSensor = true;
            //fDefFoot.filter.categoryBits = Constants.PLAYER_BIT;
            //fDefFoot.filter.maskBits = Constants.FLOOR_BIT;
            pBody.createFixture(fDefFoot).setUserData("Feet");
            feet.dispose();
        }
        return pBody;
    }

    public static Body createCircle(World world, float xPos, float yPos, float radius, boolean isStatic, boolean fixed, float linearDamping) 
	{
	    Body pBody;
	    BodyDef def = new BodyDef();
	    def.position.set(xPos / Constants.PPM, yPos / Constants.PPM);
	
	    if (isStatic) {
	        def.type = BodyDef.BodyType.StaticBody;
	    } else {
	        def.type = BodyDef.BodyType.DynamicBody;
	    }
	
	    def.linearDamping = linearDamping;
	
	    if (fixed) {
	        def.fixedRotation = true;
	    } else {
	        def.fixedRotation = false;
	    }
	
	    pBody = world.createBody(def);
	
	    CircleShape shape = new CircleShape();
	    shape.setRadius(radius / Constants.PPM);
	
	    FixtureDef fDef = new FixtureDef();
	    fDef.shape = shape;
	    fDef.density = 1.0f;
	
	    pBody.createFixture(shape, 1.0f);
	    shape.dispose();
	    return pBody;
	}
}
