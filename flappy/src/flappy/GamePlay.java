package flappy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;



public class GamePlay extends JPanel implements KeyListener,ActionListener{

	
	
	private final int Bird_H = 40;
	private final int Bird_W = 40;
	
	private final int BirdLocationX= 230;
	private int BirdLocationY= 250;
	
	private int BirdDirectionY =0;

	
	Timer timer;
	private final int Delay = 45;
	
	Boolean started =true;
	
	
	
	//Column
	
	private  int Column_H1 = 200;
	private  int Column_H2 = 400;
	
	private final int Column_W = 50;
	
	private int ColumnXLocation= 650;
	
	
	private int columSpeed =10;
	
	private static int Spread = 100;
	
	
	
	private int score =0;
		
	private Image img;
	
	private int colHight[]= {250,300,340,150,120};	
	
	Random NUM;
	
    private ArrayList<Rectangle> rects;

	
	private int SpecialEffect = 3;
	private boolean Assfire =false;

	
	public GamePlay() {
		
        rects = new ArrayList<Rectangle>();

		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		 try {
			img = ImageIO.read(new File("sticker,375x360.u2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		timer = new Timer(Delay,this);
		timer.start();
		
		
		
		NUM = new Random();
		
		
	}
	
	@Override
	public void paint(Graphics g) 
	{
		
		//background - sky
		g.setColor(new Color(173,216,230));
		g.fillRect(0, 0, 700, 500);
				

		//ground
				
		g.setColor(new Color(200, 157, 124));
		g.fillRect(0, 500, 700, 100);
				
		
				
		if(started) {
			//score
		g.setColor(Color.red);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		g.drawString("Score: "+score, 20, 30);
		
		paintBird(g);
		paintColum(g);
		
	
		
		}else {
			g.setColor(Color.red);
			
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
			g.drawString("Your Score: " + score, 160, 200);
			
			
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
			g.drawString("Press SPACE To Start Press UP To Jump" , 160, 250);
			
			
			
		}
		g.dispose();
		
	}
	
	
	public void paintBird(Graphics g) 
	{

			
		g.setColor(Color.red);
        g.fillRect(BirdLocationX-25, BirdLocationY, 25, 25);
        g.setColor(Color.orange);
        g.fillRect(BirdLocationX-29, BirdLocationY+3, 20, 20);
        g.setColor(Color.yellow);
        g.fillRect(BirdLocationX-32, BirdLocationY+5, 15, 15);
        
        g.drawImage(img, BirdLocationX, BirdLocationY-10, Bird_W, Bird_H, null);
        

		//g.fillRect(BirdLocationX, BirdLocationY, Bird_W, Bird_H);
		
	}
	

	
	public void paintColum(Graphics g) 
	{

			
		g.setColor(new Color(0,100,0));
		
		g.fillRect(ColumnXLocation, 0, Column_W, Column_H1);
		g.fillRect(ColumnXLocation, Column_H1+Spread, Column_W, Column_H2);
		
	
		
		
		
	}
	
	
	public void coluMove() 
	{
		if(ColumnXLocation<=-100) 
		{
			ColumnXLocation=700;
			Column_H1=colHight[NUM.nextInt(4)];	
			
		}else 
		{
			ColumnXLocation-=columSpeed;
		}
		
		
	}
	
	
	
	private void reset()
	{
		BirdLocationY= 250;
		started=true;
		score=0;
		BirdDirectionY =0;
		ColumnXLocation= 650;
		columSpeed =10;
		
	}
	
	private void gameOver()
	{
		
		started=false;
	}

	
	private void Collisions()	
	{

		if(BirdLocationY>=550||BirdLocationY<=0) 
		{
			gameOver();
		}
		
		Rectangle Bird = new Rectangle(BirdLocationX, BirdLocationY, Bird_W, Bird_H);	
		Rectangle col1 = new Rectangle(ColumnXLocation, 0, Column_W, Column_H1);
		Rectangle col2 = new Rectangle(ColumnXLocation, Column_H1+Spread, Column_W, Column_H2);
		
		
		if(Bird.intersects(col1)||Bird.intersects(col2)) 
		{
			
			gameOver();
			
			
		}
		
	}
	
	



	@Override
	public void actionPerformed(ActionEvent e) {
		
		timer.restart();
		
		
		Collisions();
		
		if(started){
			coluMove();
			score++;
			BirdLocationY+=BirdDirectionY;
			BirdDirectionY+=1;
		}
		
		
//		if(score>increaseSpeed) 
//		{
//			columSpeed+=5;
//			increaseSpeed+=50;
//		}
		

		repaint();
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyPressed(KeyEvent e) {
		

		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_SPACE:
		if(!started)
		{
			reset();
		}
			
				
			break;
			
		case KeyEvent.VK_UP:
			
			if(started) BirdDirectionY = -8 ;
			
					
				break;
					
		case KeyEvent.VK_RIGHT:
				
				if(SpecialEffect >0)
				{ColumnXLocation -= 200 ;
				SpecialEffect--;
				Assfire  = true;
		
			}
			
			
					
				break;
				
		}//ColumnXLocation
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
