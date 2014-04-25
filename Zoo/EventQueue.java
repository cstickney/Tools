package Main;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class EventQueue implements Cloneable
{	
	public int counter = 0;
	public int[] x = new int[50000];
	public int[] y = new int[50000];
	public int[] animal = new int[50000];
	public int[] action = new int[50000];

	
	public EventQueue()
	{
		counter = 0;
		
		x[counter] = 0;
		y[counter] = 0;
		animal[counter] = 0;
		action[counter] = 0;
	}
		
		public void set(int a, int b , int c, int d)
		{
			x[counter] = a;
			y[counter] = b;
			animal[counter] = c;
			action[counter] = d;
			++counter;
		}
		
		
		
	}
	
	
