package Main;


import java.awt.Color;

public class Species {
	
	public boolean isAlive; //1:alive 0:dead
	public boolean isMature; //1: is mature, and can reproduce 0: is a newborn, and cant reproduce
	public int daysEaten; //how many days in a row this grass has been eaten. dies after 14 days
	public boolean isEaten; //1:this grass has already been eaten today 0:this grass has not been eaten today
	
	public boolean isFed; // 1:has eaten today 0:has not eaten today
    public int sinceFed; // time since last fed
    public boolean isPregnant; // 1:pregnant 0: not pregnant
    public int vision; // sight radius of the animal X^2+y^2 <= R^2
    public boolean targeting; // 1: has target 0:no target
	
	
	public int pregTimer; //how long until birth
	public int litterSize; //how many are born
	public int lifespan; //how long until death
	public int birthRadius; // max birthing radius of the animal in meters x^2+y^2 <=R^2
	public static int initial; //initial population
	public int rest; // how many hours of sleep it has
	public Color color = Color.white; //color of the animal (default: white)
	
	// Constructors:
	public Species(){
		isAlive = false;
		isMature = false;
	}
	
	// Suggested Methods:
	public Species[][] Mature(int a, int b, Species[][] array){
		array[a][b].isMature = true;
		return array;
		
	}
	
	public void Die(){
		isAlive = false;
	}
	
	
	
}
