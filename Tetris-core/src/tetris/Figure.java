package tetris;

import java.applet.*;
import java.awt.*;
import java.util.*;


class Figure {
	static int x=5/2+1, y=1; 
		int	c[]=new int[4];
	static Random r = new Random();

	Figure()
	{	
		x = 5/2+1;
		y = 1;
		c[0] = 0;
		c[1] = (int)(Math.abs(r.nextInt())%5+1);
		c[2] = (int)(Math.abs(r.nextInt())%5+1);
		c[3] = (int)(Math.abs(r.nextInt())%5+1);
		_data = new int[][] {
			{c[1],0,0,0},	
			{c[2],0,0,0},	
			{c[3],0,0,0},	
			{0,0,0,0},
		};
	}

	
	
	
	int[][] _data;

//	public Figure() {
//		_data = new int[4][4];
//	}

	public int[][] getData() {
		return _data;
	}

	public int[] getColor(){
		return c;
	}
	
//	public static Figure randomFigure() {
//		Figure figure = new Figure();
//		figure._data = FIGURES[random.nextInt(FIGURES.length)];
//		return figure;
//	}

}
