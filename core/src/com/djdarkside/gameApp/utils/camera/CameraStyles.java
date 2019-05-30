package com.djdarkside.gameApp.utils.camera;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CameraStyles {

    public static void lerpToTarget(Camera camera, Vector2 target) 
    {
        Vector3 position = camera.position;
        // a + (b - a) * lerp
        // a = current camera position
        // b = target
        // lerp = interpolation factor
        //                 a           + (   b     -        a         ) * lerp
        position.x = camera.position.x + (target.x - camera.position.x) * .1f;
        position.y = camera.position.y + (target.y - camera.position.y) * .1f;

        camera.position.set(position);
        camera.update();
    }

    public static void asymptoticAverage(Camera camera, Vector2 target) {
        Vector3 position = camera.position;
        position.x = (.90f * camera.position.x) + (.10f * target.x); // default settings from video
        // x += (target - x) * 0.1f;
        position.y = (.90f * camera.position.y) + (.10f * target.y);
        camera.position.set(position);
        camera.update();
    }

    public static void camBoundry(Camera cam, float startX, float startY, float width, float height) 
    {
        Vector3 position = cam.position;

        if (position.x < startX) 
        {
            position.x = startX;
        }
        if (position.y < startY) 
        {
            position.y = startY;
        }
        if (position.x > startX + width) 
        {
            position.x = startX + width;
        }
        if (position.y > startY + height) 
        {
            position.y = startY + height;
        }
        cam.position.set(position);
        cam.update();
    }
    
    public static void camBoundrySmall(Camera cam, float startX, float startY, float width, float height ) {
    	
    	Vector3 position = cam.position;
    }
}
