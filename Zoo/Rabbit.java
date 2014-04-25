package Main;

import java.awt.Color;
import java.lang.Math;


public class Rabbit extends Species
{
    
    
    static int initial = 500;
    
    // Constructor:
    public Rabbit()
    {
        isAlive = true;
        isFed = false;
        sinceFed = 0; // dies at 4
        isPregnant = false;
        rest = 12;
        pregTimer = 20; // in days //MAY NEED TWEAKING
        litterSize = 8;
        vision = 4;
        lifespan = 2000; // MATURES AT 1850
        birthRadius = 3;
        color = Color.BLACK;
    }
    
    // Methods:
    // eat
    	//look for food
    	//check range
    // mate
    	//look for mate
    	//check range
    
    public Event selector(int a, int b, int action, Species[][] array, EventQueue[] queue, int i){
    	//System.out.println("hit rabbit selector");
		Event event= new Event(queue, array);
		if(action ==1){
			event= Feed(a,b,array,queue,i); //feed
		}
		if(action ==2){
			event= Mate(a,b,array,queue,i); //mate
		}
		if(action ==3){
			event= Idle(a,b,array,queue,i);
		}
		if(action == 4){
			event= asleep(a,b,array,queue,i);
		}
		
		return event;
	}
 
    public Event Move(int a, int b, Species[][] array, EventQueue[] queue, boolean targeting){
    	System.out.println("hit rabbit move");
    	
        if (targeting == false){
        	int move = 2;
            int x = move - (int) (Math.random() * 4);
            int y = move - (int) (Math.random() * 4);
            x = a+x;
            y = b+y;
            //System.out.println("moving to = "+x+","+y);
            if((x-120)*(x-120) + (y-120)*(y-120) < 14400 ){
	        	if(x > 1 && x < 239 && y > 1 && y < 239){
	        		if (array[x][y].color == Color.white){
            			array[x][y] = new Rabbit();
            			array[x][y].isFed = array[a][b].isFed;
            			array[x][y].sinceFed = array[a][b].sinceFed;
            			array[x][y].rest = array[a][b].rest;
            			array[x][y].pregTimer = array[a][b].pregTimer;
            			array[x][y].lifespan = array[a][b].lifespan;
            			array[x][y].isPregnant = array[a][b].isPregnant;
            			array[a][b] = new Species();
            			
            			
            			System.out.println("movedto"+x+","+y);
            			if (array[x][y].isFed == false){
                        	queue[1].set(x, y, 2, 1);
                        	System.out.println("move moved from"+a+","+b +"to"+x +"," +y);
                        	System.out.println("move scheduled feed on"+x +"," +y);
                        	Event event= new Event(queue, array);
                        	return event;  
                        }
                        else if (array[x][y].isMature == false && array[x][y].isPregnant == false){
                        	queue[1].set(x, y, 2, 2);
                        	System.out.println("move moved from"+a+","+b +"to"+x +"," +y);
                        	System.out.println("move scheduled idle on"+x +"," +y);
                        	Event event= new Event(queue, array);
                        	return event;  
                        }
                        else
                        		queue[1].set(x, y, 2, 3);
            					System.out.println("move moved from"+a+","+b +"to"+x +"," +y);
            					System.out.println("move scheduled idle on"+x +"," +y+"SHOULDNT HIT HERE");
            					Event event= new Event(queue, array);
            			    	return event;  
                    	}
            		}	
	        	}	
	        }
            
        if (array[a][b].isFed == false){
        	queue[1].set(a, b, 2, 1);
        	System.out.println("move scheduled feed on"+a +"," +b);
        }
        else if (array[a][b].isMature == false && array[a][b].isPregnant == false){
        	queue[1].set(a, b, 2, 2);
        	System.out.println("move scheduled feed on"+a +"," +b);
        }
        else{
        		queue[1].set(a, b, 2, 3);
        		System.out.println(" move scheduled feed on"+a +"," +b+"shouldnt hit here either");
        }
        Event event= new Event(queue, array);
    	return event;  
    }

