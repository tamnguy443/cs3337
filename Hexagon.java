import javafx.geometry.Point2D;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hexagon 
{	
	private Point2D center; //coordinates of shared vertex for all 6 triangle
	private Point2D[] vertices; //each triangle has a unique combination of two hexagon vertices
	private double scale; //distance between center and vertices
	private Color[] triColors;
	private int rotation; //ranges from 0 to 5
	
	public Hexagon(Color[] colors) 
	{
		this.triColors = colors;
		this.rotation = 0;
		this.center = new Point2D(0, 0);
		this.vertices = new Point2D[6];
	}		
	
	public Point2D getCenter()
	{
		return this.center;
	}
	
	public void setCenter(double x, double y)
	{
		this.center = new Point2D(x, y);
	}
	
	public void setVertices(List<Double> vertices)
	{
		for(int i = 0; i < 6; i++)
		{
			this.vertices[i] = new Point2D(vertices.get(2 * i), vertices.get(2 * i + 1));
		}
	}
	
	public Color colorAt(int pos)
	{
		if(pos < 0 || pos >= 6) {return Color.WHITE;}		
		return this.triColors[correctForRotation(pos)];
	}
	
	public List<Double> constructTriangles()
	{
		List<Double> tris = new ArrayList<Double>();
		for(int i = 0; i < 6; i++)
		{
			Point2D vertex1 = vertices[i];
			Point2D vertex2 = vertices[(i + 1) % 6];
			tris.addAll(Arrays.asList(center.getX(), center.getY(), 
					vertex1.getX(), vertex1.getY(),
					vertex2.getX(), vertex2.getY()));			
		}
		return tris;
	}
	
	private int correctForRotation(int pos0)
	{
		return pos0 - rotation;
	}
	
}
