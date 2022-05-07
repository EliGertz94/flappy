package flappy;

import java.awt.Color;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.*;

public class main {

	static private final int FRAME_HIGHT = 600;
	static private final int FRAME_WIDTH = 700;
	
	
	
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		GamePlay gamePlay = new GamePlay();
		
		frame.setBounds(10,10,FRAME_WIDTH,FRAME_HIGHT);
		frame.setBackground(Color.DARK_GRAY);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gamePlay);
		frame.setVisible(true);
		

	}



		

}
