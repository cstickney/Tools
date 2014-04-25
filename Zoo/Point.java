package Main;

import java.util.Random;

public class Point {
     public int x;
     public int y;
     
     public Point (int a, int b) {
    	 x = a;
    	 y = b;  	 
     }


     public double getDistance (Point other) {

    	 double dist = Math.sqrt(Math.pow((other.getX() - this.getX()),2)+ Math.pow((other.getY() - this.getY()),2));

    	 return dist;
     }

     public int getX() {
    	 return x;
     }	

     public void setX(int x) {
    	 this.x = x;
     }

     public int getY() {
    	 return y;
     }

     public void setY(int y) {
    	 this.y = y;
     }

     public void move() {

    	 Random rn = new Random(System.nanoTime());
    	 int maximum = 50;
    	 int minimum = -50;
    	 int range = maximum - minimum + 1;
    	 int xoffset = rn.nextInt(range) + minimum;
    	 int yoffset = rn.nextInt(range) + minimum;

    	 if(this.getX() + xoffset < 0 || this.getX() + xoffset > 50 )
    		 x = this.getX();
    	 else
    		 x = this.getX() + xoffset;

    	 if(this.getY() + yoffset < 0 || this.getY() + yoffset > 50)
    		 y = this.getY();
    	 else
    		 y = this.getY() + yoffset;
     }


     public void evade(Point other){

    	 Random rn = new Random(System.nanoTime());

    	 int maximum = 20*3579139;
    	 int minimum = -20*3579139;
    	 int range = maximum - minimum + 1;
    	 int xoffset = rn.nextInt(range) + minimum;
    	 int yoffset = rn.nextInt(range) + minimum;

    	 if(this.getX() + xoffset < 0 || this.getX() + xoffset > 2147483647/2 )
    		 x = this.getX();
    	 else
    		 x = this.getX() + xoffset;

    	 if(this.getY() + yoffset < 0 || this.getY() + yoffset > 2147483647/2)
    		 y = this.getY();
    	 else
    		 y = this.getY() + yoffset;
     }


     public void hunt(Point other){

    	 int yoffset;
    	 int xoffset;
    	 int maximum = 3*3579139;
    	 int center = 3*3579139;

    	 if (other.getX() > this.getX()) {
    		 Random rn = new Random(System.nanoTime());
    		 int range = maximum;
    		 xoffset = center + (rn.nextInt(range));
    		 x = this.getX() + xoffset;
    	 } else {
    		 Random rn = new Random(System.nanoTime());
    		 int range = maximum;
    		 xoffset = center + (rn.nextInt(range));
    		 x = this.getX() - xoffset;
    	 }
    	 if (other.getY() > this.getY()) {
    		 Random rn = new Random(System.nanoTime());
    		 int range = maximum;
    		 yoffset = center + (rn.nextInt(range));
    		 y = this.getY() + yoffset;
    	 } else {
    		 Random rn = new Random(System.nanoTime());
    		 int range = maximum;
    		 yoffset = center + (rn.nextInt(range));
    		 y = this.getY() - yoffset;
    	 }
     }

     public void friends(Point other){
    	 int yoffset;
    	 int xoffset;
    	 int maximum = 1*3579139;
    	 int center = 1*3579139;

    	 if (other.getX() > this.getX()) {
    		 Random rn = new Random(System.nanoTime());
    		 int range = maximum;
    		 xoffset = center + (rn.nextInt(range));
    		 x = this.getX() + xoffset;
    	 } else {
    		 Random rn = new Random(System.nanoTime());
    		 int range = maximum;
    		 xoffset = center + (rn.nextInt(range));
    		 x = this.getX() - xoffset;
    	 }
    	 if (other.getY() > this.getY()) {
    		 Random rn = new Random(System.nanoTime());
    		 int range = maximum;
    		 yoffset = center + (rn.nextInt(range));
    		 y = this.getY() + yoffset;
    	 } else {
    		 Random rn = new Random(System.nanoTime());
    		 int range = maximum;
    		 yoffset = center + (rn.nextInt(range));
    		 y = this.getY() - yoffset;
    	 }
     }

     public void kill(Point other){
    	 x = other.getX();
    	 y = other.getY();
     }
}
