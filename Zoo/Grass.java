package Main;

import java.lang.Math.*;
import javax.swing.*; // For JPanel, etc.


import java.awt.*;           // For Graphics, etc.
import java.awt.geom.*;      // For Ellipse2D, etc.
import java.util.Random;

@SuppressWarnings("unused")
public class Grass extends Species {
	
	//VARIABLES:
	
	
	static int initial = 500; //initial population
	
	// CONSTRUCTORS:
	public Grass() {
		pregTimer = 30;
		litterSize = 100;
		lifespan = 30*8; //in days
		birthRadius = 10; //in meters //MAY NEED TWEAKING
		isAlive = true;
		color = Color.green;
		daysEaten = 0;
		isEaten = false;
		rest = 23;
	}
	
	
	
	
	
	// METHODS:
	
	public Event selector(int a, int b, int action, Species[][] array, EventQueue[] queue, int i){
		Event event= new Event(queue, array);
		if(action ==1){
			event= Idle(a,b,array,queue,i);
		}
		if(action ==2){
			event= Regenerate(a,b,array,queue,i);
		}
		
		return event;
	}
	
	public Species[][] Wilt(int a, int b, Species[][] array){ //state 4
		System.out.println("wilting");
		array[a][b].color = Color.YELLOW.darker().darker(); //appears as brown usually
		return array;
	}
	
	public Event Reproduce(int a, int b, Species[][] array, EventQueue[] queue ){ //state 3
		System.out.println("reproducing");
		int x,y,remainder,i=0;
		while(i<=litterSize-1){
			x = (int) (Math.random() * 2*birthRadius);
	        remainder = (int) Math.sqrt(((birthRadius*birthRadius) - ((x-birthRadius)*(x-birthRadius))));
	        
	        y = (int) ((int) (birthRadius-remainder)+(Math.random() *(2* remainder)));
	        x = x-birthRadius;
	        y=y-birthRadius;
	        x = a+x;
	        y=b+y; 
	        System.out.println("new grass attempt at ("+ (x-120) +","+ (y-120)+")");
	        if((x-120)*(x-120) + (y-120)*(y-120) < 14400 ){
	        	if(x > 1 && x < 239 && y > 1 && y < 239){
	        	
	        		if ( array[x][y].color == Color.white  && array[x-1][y].color != array[a][b].color && array[x+1][y].color != array[a][b].color && array[x][y-1].color != array[a][b].color && array[x][y+1].color != array[a][b].color &&array[x-1][y-1].color != array[a][b].color && array[x+1][y+1].color != array[a][b].color && array[x+1][y-1].color != array[a][b].color && array[x-1][y+1].color != array[a][b].color)
	        			{
	        	
	        			array[x][y] = new Grass();   
            		
	        			System.out.println("new grass at ("+ x +","+ y+")");
	        			queue[1].set(x, y, 1, 1);
	        			}
	        	}
	        }
		++i;
		}
		array[a][b].pregTimer = 30;
		
		Event event= new Event(queue, array);
		return event;
	}
	
	public Event Idle(int a, int b, Species[][] array, EventQueue[] queue, int i) //state 1
    {
//		Event ev2= Reproduce(a, b, array, queue);
//		queue = ev2.queue;
//		array = ev2.array;
        if (array[a][b].isAlive == false)
        {
        	array[a][b] = new Species();
        	Event event= new Event(queue, array);
        	return event;
        } 
        else {
            if (array[a][b].rest == 1){
            	queue[1].set(a,b,1,2);
            	array[a][b].rest = 23;           	
            }
            else{
            	--array[a][b].rest;
            	queue[1].set(a, b, 1, 1);
            }
        }
        Event event= new Event(queue, array);
        return event;
    }
	
	public Event Regenerate(int a, int b, Species[][] array, EventQueue[] queue, int i){ //resets isEaten state, triggers the other states //state 2
		array[a][b].lifespan--;
		if (array[a][b].isMature == false){
			if (array[a][b].lifespan == 30*6){
				array = Mature(a, b, array);
			}
		}
		if (array[a][b].lifespan <= 0){
			array = Wilt(a, b, array);
			Event event= new Event(queue, array);
			return event;
		}
		else if (array[a][b].isEaten == true){
			array[a][b].isEaten = false;
			array[a][b].daysEaten++;
			array[a][b].pregTimer = 30;
				if (array[a][b].daysEaten >= 14){
					array[a][b] = new Species();
					Event event= new Event(queue, array);
					return event;
				}
			}
			
			else {
				array[a][b].daysEaten = 0;
				if (array[a][b].pregTimer <= 0){
					System.out.println("point 6");
					Event ev= Reproduce(a, b, array, queue);
					queue = ev.queue;
					array = ev.array;
				}
				
				else if (array[a][b].isMature == true){
					array[a][b].pregTimer--;
				}
			}		
			queue[1].set(a, b, 1, 1);
			Event event= new Event(queue, array);
			return event;
		}
}