    public Event moveTo(int a, int b, int c, int d, Species[][] array, EventQueue[] queue){
    	System.out.println("hit rabbit moveto");
            int oldx = a;
            int oldy = b;
            int dx = c;
            int dy = d;
            int x;
            int y;
            
            if (dx<-2){
            	dx = -2;
            }
            if (dx>2){
            	dx = 2;
            }
            if (dy<-2){
            	dy = -2;
            }
            if (dy>2){
            	dy = 2;
            }
            
            if(a+dx >= 240||a-dx<=0||b+dy>=240||b-dy<=0){
            	
				System.out.println("moveto scheduled feed on"+oldx +"," +oldy);
            	queue[1].set(oldx, oldy, 2, 1);
            	Event event= new Event(queue, array);
            	return event;
            }
            
            if (array[a+dx][b+dy].color == Color.white){
            	x=a+dx;
            	y=b+dy;
				System.out.println("moveto is targeting"+x +"," +y+"case 1");
            }
            else if (array[a+dx/2][b+dy].color == Color.white){
            	x=a+dx/2;
            	y=b+dy;
            	System.out.println("moveto is targeting"+x +"," +y+"case 2");
            }
            else if (array[a+dx][b+dy/2].color == Color.white){
            	x=a+dx;
            	y=b+dy/2;
            	System.out.println("moveto is targeting"+x +"," +y+"case 3");
            }
            else if (array[a+dx/2][b+dy/2].color == Color.white){
            	x=a+dx/2;
            	y=b+dy/2;
            	System.out.println("moveto is targeting"+x +"," +y+"case 4");
            }
            else if (array[a][b+dy].color == Color.white){
            	x=a;
            	y=b+dy;
            	System.out.println("moveto is targeting"+x +"," +y+"case 5");
            }
            else if (array[a][b+dy/2].color == Color.white){
            	x=a;
            	y=b+dy/2;
            	System.out.println("moveto is targeting"+x +"," +y+"case 6");
            }
            else if (array[a+dx][b].color == Color.white){
            	x=a+dx;
            	y=b;
            	System.out.println("moveto is targeting"+x +"," +y+"case 7");
            }
            else if (array[a+dx/2][b].color == Color.white){
            	x=a+dx/2;
            	y=b;
            	System.out.println("moveto is targeting"+x +"," +y+"case 7");
            }
            else {
            	System.out.println("moveto failed to acquire a target");
				System.out.println("moveto scheduled feed on"+oldx +"," +oldy);
            	queue[1].set(oldx, oldy, 2, 1);
            	Event event= new Event(queue, array);
            	return event;  
            }
            
            
            
            System.out.println("moving to = "+x+","+y);
            array[x][y] = new Rabbit();
            array[x][y].isFed = array[oldx][oldy].isFed;
            array[x][y].sinceFed = array[oldx][oldy].sinceFed;
            array[x][y].rest = array[oldx][oldy].rest;
            array[x][y].pregTimer = array[oldx][oldy].pregTimer;
            array[x][y].lifespan = array[oldx][oldy].lifespan;
            array[oldx][oldy] = new Species();
            
            System.out.println("moveto moved from"+oldx+","+oldy +"to"+x +"," +y);
			System.out.println("moveto scheduled feed on"+x +"," +y);	
            queue[1].set(x, y, 2, 1);
           
 
        Event event= new Event(queue, array);
    	return event;  
    }
    
