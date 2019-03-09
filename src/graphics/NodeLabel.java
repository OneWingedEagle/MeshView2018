package graphics;
import math.Mat;
import math.Vect;
import math.util;

import java.awt.Color;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedLineArray;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.IndexedTriangleArray;
import javax.media.j3d.LineArray;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.RenderingAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Text2D;

import javafx.scene.Node;



public class NodeLabel extends TransformGroup{
	//Text2D idLabel;
	Shape3D idLabel;
	int id;
	public NodeLabel(int id){
		
		int size=12;
		
		String ss=Integer.toString(id);
		String[] digits=ss.split("");


		int nDigits=ss.length();
	
		//idLabel= new Text2D(Integer.toString(id), new Color3f(Color.black), "Arial", size, 0);
 
	   // addChild(idLabel);
		
		int nPoints0=6;
		int nLines0=7;
		int vertexCount=nPoints0*nDigits;
		int lineCount=nLines0*nDigits;
		
		//IndexedLineArray arrows=new IndexedLineArray(1*N,GeometryArray.COORDINATES  |
				// GeometryArray.COLOR_3,2*N);
		IndexedLineArray arrows=new IndexedLineArray(vertexCount+1,GeometryArray.COORDINATES  |
				 GeometryArray.COLOR_3,2*lineCount);
		

			arrows.setCapability(GeometryArray.ALLOW_COLOR_WRITE);
			arrows.setCapability(GeometryArray.ALLOW_COORDINATE_WRITE);

			double x0=.001;
			double d=.02;
			double dd=.03;
			P3f[] coords=new P3f[vertexCount];

			for(int i=0;i<nDigits;i++){
			double shift=x0+i*dd;
			coords[i*nPoints0+0]=new P3f(0+shift,0,0);
			coords[i*nPoints0+1]=new P3f(d+shift,.0,0);
			coords[i*nPoints0+2]=new P3f(0+shift,d,0);
			coords[i*nPoints0+3]=new P3f(d+shift,d,0);
			coords[i*nPoints0+4]=new P3f(0+shift,2*d,0);
			coords[i*nPoints0+5]=new P3f(d+shift,2*d,0);
			}


			int[] edgeColorIndices=new int[vertexCount];	
			Color3f[] colors= new Color3f[vertexCount];
			for(int k=0;k<vertexCount;k++){
			colors[k]=new Color3f(Color.black);
			edgeColorIndices[k]=k;
			}
			
			int[] edgeCoordIndices=new int[2*lineCount];	
			

			int ix=0;
			for(int i=0;i<nDigits;i++){
				int shift=i*nPoints0;
				
				ix=i*2*nLines0;
	
			edgeCoordIndices[ix+0]=0+shift;
			edgeCoordIndices[ix+1]=1+shift;
			edgeCoordIndices[ix+2]=1+shift;
			edgeCoordIndices[ix+3]=3+shift;
			edgeCoordIndices[ix+4]=3+shift;
			edgeCoordIndices[ix+5]=5+shift;
			edgeCoordIndices[ix+6]=5+shift;
			edgeCoordIndices[ix+7]=4+shift;
			edgeCoordIndices[ix+8]=4+shift;
			edgeCoordIndices[ix+9]=2+shift;
			edgeCoordIndices[ix+10]=2+shift;
			edgeCoordIndices[ix+11]=0+shift;
			edgeCoordIndices[ix+12]=2+shift;
			edgeCoordIndices[ix+13]=3+shift;
			
			if(digits[i].equals("0")){
				edgeCoordIndices[ix+12]=0;
				edgeCoordIndices[ix+13]=0;
			}
			else if(digits[i].equals("1")){
				edgeCoordIndices[ix+0]=0;
				edgeCoordIndices[ix+1]=0;
				for(int k=6;k<14;k++)
				edgeCoordIndices[ix+k]=0;
			}
			else if(digits[i].equals("2")){
				edgeCoordIndices[ix+2]=0;
				edgeCoordIndices[ix+3]=0;
				
				edgeCoordIndices[ix+8]=0;
				edgeCoordIndices[ix+9]=0;
			}
			else if(digits[i].equals("3")){
				edgeCoordIndices[ix+8]=0;
				edgeCoordIndices[ix+9]=0;
				
				edgeCoordIndices[ix+10]=0;
				edgeCoordIndices[ix+11]=0;
			}
			else if(digits[i].equals("4")){
				edgeCoordIndices[ix+0]=0;
				edgeCoordIndices[ix+1]=0;
				
				edgeCoordIndices[ix+6]=0;
				edgeCoordIndices[ix+7]=0;
				edgeCoordIndices[ix+10]=0;
				edgeCoordIndices[ix+11]=0;
			}
			else if(digits[i].equals("5")){
				edgeCoordIndices[ix+4]=0;
				edgeCoordIndices[ix+5]=0;

				edgeCoordIndices[ix+10]=0;
				edgeCoordIndices[ix+11]=0;
			}
			else if(digits[i].equals("6")){
				edgeCoordIndices[ix+4]=0;
				edgeCoordIndices[ix+5]=0;
			}
			else if(digits[i].equals("7")){
				edgeCoordIndices[ix+0]=0;
				edgeCoordIndices[ix+1]=0;
				for(int k=8;k<14;k++)
					edgeCoordIndices[ix+k]=0;
			}
			else if(digits[i].equals("9")){
				edgeCoordIndices[ix+0]=0;
				edgeCoordIndices[ix+1]=0;
				edgeCoordIndices[ix+10]=0;
				edgeCoordIndices[ix+11]=0;
			}
			}
			
	arrows.setCoordinates(0, coords);		
	arrows.setCoordinateIndices(0, edgeCoordIndices);	
	arrows.setColors(0, colors);	
	arrows.setColorIndices(0, edgeColorIndices);		



	LineAttributes la=new LineAttributes(1.5f,LineAttributes.PATTERN_SOLID,false);
	
				
	Appearance app=new Appearance();

	
	app.setLineAttributes(la);
	
	
	
	//RenderingAttributes ra = new RenderingAttributes( );
	//ra.setCapability(RenderingAttributes.ALLOW_VISIBLE_WRITE);
	   
	//ra.setVisible(true);
	//app.setRenderingAttributes(ra);


	idLabel=new Shape3D(arrows,app);
	   
	addChild(idLabel);
	    
/*		Color3f color3=new Color3f(Color.red);

	double base=0,d=0.1;

		base=.1*scale.el[0];
		height=.4*scale.el[1];

	 LineArray head=new LineArray(14, GeometryArray.COORDINATES);

	 P3f[] vert=new P3f[6];

		vert[0]=new P3f(0,0,0);
		vert[1]=new P3f(d,.0,0);
		vert[2]=new P3f(0,d,0);
		vert[3]=new P3f(d,d,0);
		vert[4]=new P3f(0,2*d,0);
		vert[5]=new P3f(d,2*d,0);


		head.setCoordinate(0,vert[0]);
		head.setCoordinate(1,vert[1]);
		head.setCoordinate(2,vert[1]);
		head.setCoordinate(3,vert[3]);
		head.setCoordinate(4,vert[3]);
		head.setCoordinate(5,vert[5]);
		head.setCoordinate(6,vert[5]);
		head.setCoordinate(7,vert[4]);
		head.setCoordinate(8,vert[4]);
		head.setCoordinate(9,vert[2]);
		head.setCoordinate(10,vert[2]);
		head.setCoordinate(11,vert[0]);
		head.setCoordinate(12,vert[1]);
		head.setCoordinate(13,vert[3]);

	LineAttributes la=new LineAttributes(1.8f,LineAttributes.PATTERN_SOLID,false);
	ColoringAttributes ca=new  ColoringAttributes(color3, ColoringAttributes.SHADE_FLAT);
	Appearance app=new Appearance();

	ca.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);

	app.setLineAttributes(la);
	app.setColoringAttributes(ca);


	idLabel=new Shape3D(head,app);
	
	
	Color3f color3=new Color3f(Color.red);

double base=0,height=0;


	base=.1;
	height=.4;

 LineArray head=new LineArray(6, GeometryArray.COORDINATES);

 P3f[] vert=new P3f[4];

	vert[0]=new P3f(0,height,0);
	vert[1]=new P3f(-.3*base,.9*height,0);
	vert[2]=new P3f(.3*base,.9*height,0);
	vert[3]=new P3f(0,0,0);


	head.setCoordinate(0,vert[0]);
	head.setCoordinate(1,vert[1]);
	head.setCoordinate(2,vert[0]);
	head.setCoordinate(3,vert[2]);
	head.setCoordinate(4,vert[0]);
	head.setCoordinate(5,vert[3]);

LineAttributes la=new LineAttributes(1.8f,LineAttributes.PATTERN_SOLID,false);
ColoringAttributes ca=new  ColoringAttributes(color3, ColoringAttributes.SHADE_FLAT);
Appearance app=new Appearance();

ca.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);

app.setLineAttributes(la);
app.setColoringAttributes(ca);


//TransformGroup arrow=new TransformGroup();

idLabel=new Shape3D(head,app);
//arrow.addChild(arrowShape);
*/
	}
		
	public void setColor(Color color){

	}


}
