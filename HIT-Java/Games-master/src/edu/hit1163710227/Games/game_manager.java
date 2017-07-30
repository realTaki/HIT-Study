package edu.hit1163710227.Games;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class game_manager extends JFrame{

	private static final long serialVersionUID = 1L;
	final static JPanel pl=new JPanel();
	public static void main(String[] agrs){
		final game_manager frame = new game_manager();	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	game_manager(){
		setTitle("Games");
		setBounds(100, 100, 400, 120);
		JLabel lblbmi = new JLabel("街机合集");
		lblbmi.setBounds(45, 5, 300, 64);
		lblbmi.setFont(new Font("等线", Font.PLAIN, 45));
		pl.add(lblbmi);
		JMenuBar menu = new JMenuBar();
		
		JMenuItem cubecrsh = new JMenuItem("CubeCrash");
		cubecrsh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pl.removeAll();
				pl.setBounds(0, 0, 400, 320);
				setBounds(300,300, 400, 320);
				CubeCrash map = new CubeCrash();
				addMouseListener(map);
				map.setVisible(true);
				map.setBounds(0, 0, 400, 320);
				pl.add(map);
			}
		});
		menu.add(cubecrsh);
		
		JMenuItem goldminer = new JMenuItem("GoldMiner");
		goldminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pl.removeAll();
				pl.setBounds(0, 0, 800, 750);
				setBounds(300, 300, 800, 780);
				GlodMiner map = new GlodMiner();
				addKeyListener(map);
				map.setVisible(true);
				map.setBounds(0, 0, 800, 750);
				pl.add(map);			
			}
		});
		menu.add(goldminer);
		
		JMenuItem blockies = new JMenuItem("Blockies");
		blockies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pl.removeAll();
				pl.setBounds(300, 100, 400, 520);
				setBounds(300, 100, 400, 550);
				Blockies map = new Blockies();
				addMouseListener(map);
				map.setVisible(true);
				map.setBounds(0, 0, 400, 520);
				pl.add(map);	
				map.timer.start();
			}
		});
		menu.add(blockies);
		
		JMenuItem tetrist = new JMenuItem("Tetrist");
		tetrist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pl.removeAll();
				pl.setBounds(0, 0, 400, 460);
				setBounds(300, 300, 400, 490);
				Tetrist map = new Tetrist();
				addKeyListener(map);
				map.setVisible(true);
				map.setBounds(0, 0, 400, 460);
				pl.add(map);
				map.timer.start();
			}
		});
		menu.add(tetrist);
		
		JMenuItem helpMenu = new JMenuItem("About ");
		helpMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"街机合集，祝你玩得愉快\n作者：hit1163710227",
						"Ablout", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menu.add(helpMenu);
		
		pl.setLayout(null);
		add(pl);
		setJMenuBar(menu);
	}
}

class GlodMiner extends JPanel implements KeyListener{

	private static final long serialVersionUID = 1L;
	final int Gold=2,Stone=3,Diamond=1;
	ArrayList<Items> ls=new ArrayList<Items>();
	Random ran = new Random();
	final Timer timer = new Timer(50, new TimerListener());
	private int score = 0,lx=400,ly=200,angle=90,derta=1,len=50,speed=10;
	private boolean shooting=false;
	
	GlodMiner(){
		
		for(int i=0;i<15;i++){
			int x=ran.nextInt(700),y=ran.nextInt(450)+200,r,c;
			if(i<2)c=1;else if(i<10)c=2;else c=3;
			if(c==1)r=ran.nextInt(10)+10;else r=ran.nextInt(30)+20;
			boolean flag=true;
			for(Iterator<Items> it = ls.iterator();it.hasNext();){
				Items temp=it.next();
				int d=(x-temp.x)*(x-temp.x)+(y-temp.y)*(y-temp.y)-(r+temp.r)*(temp.r+r);
				if(d<0){
					flag=false;
					i--;
					break;
				}
			}
			if(flag)ls.add(new Items(x,y,r,c));
		}
		timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawLine(0,150,800,150);
		for(Iterator<Items> it = ls.iterator();it.hasNext();){
				Items temp=it.next();
				g.setColor(temp.c);
				g.fillOval(temp.x,temp.y, 2*temp.r, 2*temp.r);
				g.setColor(Color.black);
				g.drawOval(temp.x,temp.y, 2*temp.r, 2*temp.r);
		}
		g.drawLine(400,150,lx,ly);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		g.drawString("Score = " + score, 250, 100);
	}
	
