package Main;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

@SuppressWarnings("unused")
public class Ecosystem {
	
	
	public Ecosystem() {
		
		
		initFrame();
		
	}
	
	public void initFrame() {
		JFrame frame = new JFrame("The Steppes");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new GUI());
		frame.setSize(740, 760);
		frame.setVisible(true);
		
		
	}
}