    public Event Idle(int a, int b, Species[][] array, EventQueue[] queue, int i) //state 3
    {
//    	Event ev2= Reproduce(a, b, array, queue);
//		queue = ev2.queue;
//		array = ev2.array;
    	System.out.println("hit rabbit idle");
    	if (array[a][b].isAlive == false){
        	array[a][b] = new Species();
        	Event event= new Event(queue, array);
        	return event;
        } 
        else
        {   
        	System.out.println("idling");
        	array[a][b].targeting = false;
            --array[a][b].rest;
            if (array[a][b].rest <= 0){
                Event ev= Sleep(a, b, array, queue, i); 
                return ev;
            }
            else{
            	int rand = (int) (Math.random() * 240);
            	rand = rand%2;
            	if (rand == 0){
            		Event ev= Move(a,b,array,queue,array[a][b].targeting);
            		queue = ev.queue;
					array = ev.array;
            	}
            	else {
            		queue[1].set(a, b, 2, 3);
            		
            	}
            }
        }
        
    	Event event= new Event(queue, array);
		return event;
   
    }
    public Event asleep(int a, int b, Species[][] array, EventQueue[] queue, int i) //state 5
    {
    	
    	System.out.println("hit rabbit asleep");
    	if (array[a][b].isAlive == false){
        	array[a][b] = new Species();
        	Event event= new Event(queue, array);
        	return event;
    	}
    	else if(array[a][b].rest == 11){
    		++array[a][b].rest;
    		queue[1].set(a, b, 2, 1);
    		Event event= new Event(queue, array);
    		return event;
    	}
    	else{
    		++array[a][b].rest;
    		queue[1].set(a, b, 2, 4);
    		Event event= new Event(queue, array);
    		return event;
    	}
    }
    
    public Event Feed(int a, int b, Species[][] array, EventQueue[] queue, int i){ //state 1
    	System.out.println("hit rabbit feed");
    	if (array[a][b].isAlive == false){
        	array[a][b] = new Species();
        	Event event= new Event(queue, array);
        	return event;
        } 
        else{
            --rest;
            if (array[a][b].rest <= 0){
                Event ev= Sleep(a, b, array, queue, i);
                return ev;
            }
            else{
            	Point target = isNearFood(a,b,array);
            	int dx = Math.abs(a-target.x);
            	int dy = Math.abs(b-target.y);
            	System.out.println(target.x+" "+target.y);
            	

                if (dx+dy == a+b){
                	System.out.println("FEED FAILED, WANDERING");
                    targeting = false;
                    Event ev = Move(a, b, array, queue, targeting);
                    queue = ev.queue;
					array = ev.array;
                    //queue[1].set(a, b, 2, 1);
                } 
                else if(dx+dy <=2){
                	System.out.println("FEED SUCCEEDED");
                	array[target.x][target.y].isEaten = true;
                	array[a][b].isFed = true;
                        if (array[a][b].isMature == true && array[a][b].isPregnant == false){
                        	queue[1].set(a, b, 2, 2);
                        }
                        else{
                        	queue[1].set(a, b, 2, 3);
                        }
                    } 
                    else{
                    	targeting = true;
                        Event ev = moveTo(a,b,dx,dy,array,queue);
                        return ev;
                    }
                }
            }
    	Event event= new Event(queue, array);
    	return event;
        }
    
    public Event Mate(int a, int b, Species[][] array, EventQueue[] queue, int i){ //state 1
    	System.out.println("hit rabbit mate");
    	if (array[a][b].isAlive == false){
        	array[a][b] = new Species();
        	Event event= new Event(queue, array);
        	return event;
        } 
        else{
            --rest;
            if (array[a][b].rest <= 0){
                Event ev= Sleep(a, b, array, queue, i);
                return ev;
            }
            else{
            	Point target = isNearMate(a,b,array);
            	int dx = Math.abs(a-target.x);
            	int dy = Math.abs(b-target.y);
            	System.out.println(target.x+" "+target.y);
            	

                if (dx+dy == a+b){
                	System.out.println("MATE FAILED, WANDERING");
                    targeting = false;
                    Event ev = Move(a, b, array, queue, targeting);
                    queue = ev.queue;
					array = ev.array;
                    //queue[1].set(a, b, 2, 1);
                } 
                else if(dx+dy <=2){
                	System.out.println("GOT LAID!");
                	array[a][b].isPregnant = true;
                    queue[1].set(a, b, 2, 3);
                       
                    } 
                    else{
                    	targeting = true;
                        Event ev = moveTo(a,b,dx,dy,array,queue);
                        return ev;
                    }
                }
            }
    	Event event= new Event(queue, array);
    	return event;
        }
    
