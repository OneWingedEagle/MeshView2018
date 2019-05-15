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


public class IdLabel extends TransformGroup{
	//Text2D idLabel;
	Shape3D idLabel;
	int id;
	public IdLabel(int id){
		

		String ss=Integer.toString(id);
		String[] digits=ss.split("");


		int nDigits=ss.length();

		
		int nPoints0=6;
		int nLines0=7;
		int vertexCount=nPoints0*nDigits;
		int lineCount=nLines0*nDigits;

		IndexedLineArray arrows=new IndexedLineArray(vertexCount+1,GeometryArray.COORDINATES  |
				 GeometryArray.COLOR_3,2*lineCount);
		

			arrows.setCapability(GeometryArray.ALLOW_COLOR_WRITE);
			arrows.setCapability(GeometryArray.ALLOW_COORDINATE_WRITE);

			double x0=.0;
			double y0=.0;
			double z0=.0001;
			double d=.02;
			double dd=.03;
			P3f[] coords=new P3f[vertexCount];

			for(int i=0;i<nDigits;i++){
			double shift=x0+(i-nDigits/2)*dd;
			coords[i*nPoints0+0]=new P3f(0+shift,y0,z0);
			coords[i*nPoints0+1]=new P3f(d+shift,y0,z0);
			coords[i*nPoints0+2]=new P3f(0+shift,d+y0,z0);
			coords[i*nPoints0+3]=new P3f(d+shift,d+y0,z0);
			coords[i*nPoints0+4]=new P3f(0+shift,2*d+y0,z0);
			coords[i*nPoints0+5]=new P3f(d+shift,2*d+y0,z0);
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
	

	idLabel=new Shape3D(arrows,app);
	   
	addChild(idLabel);

	}
		
	public void setColor(Color color){

	}


}
