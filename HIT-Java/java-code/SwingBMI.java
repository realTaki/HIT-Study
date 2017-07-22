package edu.hitXXXX.experimentY;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class SwingBMI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	SwingBMI frame;
	static ArrayList<Student> students = new ArrayList<Student>();

	public static void main(String[] args) {
	
		SwingBMI frame = new SwingBMI();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);	
	}

	public static class Student {

		String ID=null,name=null;
		double height=0.0,weight=0.0,bmi=0.0;
		public Student(){}
		public Student(String number,String name,double height,double weight){
			this.ID=number;
			this.name=name;
			this.height=height;
			this.weight=weight;
			this.bmi=weight/(height*height);
		}
	}

	public static boolean isExists(String id){
		for(Iterator<Student> iter = students.iterator();iter.hasNext();){
			Student temp=iter.next();
			if(temp.ID==null)continue;
			if(id.compareTo(temp.ID)==0)return true;
		}
		return false;
	}

	public static void genStudents(int n) {
		Random ron=new Random();
		
		char Char[]={
				'A','B','C','D','E','F','G',
				'H','I','J','K','L','M','N',
				'O','P','Q','R','S','T',
				'U','V','W','X','Y','Z',
				'a','b','c','d','e','f','g',
				'h','i','j','k','l','m','n',
				'o','p','q','r','s','t',
				'u','v','w','x','y','z'};
		
		for(int i=0;i<n;i++){
			String number=String.valueOf(100+ron.nextInt(100));
			if(isExists(number)){
				i--;
				}
			else {
			String name="";
			for(int j=0;j<5;j++){name+=Char[ron.nextInt(46)];}
			double height=ron.nextDouble()+1;
			double weight=ron.nextDouble()*60+40;
			students.add(new Student(number,name,height,weight));
			}
		}
	}

	public static class comparator{
		
		public static void SortbyBMI(){
				
			Collections.sort(students, new Comparator<Student>() {
	            @Override
	            public int compare(Student o1, Student o2) {
	                //升序
	                return (int)(100*o1.bmi-100*o2.bmi);
	            }
	        });
		}
		
		public static void SortbyID(){
			Collections.sort(students, new Comparator<Student>() {
	            @Override
	            public int compare(Student o1, Student o2) {
	                //升序
	                return o1.ID.compareTo(o2.ID);
	            }
	        });
		}
		
		public static void SortbyNAME(){
			Collections.sort(students, new Comparator<Student>() {
	            @Override
	            public int compare(Student o1, Student o2) {
	                //升序
	                return o1.name.compareTo(o2.name);
	            }
	        });
		}
		
		
		public static void SortbyHeight(){
			Collections.sort(students, new Comparator<Student>() {
	            @Override
	            public int compare(Student o1, Student o2) {
	                //升序
	                return (int)(100*o1.height-100*o2.height);
	            }
	        });
		}
		
		public static void SortbyWeight(){
			Collections.sort(students, new Comparator<Student>() {
	            @Override
	            public int compare(Student o1, Student o2) {
	                //升序
	                return (int)(100*o1.weight-100*o2.weight);
	            }
	        });
		}
	}
	
	public SwingBMI() {
		
		setBounds(100, 100, 750, 800);
		JMenuBar menu = new JMenuBar();
		JMenu opMenu = new JMenu("Operations");
		JMenu helpMenu = new JMenu("Help");
		JMenu statisticsMenu = new JMenu("统计");
		
		JMenuItem QueryMenu = new JMenuItem("查改删");
		QueryMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				queryStudent();
		}
		});
		opMenu.add(QueryMenu);
		
		JMenuItem inputMenu = new JMenuItem("输入");
		inputMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				inputStudent();
		}
		});
		opMenu.add(inputMenu);
	
		opMenu.add(statisticsMenu);
		
		JMenuItem heightMenu = new JMenuItem("身高");
		heightMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Statics(1);
		}
		});
		statisticsMenu.add(heightMenu);
		
		JMenuItem weightMenu = new JMenuItem("体重");
		weightMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Statics(2);
		}
		});
		statisticsMenu.add(weightMenu);
		
		JMenuItem BMIMenu = new JMenuItem("BMI");
		BMIMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Statics(3);	
		}
		});
		statisticsMenu.add(BMIMenu);
		
		JMenuItem aboutitem = new JMenuItem("About ");
		aboutitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showHelp();
			}
		});
		helpMenu.add(aboutitem);
		
		menu.add(opMenu);
		menu.add(helpMenu);
		setJMenuBar(menu);
		queryStudent();
	}
	
	public void showHelp(){
		JOptionPane.showMessageDialog(null,
				"欢迎使用BMI统计分析系统\n作者：hitxxxxn",
				"Ablout", JOptionPane.INFORMATION_MESSAGE);		
	}

	public void queryStudent(){
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel lblbmi = new JLabel("BMI统计分析系统——学生列表");
		lblbmi.setBounds(45, 5, 682, 64);
		lblbmi.setFont(new Font("等线", Font.PLAIN, 45));
		contentPane.add(lblbmi);
		
		
		JButton hbotton=new JButton("按身高排序");
		hbotton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				comparator.SortbyHeight();
				showStudent();
		}
		});
		hbotton.setBounds(30, 70, 120, 30);
		contentPane.add(hbotton);
		
		JButton wbotton=new JButton("按体重排序");
		wbotton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				comparator.SortbyWeight();
				showStudent();
		}
		});
		wbotton.setBounds(30, 110, 120, 30);
		contentPane.add(wbotton);
		
		JButton nbotton=new JButton("按姓名排序");
		nbotton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				comparator.SortbyNAME();	
				showStudent();
		}
		});
		nbotton.setBounds(30,150, 120, 30);
		contentPane.add(nbotton);
		
		JButton ibotton=new JButton("按ID排序");
		ibotton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				comparator.SortbyID();
				showStudent();
		}
		});
		ibotton.setBounds(30, 190, 120, 30);
		contentPane.add(ibotton);
		
		JButton bbotton=new JButton("按BMI排序");
		bbotton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				comparator.SortbyBMI();	
				showStudent();
		}
		});
		bbotton.setBounds(30, 230, 120, 30);
		contentPane.add(bbotton);
		
		JLabel label = new JLabel("学号：");
		label.setBounds(30, 270, 120, 30);
		contentPane.add(label);
		
		final JTextField 	textField1= new JTextField();
		textField1.setBounds(30, 310, 120, 30);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		JButton sibotton=new JButton("按学号查询");
		sibotton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int flag=findStudent(1,textField1.getText());
				searchStudent(flag);
				if(flag<0)
				JOptionPane.showMessageDialog(null,"该学生不存在！","Warning",JOptionPane.WARNING_MESSAGE);
		}
		});
		sibotton.setBounds(30, 350, 120, 30);
		contentPane.add(sibotton);
		
		JLabel labe2 = new JLabel("姓名：");
		labe2.setBounds(30, 390, 120, 30);
		contentPane.add(labe2);
		
		final JTextField 	textField2= new JTextField();
		textField2.setBounds(30, 430, 120, 30);
		contentPane.add(textField2);
		textField2.setColumns(10);
		
		JButton snbotton=new JButton("按姓名查询");
		snbotton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int flag=findStudent(1,textField2.getText());
				searchStudent(flag);
				if(flag<0)
				JOptionPane.showMessageDialog(null,"该学生不存在！","Warning",JOptionPane.WARNING_MESSAGE);
		}
		});
		snbotton.setBounds(30, 470, 120, 30);
		contentPane.add(snbotton);
		
		final JTextField 	textField3= new JTextField();
		textField3.setBounds(180, 650, 120, 30);
		contentPane.add(textField3);
		textField3.setColumns(10);
		
		final JTextField 	textField4= new JTextField();
		textField4.setBounds(310, 650, 120, 30);
		contentPane.add(textField4);
		textField4.setColumns(10);
		
		final JTextField 	textField5= new JTextField();
		textField5.setBounds(440, 650, 120, 30);
		contentPane.add(textField5);
		textField5.setColumns(10);
		
		final JTextField 	textField6= new JTextField();
		textField6.setBounds(570, 650, 120, 30);
		contentPane.add(textField6);
		textField6.setColumns(10);
		
		JButton delbotton=new JButton("按学号删除");
		delbotton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int flag=findStudent(1,textField1.getText());
				if(flag>-1){
					students.remove(flag);
					JOptionPane.showMessageDialog(null,"删除成功","提示",JOptionPane.WARNING_MESSAGE);
					searchStudent(-1);
					showStudent();
				}else{
					JOptionPane.showMessageDialog(null,"该学生不存在","提示",JOptionPane.WARNING_MESSAGE);
				}
		}
		});
		delbotton.setBounds(30, 570, 120, 30);
		contentPane.add(delbotton);
		
		JButton modifybotton=new JButton("按学号修改");
		modifybotton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int flag=findStudent(1,textField1.getText());
				if(flag>-1){
					students.set(flag,inputStudent(textField3.getText(),
							textField4.getText(),textField5.getText(),textField6.getText()));
					JOptionPane.showMessageDialog(null,"修改成功","提示",JOptionPane.WARNING_MESSAGE);
					searchStudent(flag);
					showStudent();
				}else{
					JOptionPane.showMessageDialog(null,"该学生不存在","提示",JOptionPane.WARNING_MESSAGE);
				}
		}
		});
		modifybotton.setBounds(30, 650, 120, 30);
		contentPane.add(modifybotton);
		
		searchStudent(-1);
		showStudent();
		setContentPane(contentPane);
		setVisible(true);
	}
	
	public int findStudent(int mode,String key){
		if(mode==1)for(int i=0;i<students.size();i++){		
			if(key.compareTo(students.get(i).ID)==0){
				return i;
			}
		}
		else for(int i=0;i<students.size();i++){		
			if(key.compareTo(students.get(i).name)==0){
				return i;
			}
			}
		return -1;
	}
	
	public void showStudent(){
		ArrayList<Student> alst = students;
		String sb = "学号\t姓名\t身高\t体重\tBMI\n";
		JTextArea textArea= new JTextArea();
		textArea.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 15));
		textArea.setBounds(180, 70, 550, 450);
		textArea.setEditable(false);
		contentPane.add(textArea);
		for (Student st : alst) {
			sb = sb + String.format("%s\t%s\t%.2f\t%.2f\t%.2f\t%n", st.ID,
				st.name, st.height, st.weight, st.bmi);
		}
		JScrollPane scrollBar = new JScrollPane(textArea);
		scrollBar.setBounds(180, 70, 550, 450);
		scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollBar.setVisible(true);
		contentPane.add(scrollBar);
		textArea.setText(sb);
	}

	public void searchStudent(int i) {
		Student stu;
		String sb = "学号\t姓名\t身高\t体重\tBMI\n";
		if(i>-1){
			stu=students.get(i);
			sb = sb + String.format("%s\t%s\t%.2f\t%.2f\t%.2f\t%n", stu.ID,
					stu.name, stu.height, stu.weight, stu.bmi);
		}else sb += "无\t无\t无\t无\t无\n";
	
		
		JTextArea textArea= new JTextArea();
		textArea.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 15));
		textArea.setBounds(180, 530, 550, 100);
		textArea.setEditable(false);
		contentPane.add(textArea);

		JScrollPane scrollBar = new JScrollPane(textArea);
		scrollBar.setBounds(180, 530, 550, 80);
		scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollBar.setVisible(true);
		contentPane.add(scrollBar);
		textArea.setText(sb);
		
		JLabel labe1 = new JLabel(String.format("学号　　姓名　　身高　　体重　　BMI"));
		labe1.setBounds(180, 610, 550, 30);
		contentPane.add(labe1);
		

	}

	public void inputStudent()  {
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel lblbmi = new JLabel("BMI统计分析系统——信息登记");
		lblbmi.setBounds(45, 5, 682, 64);
		lblbmi.setFont(new Font("等线", Font.PLAIN, 45));
		contentPane.add(lblbmi);
		
		final JTextField 	textField1= new JTextField();
		final JTextField 	textField2= new JTextField();
		final JTextField 	textField3= new JTextField();
		final JTextField 	textField4= new JTextField();
		final JTextField 	textField5= new JTextField();
		
		JLabel label = new JLabel("学号：");
		label.setFont(new Font("等线", Font.PLAIN, 20));
		label.setBounds(20, 70, 110, 30);
		contentPane.add(label);

		textField1.setBounds(20, 110, 150, 35);
		contentPane.add(textField1);
		textField1.setColumns(10);

		JLabel label2 = new JLabel("姓名：");
		label2.setFont(new Font("等线", Font.PLAIN, 20));
		label2.setBounds(20, 150, 110, 30);
		contentPane.add(label2);

		textField2.setColumns(10);
		textField2.setBounds(20, 180, 150, 35);
		contentPane.add(textField2);

		JLabel label3 = new JLabel("身高：(m)");
		label3.setFont(new Font("等线", Font.PLAIN, 20));
		label3.setBounds(20, 220, 110, 30);
		contentPane.add(label3);

		textField3.setColumns(10);
		textField3.setBounds(20, 250, 150, 35);
		contentPane.add(textField3);

		JLabel label4 = new JLabel("体重：(kg)");
		label4.setFont(new Font("等线", Font.PLAIN, 20));
		label4.setBounds(20, 290, 110, 30);
		contentPane.add(label4);

		textField4.setColumns(10);
		textField4.setBounds(20, 320, 150, 35);
		contentPane.add(textField4);

		JLabel label5 = new JLabel("BMI：");
		label5.setFont(new Font("等线", Font.BOLD, 22));
		label5.setBounds(20, 360, 110, 30);
		contentPane.add(label5);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(20, 390, 150, 30);
		textArea.setEditable(false);
		contentPane.add(textArea);
		
		JButton button1 = new JButton("提交");
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String ID = textField1.getText();
				if(isExists(ID)){
					JOptionPane.showMessageDialog(null,"该学生已存在！","Warning",JOptionPane.WARNING_MESSAGE);
				}else{
				String name = textField2.getText();
				double height = Double.parseDouble(textField3.getText());
				double weight = Double.parseDouble(textField4.getText());
				Student student=new Student(ID,name,height,weight);
				students.add(student);
				textArea.setText(Double.toString(student.bmi));
				showStudent();
				}
		}
		});
		button1.setFont(new Font("等线", Font.PLAIN, 20));
		button1.setBounds(20, 440, 130, 35);
		contentPane.add(button1);
		
		JLabel label6 = new JLabel("人数：");
		label6.setFont(new Font("等线", Font.PLAIN, 20));
		label6.setBounds(20, 480, 150, 30);
		contentPane.add(label6);
		
		textField5.setColumns(10);
		textField5.setBounds(20,510, 150, 35);
		contentPane.add(textField5);
		
		JButton button2 = new JButton("生成");
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				genStudents(Integer.parseInt(textField5.getText()));
				showStudent();
				JOptionPane.showMessageDialog(null,"Success!","提示消息",JOptionPane.WARNING_MESSAGE);
		}
		});
		button2.setFont(new Font("等线", Font.PLAIN, 20));
		button2.setBounds(20,550, 130, 35);
		contentPane.add(button2);

		showStudent();
		setContentPane(contentPane);
		setVisible(true);
	}
	
	public Student inputStudent(String ID,String name,String height,String weight) {
		double H = Double.parseDouble(height);
		double W = Double.parseDouble(weight);
		Student student=new Student(ID,name,H,W);
		showStudent();
		return student;
	}

	public void Statics(int mode) {
		String mos;
		IntervalXYDataset t;
		switch(mode){
		case 1:
			mos="身高";
			t=Data(1);
			break;
		case 2:
			mos="体重";
			t=Data(2);
			break;
		case 3:
			default:mos="BMI";t=Data(3);
		}
		JFreeChart chart = ChartFactory.createXYBarChart(mos+"统计",
				mos+"分组", false, "学生人数",
				t, PlotOrientation.VERTICAL,
				true, false, false);
			XYPlot xyplot = (XYPlot)chart.getPlot();
			chart.getTitle().setFont(new Font("等线", Font.BOLD, 25));//设置标题  
																	//设置图例类别字体           
			chart.getLegend().setItemFont(new Font("等线", Font.BOLD, 15));
			xyplot.getRangeAxis().setLabelFont(new Font("等线", Font.PLAIN, 15));//纵轴字体设置
			xyplot.getRangeAxis().setTickLabelFont(new Font("等线", Font.PLAIN, 15));
			xyplot.getDomainAxis().setTickLabelFont(new Font("等线", Font.PLAIN, 15));//横轴字体设置
			xyplot.getDomainAxis().setLabelFont(new Font("等线", Font.PLAIN, 15));
			
			ChartFrame frame = new ChartFrame("组别学生人数", chart);
			frame.pack();
			frame.setVisible(true);
	}
	
	public IntervalXYDataset Data(int mode) {
		XYSeriesCollection seriesCollection = new XYSeriesCollection();
		XYSeries series = new XYSeries("BMI统计");
		int top=students.size();
		double min,max;
		
		switch(mode){
		case 1:
			comparator.SortbyHeight();
			min = students.get(0).height; max = students.get(top-1).height;
			break;
		case 2:
			comparator.SortbyWeight();
			min = students.get(0).weight; max =students.get(top-1).weight;
			
			break;
		case 3:
			default:
				comparator.SortbyBMI();
				min = students.get(0).bmi; max =students.get(top-1).bmi;
		}
		
		double range = max - min;
		double groups = range / 10;
		int[] count = new int[10];

		for (int i = 0; i<top; i++) {
			for (int j = 0; j<10; j++) {
				switch(mode){
				case 1:
					if (students.get(i).height >= min + j*groups&&students.get(i).height<min + (j + 1)*groups) {
						count[j]++;
					}
					break;
				case 2:
					if (students.get(i).weight >= min + j*groups&&students.get(i).weight<min + (j + 1)*groups) {
						count[j]++;
					}
					break;
				case 3:
					default:
						if (students.get(i).bmi >= min + j*groups&&students.get(i).bmi<min + (j + 1)*groups) {
							count[j]++;
						}
				}
			}
		}
		count[9]++;
		for (int j = 0; j<10; j++) {
			series.add(j + 1, count[j]);
		}
		seriesCollection.addSeries(series);
		return new XYBarDataset(seriesCollection, 0.9);
	}
}
