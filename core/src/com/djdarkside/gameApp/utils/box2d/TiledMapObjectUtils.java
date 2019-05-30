package com.djdarkside.gameApp.utils.box2d;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.djdarkside.gameApp.utils.constants.Constants;

public class TiledMapObjectUtils {
	
	// The pixels per tile. If your tiles are 16x16, this is set to 16f
	private static float PPM = 0;
	public static int spawnPointX, spawnPointY;

	
	public static Array<Body> buildShapes(Map map, float pixels, World world, String mapLayer) 
	{
        PPM = pixels;
        MapObjects objects = map.getLayers().get(mapLayer).getObjects();

        Array<Body> bodies = new Array<Body>();

        for(MapObject object : objects) 
        {
            if (object instanceof TextureMapObject) 
            {
                continue;
            }

            Shape shape;

            if (object instanceof RectangleMapObject) 
            {
                shape = getRectangle((RectangleMapObject)object);
            }
            else if (object instanceof PolygonMapObject) 
            {
                shape = getPolygon((PolygonMapObject)object);
            }
            else if (object instanceof PolylineMapObject) 
            {
                shape = getPolyline((PolylineMapObject)object);
            }
            else if (object instanceof CircleMapObject) 
            {
                shape = getCircle((CircleMapObject)object);
            }
            else if (object instanceof EllipseMapObject) 
            {
                shape = getEllipse((EllipseMapObject)object);
            }
            else 
            {
                continue;
            }

            BodyDef bd = new BodyDef();
            bd.type = BodyDef.BodyType.StaticBody;
            Body body = world.createBody(bd);

            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;
            //fdef.filter.categoryBits = Constants.FLOOR_BIT;
            //fdef.filter.maskBits = Constants.PLAYER_BIT;
            fdef.density = 1;
            body.createFixture(fdef).setUserData("GROUND");
            bodies.add(body);
            shape.dispose();
        }
        return bodies;
    }
	
    private static PolygonShape getRectangle(RectangleMapObject rectangleObject) 
    {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / PPM,
                (rectangle.y + rectangle.height * 0.5f ) / PPM);
        polygon.setAsBox(rectangle.width * 0.5f / PPM,
                rectangle.height * 0.5f / PPM,
                size,
                0.0f);
        return polygon;
    }
    
    private static CircleShape getCircle(CircleMapObject circleObject) 
    {
        Circle circle = circleObject.getCircle();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(circle.radius / PPM);
        circleShape.setPosition(new Vector2(circle.x / PPM, circle.y / PPM));
        return circleShape;
    }
    
    private static CircleShape getEllipse(EllipseMapObject ellipseObject) 
    {
        Ellipse ellipse = ellipseObject.getEllipse();
        CircleShape circleShape = new CircleShape();
        //Needed to find Radius of an ellyspe (c = 2pi R squared)
        circleShape.setRadius((ellipse.circumference() / (2 * MathUtils.PI)) / PPM);
        circleShape.setPosition(new Vector2(ellipse.x / PPM, ellipse.y / PPM));
        return circleShape;
    }
    
    public static PolygonShape getPolygon(PolygonMapObject polygonObject) 
    {
        PolygonShape polygon = new PolygonShape();
        float[] vertices = polygonObject.getPolygon().getTransformedVertices();

        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) 
        {
            System.out.println(vertices[i]);
            worldVertices[i] = vertices[i] / PPM;
        }

        polygon.set(worldVertices);
        return polygon;
    }
    
    private static ChainShape getPolyline(PolylineMapObject polylineObject) 
    {
        float[] vertices = polylineObject.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; ++i) 
        {
            worldVertices[i] = new Vector2();
            worldVertices[i].x = vertices[i * 2] / PPM;
            worldVertices[i].y = vertices[i * 2 + 1] / PPM;
        }

        ChainShape chain = new ChainShape();
        chain.createChain(worldVertices);
        return chain;
    }
	
	//Grabs the ChainShape and creates a box2d object to it
    public static void parseTiledObjectLayer(World world, MapObjects objects) 
    {
        for (MapObject object: objects) 
        {
            Shape shape;
            if (object instanceof PolylineMapObject) 
            {
                shape = createPolyLine((PolylineMapObject) object);
            } 
            else
            {
                continue;
            }
            
            Body body;
            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bdef);
            body.createFixture(shape, 1.0f);
            shape.dispose();
        }
    }
	
	//Grabs all polylines in Tiled tilemap and links them together
	private static ChainShape createPolyLine(PolylineMapObject polyline) 
	{
		float[] vertices = polyline.getPolyline().getTransformedVertices();
		Vector2[] worldVerticies = new Vector2[vertices.length / 2];
		
		for (int i = 0; i < worldVerticies.length; i++) 
		{
			worldVerticies[i] = new Vector2(vertices[i * 2] / PPM, vertices[i * 2 + 1] / PPM);
			
		}
		
		ChainShape cs = new ChainShape();
		cs.createChain(worldVerticies);
		return cs;
	}
}