    public boolean checker(int a, int b, Species[][] array){
    	if(a>0 && a<240 && b>0 && b<240){
    		//System.out.println("checking");
    		if(array[a][b].color == Color.green){
    			//System.out.println("shits green yo");
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean checker2(int a, int b, Species[][] array){
    	if(a>0 && a<240 && b>0 && b<240){
    		//System.out.println("checking");
    		if(array[a][b].color == Color.BLACK){
    			return true;
    		}
    	}
    	return false;
    }
    
    public Point isNearFood(int a, int b, Species[][] array){
    	System.out.println("hit rabbit isnearfood");
        int radius = array[a][b].vision;
    	int counter=0;
    	int x;
    	int y;
    	int i=1;
    	int j=0;
    	Point[] point = new Point[(4*radius)];
    	
    	while (j<=(4*radius)-1){
        	point[j] = new Point(0,0);
    		++j;
        }
    	
    	
    	
    	while(i<=radius){
    		x=0;
    		y=i;
    		while(x<i){
    			if(checker(a+x,b+y,array)){
    				//System.out.println(x+","+y +"="+point[counter].x+","+point[counter].y );
    				point[counter].x = a+x;
    				point[counter].y = b+y;
    				++counter;
    			}
    			--y;
    			++x;
    			//System.out.println(x+","+y +"="+point[counter].x+","+point[counter].y );
    		}
    		while(y>-i){
    			if(checker(a+x,b+y,array)){
    				//System.out.println(x+","+y +"="+point[counter].x+","+point[counter].y );
    				point[counter].x = a+x;
    				point[counter].y = b+y;
    				++counter;
    			}
    			--y;
    			--x;
    			//System.out.println(x+","+y +"="+point[counter].x+","+point[counter].y );
    		}
    		
    		while(x>-i){
    			if(checker(a+x,b+y,array)){
    				//System.out.println(x+","+y +"="+point[counter].x+","+point[counter].y );
    				point[counter].x = a+x;
    				point[counter].y = b+y;
    				++counter;
    			}
    			++y;
    			--x;
    			//System.out.println(x+","+y +"="+point[counter].x+","+point[counter].y );
    		}
    		while(y<i){
    			if(checker(a+x,b+y,array)){
    				//System.out.println(x+","+y +"="+point[counter].x+","+point[counter].y );
    				point[counter].x = a+x;
    				point[counter].y = b+y;
    				++counter;
    			}
    			++y;
    			++x;
    			//System.out.println(x+","+y +"="+point[counter].x+","+point[counter].y );
    		}
    		
    		
    		if(counter > 0){
    			//System.out.println("found target grass");
    			int rand = (int) (Math.random() * counter);
    			//System.out.println(rand+ "found one"+ point[rand].x);
    			return point[rand];
    		}
    		++i;
    		
    		
    	}
    	point[0].x = 0;
		point[0].y = 0;
    	
    	return point[0];
    	
    	
    }

    public Point isNearMate(int a, int b, Species[][] array){
    	System.out.println("hit rabbit isnearmate");
        int radius = array[a][b].vision;
    	int counter=0;
    	int x;
    	int y;
    	int i=1;
    	int j=0;
    	Point[] point = new Point[(4*radius)];
    	
    	while (j<=(4*radius)-1){
        	point[j] = new Point(0,0);
    		++j;
        }
    	
    	
    	
    	while(i<=radius){
    		x=0;
    		y=i;
    		while(x<i){
    			if(checker2(a+x,b+y,array)){
    				//System.out.println(x+","+y +"="+point[counter].x+","+point[counter].y );
    				point[counter].x = a+x;
    				point[counter].y = b+y;
    				++counter;
    			}
    			--y;
    			++x;
    			//System.out.println(x+","+y +"="+point[counter].x+","+point[counter].y );
    		}
    		while(y>-i){
    			if(checker2(a+x,b+y,array)){
    				//System.out.println(x+","+y +"="+point[counter].x+","+point[counter].y );
    				point[counter].x = a+x;
    				point[counter].y = b+y;
    				++counter;
    			}
    			--y;
    			--x;
    			//System.out.println(x+","+y +"="+point[counter].x+","+point[counter].y );
    		}
    		
    		while(x>-i){
    			if(checker2(a+x,b+y,array)){
    				//System.out.println(x+","+y +"="+point[counter].x+","+point[counter].y );
    				point[counter].x = a+x;
    				point[counter].y = b+y;
    				++counter;
    			}
    			++y;
    			--x;
    			//System.out.println(x+","+y +"="+point[counter].x+","+point[counter].y );
    		}
    		while(y<i){
    			if(checker2(a+x,b+y,array)){
    				//System.out.println(x+","+y +"="+point[counter].x+","+point[counter].y );
    				point[counter].x = a+x;
    				point[counter].y = b+y;
    				++counter;
    			}
    			++y;
    			++x;
    			//System.out.println(x+","+y +"="+point[counter].x+","+point[counter].y );
    		}
    		
    		
    		if(counter > 0){
    			//System.out.println("found target grass");
    			int rand = (int) (Math.random() * counter);
    			//System.out.println(rand+ "found one"+ point[rand].x);
    			return point[rand];
    		}
    		++i;
    		
    		
    	}
    	point[0].x = 0;
		point[0].y = 0;
    	
    	return point[0];
    	
    	
    }
   
    public Event Sleep(int a, int b, Species[][] array, EventQueue[] queue, int i){//state 4
    	System.out.println("hit rabbit sleep");
    	//System.out.println("going to sleep");
    	if (array[a][b].isAlive == false){
       		array[a][b] = new Species();
       		Event event= new Event(queue, array);
       		return event;
    	} 
    	
        --array[a][b].lifespan;
        if (array[a][b].lifespan <= 0){
        	array[a][b] = new Species();
           	Event event= new Event(queue, array);
           	return event;
        }
        
        if (array[a][b].lifespan == 1850){
            Mature(a, b, array);
        }
        
        if (array[a][b].isPregnant == true){
            --array[a][b].pregTimer;
            if (array[a][b].pregTimer <= 0){
            	Event ev= Reproduce(a, b, array, queue);
				queue = ev.queue;
				array = ev.array;
				array[a][b].isPregnant = false;
            }
        }
        System.out.println(array[a][b].isFed);
        if (array[a][b].isFed == false){
            ++array[a][b].sinceFed;
            
            if (array[a][b].sinceFed >= 4){
            	array[a][b] = new Species();
               	Event event= new Event(queue, array);
               	return event;
            }
            if (array[a][b].isPregnant == true){
            	array[a][b].isPregnant = false;
            }
        }
        else{
        	array[a][b].sinceFed = 0;
        	array[a][b].isFed = false;
        }
        queue[1].set(a, b, 2, 4);
        Event event= new Event(queue, array);
       	return event;
    }
   public Event Reproduce(int a, int b, Species[][] array, EventQueue[] queue ){ 
	   System.out.println("hit rabbit reproduce");
		//System.out.println("reproducing");
		int x,y,remainder,i=0;
		while(i<=litterSize-1){
			x = (int) (Math.random() * 2*birthRadius);
	        remainder = (int) Math.sqrt(((birthRadius*birthRadius) - ((x-birthRadius)*(x-birthRadius))));
	        
	        y = (int) ((int) (birthRadius-remainder)+(Math.random() *(2* remainder)));
	        x = x-birthRadius;
	        y=y-birthRadius;
	        x = a+x;
	        y=b+y; 
	        System.out.println("new rabbit attempt at ("+ (x-120) +","+ (y-120)+")");
	        if((x-120)*(x-120) + (y-120)*(y-120) < 14400 ){
	        	if(x > 1 && x < 239 && y > 1 && y < 239){
	        	
	        		if ( array[x][y].color == Color.white  && array[x-1][y].color != array[a][b].color && array[x+1][y].color != array[a][b].color && array[x][y-1].color != array[a][b].color && array[x][y+1].color != array[a][b].color &&array[x-1][y-1].color != array[a][b].color && array[x+1][y+1].color != array[a][b].color && array[x+1][y-1].color != array[a][b].color && array[x-1][y+1].color != array[a][b].color)
	        			{
	        	
	        			array[x][y] = new Rabbit();   
           		
	        			System.out.println("new Rabbit at ("+ x +","+ y+")");
	        			queue[1].set(x, y, 2, 1);
	        			}
	        	}
	        }
		++i;
		}
		array[a][b].pregTimer = 20;
		Event event= new Event(queue, array);
		return event;
	}
}
