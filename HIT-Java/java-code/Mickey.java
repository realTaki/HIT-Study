package edu.hit1162800204.experiment8;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Mickey extends JFrame  {

	private static final long serialVersionUID = 1L;
	
	final int lenth=900,width=600; 
	
	public Mickey(int n) {
		setTitle("Mickey");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, lenth, width);
		Container cp = getContentPane();
		
		DrawMickey mickey=	new DrawMickey();
		cp.add(mickey);
		mickey.timer.start();
		// Construct the drawing canvas		
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Mickey frame = new Mickey(3);
					 new Mickey(3);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}

class DrawMickey extends JPanel {

	private static final long serialVersionUID = 1L;
	final Timer timer = new Timer(10, new TimerListener());
	ArrayList<aMickey> mickeys=new ArrayList<aMickey>();
	Random ran = new Random(); 
	
	DrawMickey(){
		int n=ran.nextInt(3)+2;
		for(int i=0;i<n;i++)mickeys.add(new aMickey());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		drawMickey(g);
		moveMickey(g);
	}

	private void moveMickey(Graphics g) {
		for(Iterator<aMickey> iter = mickeys.iterator();iter.hasNext();){
			aMickey temp=iter.next();
			if(temp.bb.x>750||temp.bb.x<50)temp.left=!temp.left;
			if(temp.left){temp.bb.x-=2;} else temp.bb.x+=2;
			
			if(temp.bb.y>450||temp.bb.y<50)temp.up=!temp.up;
			if(temp.up){temp.bb.y-=2;} else temp.bb.y+=2;
		}
	}
	

	public void boxOval(Graphics g, Rectangle bb) {
		     g.fillOval(bb.x, bb.y, bb.width, bb.height);
	}
	
	private void drawMickey(Graphics g) {
		int i=0;
		for(Iterator<aMickey> iter = mickeys.iterator();iter.hasNext();){
			aMickey temp=iter.next();
			g.setColor(temp.color);
			boxOval(g, temp.bb);
			int dx = temp.bb.width / 2;
			int dy = temp.bb.height / 2;
			Rectangle half = new Rectangle(temp.bb.x, temp.bb.y, dx, dy);
			half.translate(-dx / 2, -dy / 2);
			boxOval(g, half);
			half.translate(dx * 2, 0);
			boxOval(g, half);
			g.setColor(Color.BLUE);
			g.setFont(new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 16));
			g.drawString("Mickey"+i++, temp.bb.x+25, temp.bb.y+120);  
			
		}
		
	}
	

	
	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
	
	class aMickey{
		
		Color color = new Color(
				(new Double(Math.random() * 128)).intValue() + 128,
				(new Double(Math.random() * 128)).intValue() + 128,
				(new Double(Math.random() * 128)).intValue() + 128
				); 
		
		Random ran = new Random(); 
		public boolean left=ran.nextBoolean(),up=ran.nextBoolean() ; 
		public Rectangle bb = new Rectangle(ran.nextInt(700)+50,ran.nextInt(400)+50, 100, 100);
		aMickey(){}
	}

}
