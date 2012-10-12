package org.flixel.plugin.flxbox2d.collision.shapes;

import org.flixel.FlxG;
import org.flixel.plugin.flxbox2d.B2FlxB;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.EdgeShape;

/**
 * A line segment (edge) shape. These can be connected in chains or loops 
 * to other edge shapes. The connectivity information is used to ensure 
 * correct contact normals. 
 * 
 * @author Ka Wing Chin
 */
public class B2FlxEdge extends B2FlxSprite
{
	// Holds the vertices.
	private float[][] _vertices;

	/**
	 * This creates an edge.
	 * @param x			The X-coordinate of the point in space.
	 * @param y			The Y-coordinate of the point in space.
	 * @param vertices	The vertices of the line segment.
	 */
	public B2FlxEdge(float x, float y, float[][] vertices)
	{
		super(x, y);
		if(vertices != null)
			_vertices = vertices;
		else
			FlxG.log("no vertices has been set, default is used.");
		createShape();
	}
	
	/**
	 * This creates an edge.
	 * @param x			The X-coordinate of the point in space.
	 * @param y			The Y-coordinate of the point in space.
	 */
	public B2FlxEdge(float x, float y)
	{
		this(x, y, null);
	}
	
	/**
	 * This creates an edge.
	 * @param x			The X-coordinate of the point in space.
	 */
	public B2FlxEdge(float x)
	{
		this(x, 0, null);
	}
	
	/**
	 * This creates an edge.
	 */
	public B2FlxEdge()
	{
		this(0, 0, null);
	}
	
	/**
	 * The default line segment. A message will be logged 
	 * so you'll know the default has been used.
	 */
	@Override
	public void setDefaults()
	{
		setType(STATIC);
		/*
		 * 						   p4
		 * 						  /
		 * p1-------p2          /
		 * 			  \       /
		 * 			   \    /
		 * 			    \ /
		 * 				p3
		 */
		_vertices = new float[][]{{10,100}, {100,100}, {150,150}, {250, 50}};
	}
	
	/**
	 * Creates the shape.
	 */
	@Override
	public void createShape()
	{
		shape = new EdgeShape();
		fixtureDef.shape = shape;
	}
	
	/**
	 * Creates the body.
	 * @return This object. Handy for chaining stuff together.
	 */
	@Override
	public B2FlxEdge create()
	{		
		bodyDef.position.x = x / RATIO;
		bodyDef.position.y = y / RATIO;
		position = bodyDef.position;
		body = B2FlxB.world.createBody(bodyDef);
		
		int length = _vertices.length - 1;			
		Vector2 startPoint;
		Vector2 endPoint;
		for (int i = 0; i < length; i++) 
		{
			startPoint = new Vector2(_vertices[i][0]/RATIO, _vertices[i][1]/RATIO);
			endPoint= new Vector2(_vertices[i+1][0]/RATIO, _vertices[i+1][1]/RATIO);
			((EdgeShape)shape).set(startPoint, endPoint);
			body.createFixture(fixtureDef);
		}
		shape.dispose();
		shape = null;
		return this;
	}

}