	private void Shoot(){
		for(int i=0;i<ls.size();i++){
			Items temp=ls.get(i);
			int x=temp.x+temp.r-lx,y=temp.y+temp.r-ly;
			int d=x*x+y*y-temp.r*temp.r;
			if(d<0){
				ls.get(i).x-=(int)(Math.cos(angle*2*Math.PI/360)*speed/temp.mass);
				ls.get(i).y-=(int)(Math.sin(angle*2*Math.PI/360)*speed/temp.mass);
				len-=(int)(speed/temp.mass);
				return;
			}
		}
		
		if(lx<0||lx>800||ly<150||ly>750){
			speed=-speed;
			if(speed>0)	{
				shooting=false;	
				len=50;
			}
		}
		len+=speed;
	}
	
	private void calScore(){
		for(int i=0;i<ls.size();i++){
			Items temp=ls.get(i);
			if(temp.y<150){
			ls.remove(i);
			score+=temp.value;
			shooting=false;
			len=50;
				return;
			}
		}
	}
	
	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
			if(shooting){
				Shoot();
				calScore();
			}
			else{
				angle+=2*derta;
				if(angle>175)derta=-1;
				else if(angle<15)derta=1;	
			}
			lx=400+(int)(Math.cos(angle*2*Math.PI/360)*len);
			ly=150+(int)(Math.sin(angle*2*Math.PI/360)*len);
			repaint();
		}
	}
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			shooting=true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	class Items{
	public int x=0,y=0,r=0;
	public Color c;
	public int value;
	double mass;

	Items(int x,int y,int r,int item){
		this.x=x;
		this.y=y;
		this.r=r;
		mass=r*item/30;
		if(mass<1)mass=1;
		switch(item){
			case Diamond:
				value=r*50;
				c=Color.BLUE;
				break;
			case Gold:
				value=r*10;
				c=Color.YELLOW;
				break;
			case Stone:		
			default:
				value=r*2;
				c=Color.GRAY;
				break;
		}
	}
	}
}

class CubeCrash extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	final Timer timer = new Timer(100, new TimerListener());//计时器
	Random ran = new Random();
	private int score = 0;
	final int red=1,orange=2,green=3,blue=4,non=0;
	final int line=10,col=10;//定义行列数，一次修正全局更改
	int[][][] map = new int[col][line][2];

	CubeCrash() {
		newMap();
	}
	
	public void newMap() {//初始化
		for(int i=0;i<col;i++)
			for(int j=0;j<line;j++){
				map[i][j][0]=ran.nextInt(4)+1;
				map[i][j][1]=0;
				}	
	}

	private void deletCube(int x,int y){//实际上这个函数是游戏的主流程
		int count=findsameCube(x,y);//寻找同色方块
		if(count>1){//是否可消除
			for(int i=0;i<col;i++)
				for(int j=0;j<line;j++)
					if(map[i][j][1]==1)map[i][j][0]=non;
			score+=Math.pow(2,count);
		}else{
			map[x][y][1]=0;
		}
		
		repaint();
		timer.start();//开始动画
		checkover();//检查是否结束游戏
	}
	
	private void checkover(){//所有方块无相邻相同色块游戏结束
		for(int i=0;i<col-1;i++)
			for(int j=line-1;j>0;j--){
				if(map[i][j][0]!=non
					&&(map[i][j][0]==map[i+1][j][0]
					||map[i][j][0]==map[i][j-1][0]))return;
			}
		JOptionPane.showMessageDialog(null, "Game Over!");
		newMap();
		score = 0;
	}
	
	private int findsameCube(int x,int y){//递归搜索相同色块，主要算法
		int count=1;
		map[x][y][1]=1;
		if(x>0&&map[x][y][0]==map[x-1][y][0]&&map[x-1][y][1]==0)
			count+=findsameCube(x-1, y);
		if(y>0&&map[x][y][0]==map[x][y-1][0]&&map[x][y-1][1]==0)
			count+=findsameCube(x, y-1);
		if(x<col-1&&map[x][y][0]==map[x+1][y][0]&&map[x+1][y][1]==0)
			count+=findsameCube(x+1, y);
		if(y<line-1&&map[x][y][0]==map[x][y+1][0]&&map[x][y+1][1]==0)
			count+=findsameCube(x, y+1);
		
		return count;
	}
	
	private boolean checkCube(){//检查是否有浮空色块，有的话每次下降一格，形成动画
		boolean flag=true;
		
		for(int i=0;i<col;i++)
			for(int j=0;j<line-1;j++)
				if(map[i][j][0]!=non&&map[i][j+1][0]==non)
				{
					flag=false;
					swap(i,j+1,i,j);
				}
		for(int i=0;i<col-1;i++){
			int j;
			for(j=0;j<line;j++){
				if(map[i][j][0]!=non)break;
			}
			if(j==line){
				flag=false;
				for(j=0;j<line;j++){
					swap(i,j,i+1,j);
				}
			}
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
		for(int i=0;i<col;i++)
			for(int j=0;j<line;j++){
				boolean flag=true;
				switch(map[i][j][0]){
				case green:g.setColor(Color.GREEN);break;
				case orange:g.setColor(Color.ORANGE);break;
				case red:g.setColor(Color.RED);break;
				case blue:g.setColor(Color.BLUE);break;
				default:flag=false;break;
				}
				if(flag)g.fill3DRect(i * 20+20, j * 20+20, 20, 20, true);
			}

		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		g.drawString("Score = " + score, 250, 100);
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1){//点击鼠标左键  
			int x=e.getX();
			int y=e.getY();
			if(x>20&&x<20*col+20&&y>70&&y<line*20+70)deletCube((x-20)/20,(y-70)/20);
		}
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
			if(checkCube()){
				timer.stop();
			}
			repaint();
		}
	}	
}


