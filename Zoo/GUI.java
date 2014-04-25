package Main;

import javax.swing.*; // For JPanel, etc.


import java.awt.*;           // For Graphics, etc.
import java.awt.geom.*;      // For Ellipse2D, etc.
import java.lang.Math.*;
import java.util.ArrayList;




@SuppressWarnings("unused")
public class GUI extends JPanel {
    
	
    private static final long serialVersionUID = 1L;
    public EventQueue[] queue = new EventQueue[25];
    public int i = 0;
    public Species[][] array = new Species[240][240];
    public int foxNo = Fox.initial;
    public int grassNo = Grass.initial;
    public int rabbitNo = Rabbit.initial;
    public int radiusSq;
    public int remainder;
    public int x;
    public int y;
    public int radius;
    

    


	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setSize(720,720);
		paintBorder(g);

		
		
	}
	
	public void paintBorder(Graphics g) {
	//	int width = getWidth();
	//	int height = getHeight();
		g.setColor(Color.black);
		g.drawOval(0, 0, 720, 720); //holds ~40000+ spaces
	}
	
	public void paintPoint(Color color, Graphics g, int a, int b) {
		g.setColor(color);
		g.fillRect(a*3,b*3,3,3); //center of the circle is (358,358) 
		//radius of the circle is 120 spaces
		//((x-120)^2+(y-120)^2) <= 14400
	}

	
}
