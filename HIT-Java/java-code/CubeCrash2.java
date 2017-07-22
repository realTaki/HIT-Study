package edu.hit.java.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CubeCrash2 extends JFrame {
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		final JFrame frame = new JFrame("CubeCrash");
		final Cube2 a = new Cube2();
		frame.addMouseListener(a);
		frame.add(a);	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(300, 100, 400, 520);
		frame.setVisible(true);
		frame.setResizable(false);
		a.timer.start();
	}
}

class Cube2 extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	final Timer timer = new Timer(100, new TimerListener());
	Random ran = new Random();
	private int score = 0,times=-1;
	final int red=1,orange=2,green=3,blue=4,non=0;
	int[][][] map;
	int[] ready=new int[10];

	Cube2() {
		newMap();
	}
	
	public void newMap() {
		 map = new int[10][20][2];
		 for(int i=0;i<10;i++){
			 ready[i]=ran.nextInt(4)+1;
			 for(int j=0;j<20;j++)
				 map[i][j][1]=1;
		 }
	}

	private void deletCube(int x,int y){
		int count=findsameCube(x,y);
		if(count>1){
			for(int i=0;i<10;i++)
				for(int j=0;j<20;j++)
					if(map[i][j][1]==1)map[i][j][0]=non;
			score+=Math.pow(2,count);
		}else{
			map[x][y][1]=0;
		}
		repaint();
		
	}
	private void checkover(){
		for(int i=0;i<10;i++){
			if(map[i][0][0]!=non){
				JOptionPane.showMessageDialog(null, "Game Over!");
				newMap();
				score = 0;
			}
		}
	}
	
	private int findsameCube(int x,int y){
		int count=1;
		map[x][y][1]=1;
		if(x>0&&map[x][y][0]==map[x-1][y][0]&&map[x-1][y][1]==0)
			count+=findsameCube(x-1, y);
		if(y>0&&map[x][y][0]==map[x][y-1][0]&&map[x][y-1][1]==0)
			count+=findsameCube(x, y-1);
		if(x<9&&map[x][y][0]==map[x+1][y][0]&&map[x+1][y][1]==0)
			count+=findsameCube(x+1, y);
		if(y<19&&map[x][y][0]==map[x][y+1][0]&&map[x][y+1][1]==0)
			count+=findsameCube(x, y+1);
		
		return count;
	}
	
	private boolean checkCube(){
		boolean flag=true;
		
		for(int i=0;i<10;i++)
			for(int j=0;j<19;j++)
				if(map[i][j][0]!=non&&map[i][j+1][0]==non)
				{
					flag=false;
					swap(i,j+1,i,j);
				}

		return flag;
	}
	
	public void swap(int x1,int y1,int x2,int y2){
		int [][][]temp=new int[1][1][2];
		temp[0][0]=map[x1][y1];
		map[x1][y1]=map[x2][y2];
		map[x2][y2]=temp[0][0];
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0;i<10;i++)
			for(int j=0;j<20;j++){
				boolean flag=true;
				switch(map[i][j][0]){
				case green:g.setColor(Color.GREEN);break;
				case orange:g.setColor(Color.ORANGE);break;
				case red:g.setColor(Color.RED);break;
				case blue:g.setColor(Color.BLUE);break;
				default:flag=!flag;break;
				}
				if(flag)g.fill3DRect(i * 20+20, j * 20+60, 20, 20, true);
			}
		for(int i=0;i<10;i++){
			switch(ready[i]){
			case green:g.setColor(Color.GREEN);break;
			case orange:g.setColor(Color.ORANGE);break;
			case red:g.setColor(Color.RED);break;
			case blue:g.setColor(Color.BLUE);break;
			default:break;
			}
			g.fill3DRect(i * 20+20, 20, 20, 20, true);
		}
		
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		g.drawString("Score = " + score, 250, 100);
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1){//µã»÷Êó±ê×ó¼ü  
			int x=e.getX();
			int y=e.getY();
			if(x>20&&x<220&&y>80&&y<480)deletCube((x-20)/20,(y-80)/20);
		}
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			times++;
			checkCube();
			if(times%10==0){
				checkover();
				for(int i=0;i<10;i++){
					map[i][0][0]=ready[i];
					map[i][0][1]=0;
					ready[i]=ran.nextInt(4)+1;
				}
			}
			repaint();
		}
	}
	
}