package edu.hit116xxxxxxxx.experiment4;

import java.util.Scanner;
import java.util.Random;

public class OOBMI {
	
	static Student[] students;
	public OOBMI(){}
	public OOBMI(int n){
		students=new Student[n];
		for(int i=0;i<students.length;i++)students[i]=new OOBMI().new Student();
	}
	
	public static void main(String[] agrv){
		Scanner in=new Scanner(System.in);
		System.out.println("please input the number of students:");
		
		int n=in.nextInt();
		new OOBMI(n);
		
		//System.out.println("please input the ID,name,height and weight:");
		//for(int i=0;i<n;i++)students[i]=inputStudent(in);
		
		genStudents(n);
		printStatics(AverofBMI(),ModofBMI(),MedianofBMI(),VarianceofBMI());
		
		in.close();
	}

	public static void genStudents(int n){
		
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
			String number=String.valueOf(100+ron.nextInt()%100);
			if(isExists(number))continue;
			String name="";
			for(int j=0;j<5;j++){name+=Char[ron.nextInt(46)];}
			double height=ron.nextDouble()+1;
			double weight=ron.nextDouble()*60+40;
			students[i]=new OOBMI().new Student(number,name,height,weight);
		}
		System.out.println("Success!");
	}
	
	public static Student inputStudent(Scanner in){
		
		String number=in.next();
		String name=in.next();
		double height=in.nextDouble();
		double weight=in.nextDouble();
		Student student=new OOBMI().new Student(number,name,height,weight);

		return student;
	}
	
	public static boolean isExists(String id){
		for(int i=0;i<students.length;i++){
			if(students[i].number==null)continue;
			if(id.compareTo(students[i].number)==0)return true;
		}
		return false;
	}
	
	public static double AverofBMI(){
		double sum=0;
		for(int i=0;i<students.length;i++)sum+=students[i].bmi;
		
		return sum/students.length;
	}
	
	public static double ModofBMI(){
		
		int hash[]=new int[10000];
		for(int i=0;i<students.length;i++)hash[(int)students[i].bmi*100-1]++;
		int max=0;
		for(int i=1;i<hash.length;i++){
			if(hash[i]>hash[max])max=i;
		}
		return max/100.0;
	}	
	
	public static double MedianofBMI(){
		SortbyBMI();
		if(students.length%2==0)
			return students[students.length/2].bmi+students[students.length/2-1].bmi;
		
		return students[students.length/2].bmi;
	}	
	
	
	public static double VarianceofBMI(){
		double sum=0;
		double aver=AverofBMI();
		for(int i=0;i<students.length;i++)
			sum+=(students[i].bmi-aver)*(students[i].bmi-aver);
		
		return sum/students.length;
	}
	
	public static void SortbyBMI(){
		for(int i=0;i<students.length;i++){
			int min=i;
			for(int j=i+1;j<students.length;j++){
				if(students[i].bmi<students[min].bmi)min=j;
			}
			if(min!=i){
				Student temp=students[i];
				students[i]=students[min];
				students[min]=temp;
			}
		}
	}
	
	public static void printStatics(double aver,double mod,double median,double variance){
		
		System.out.printf("Average is:%.2f\tMode is:%.2f\tMedian is:%.2f\tVariance is:%.2f\n"
				,aver,mod,median,variance);
		System.out.printf("ID\tNAME\tHeight\tWeight\tBMI\n");
		for(int i=0;i<students.length;i++)
			System.out.printf("%s\t%s\t%.2f\t%.2f\t%.2f\n",
					students[i].number,students[i].name,
					students[i].height,students[i].weight,students[i].bmi);
	}
	
	
	
	public class Student{
	
		String number="",name="";
		double height=0.0,weight=0.0,bmi=0.0;
		
		public Student(){}
		public Student(String number,String name,double height,double weight){
			this.number=number;
			this.name=name;
			this.height=(int)(height*100)/100.0;
			this.weight=(int)(weight*100)/100.0;
			this.bmi=(int)(weight/(height*height))*100/100.0;
		}
		
	}
}