class Tetrist extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;
	private boolean pause=false;
	final Timer timer = new Timer(500, new TimerListener());
	Random ran = new Random(); // 使用Random函数；
	private int TetriminoType; // 代表方块类型
	private int score = 0; // 分数；
	private int rotateState; //代表方块状态
	private int x,y; // 方块起始位置的坐标
	private int nextb = ran.nextInt(7); // 下一个方块类型；
	private int nextt = ran.nextInt(4); // 下一个方块的形状；

	int[][] map = new int[23][13];

	Tetrist() {
		newTetrimino();
		newMap();
	}

	// 第一维代表方块的形状，方块形状类型有S、 Z、 L、 J、 I、 O、 T 7种
	// 第二维代表方块状态
	// 第三维为方块矩阵
	private final int shapes[][][] = new int[][][] {
			// 棒型方块
			{ 
				{ 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } 
			},
			// s型方块
			{ 
				{ 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } 
			},
			// z型方块
			{ 
				{ 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } },
			// 右l型方块
			{
				{ 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, 
				{ 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			// 田型方块
			{ 
				{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			// 左l型方块
			{
				{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
				{ 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			// t型方块
			{ 
				{ 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
				{ 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 } 
			} 
	};
		
	// initialize the map
	public void newMap() {
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 23; j++) {
				map[j][i] = 0;
			}
		}
		// draw walls
		for (int i = 0; i < 12; i++) {//right-1
			map[21][i] = 2;
		}
		for (int j = 0; j < 22; j++) {//lower-1
			map[j][11] = 2;
			map[j][0] = 2;
		}
	}


	//new Tetrimino
	public void newTetrimino() {
		TetriminoType = nextb;
		rotateState = nextt;
		nextb = ran.nextInt(7);
		nextt = ran.nextInt(4);
		x = 4;
		y = 0;
		if (collisionDetect(x, y, TetriminoType, rotateState)) {
			JOptionPane.showMessageDialog(null, "Game Over!");
			newMap();
			score = 0;
		}
	}

	//collision Detect
	public boolean collisionDetect(int x, int y, int blockType, int turnState) {
		//a,b为相对坐标(每个形状的每个状态)
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (((shapes[blockType][turnState][a * 4 + b] == 1) 
						&& (map[y + a][x + b + 1] == 1))|| 
					((shapes[blockType][turnState][a * 4 + b] == 1) 
						&& (map[y + a][x + b + 1] == 2))) {
					return true;
				}
			}
		}
		return false;
	}
	public void showHelp(){
		JOptionPane.showMessageDialog(null,
				"Welcole to play Tetris Game!\nEscape Start/Pause\n",
				"Ablout", JOptionPane.INFORMATION_MESSAGE);		
	}
	

	// 旋转的方法
	private void rotate() {
		int tempturnState = rotateState;
		rotateState = (rotateState + 1) % 4;
		if (pause||collisionDetect(x, y, TetriminoType, rotateState)) {
			rotateState = tempturnState;
		}
	}

	// 左移的方法
	private void left() {
		if (!pause&&!collisionDetect(x - 1, y, TetriminoType, rotateState)) {
			x = x - 1;
		}
	}

	// 右移的方法
	private void right() {
		if (!pause&&!collisionDetect(x + 1, y, TetriminoType, rotateState)) {
			x = x + 1;
		}
	}

	// 下落的方法
	private void fall() {
		if (!pause&&!collisionDetect(x, y + 1, TetriminoType, rotateState)) {
			y = y + 1;
		}
		
	}

	// 消行的方法
	private void clearLines() {
		int c = 0;
		int lines = 0; // 用来确定本次消了几行
		for (int yy = 0; yy < 22; yy++) {
			c = 0;
			for (int xx = 0; xx < 12; xx++) {
				if (map[yy][xx] == 1) {
					c = c + 1;
					if (c == 10) {
						lines++;
						for (int cy = yy; cy > 0; cy--) {
							for (int e = 1; e < 11; e++) {
								map[cy][e] = map[cy - 1][e];
							}
						}
					}
				}
			}
		}
		// 确定消行分数
		switch (lines) {
		case 1:
			score = score + 10;
			break;
		case 2:
			score = score + 40;
			break;
		case 3:
			score = score + 90;
			break;
		case 4:
			score = score + 160;
			break;
		default:
			break;
		}
	}

	private void addToMap(int x, int y, int blockType, int turnState) {
		int j = 0;
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (map[y + a][x + b + 1] == 0) {
					map[y + a][x + b + 1] = shapes[blockType][turnState][j];
				}
				j++;
			}
		}
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		for (int j = 0; j < 16; j++) {
			if (shapes[TetriminoType][rotateState][j] == 1) {
				g.setColor(Color.BLUE);
				g.fill3DRect((j % 4 + x + 1) * 20, (j / 4 + y) * 20, 20, 20, true);

			}
		}
		for (int j = 0; j < 16; j++) {
			if (shapes[nextb][nextt][j] == 1) {
				g.setColor(Color.BLUE);
				g.fill3DRect((j % 4 + 1) * 20 + 250, (j / 4) * 20 + 40, 20, 20, true);

			}
		}
		for (int j = 0; j < 23; j++) {
			for (int i = 0; i < 12; i++) {
				if (map[j][i] == 2) { // 画围墙
					g.setColor(Color.BLACK);
					g.fill3DRect(i * 20, j * 20, 20, 20, true);

				}
				if (map[j][i] == 1) { // 画固定的方块
					g.setColor(Color.GREEN);
					g.fill3DRect(i * 20, j * 20, 20, 20, true);

				}
			}
		}
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		g.drawString("Score = " + score, 250, 10);
		g.setColor(Color.RED);
		g.drawString("Next Tetrimino: ", 250, 30);
	}

	// 键盘监听
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			fall();
			break;
		case KeyEvent.VK_UP:
			rotate();
			break;
		case KeyEvent.VK_RIGHT:
			right();
			break;
		case KeyEvent.VK_LEFT:
			left();
			break;
		case KeyEvent.VK_ESCAPE:
			if (!pause){
				pause=true;
				timer.stop();
			}else{
				pause=false;
				timer.start();	
			}
			break;
		default:
			break;
		}
		repaint();

	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			fall();
			if (collisionDetect(x, y + 1, TetriminoType, rotateState)) {
					addToMap(x, y, TetriminoType, rotateState);
					clearLines();
					newTetrimino();
			}
			repaint();
		}
	}
}

class Blockies extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	final Timer timer = new Timer(100, new TimerListener());
	Random ran = new Random();
	private int score = 0,times=-1;
	final int red=1,orange=2,green=3,blue=4,non=0;
	int[][][] map;
	int[] ready=new int[10];

	Blockies() {
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
		if(e.getButton()==MouseEvent.BUTTON1){//点击鼠标左键  
			int x=e.getX();
			int y=e.getY();
			if(x>20&&x<220&&y>110&&y<510)deletCube((x-20)/20,(y-110)/20);
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


