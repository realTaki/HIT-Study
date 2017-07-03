package edu.hit116xxxxxxxx.experiment5;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class CollectionBMI {
	
	static List<Student> students=new ArrayList<Student>();
	public CollectionBMI(){}

	
	public static void main(String[] agrv){
		new CollectionBMI();	
		Scanner in=new Scanner(System.in);	
		String str;
		
		do{	
			System.out.println("please input the ID");
			genStudents(in.next());
			System.out.println("Do you want to creat next random student?");
			//students.add(inputStudent(in));
			//System.out.println("Do you want to creat next student?");
			str=in.next();
		}while(str.compareTo("Y")==0||str.compareTo("y")==0);
			
		double aver=AverofBMI(),mod=ModofBMI(),med=MedianofBMI(),var=VarianceofBMI();
		System.out.printf("\nsort by bmi\n");
		printStatics(aver,mod,med,var);
		
		System.out.printf("\nsort by ID\n");
		comparator.SortbyID();
		printStatics(aver,mod,med,var);
		
		System.out.printf("\nsort by name\n");
		comparator.SortbyNAME();
		printStatics(aver,mod,med,var);
		
		System.out.printf("\nsort by height\n");
		comparator.SortbyHeight();
		printStatics(aver,mod,med,var);
		
		System.out.printf("\nsort by weight\n");
		comparator.SortbyWeight();
		printStatics(aver,mod,med,var);

		
		in.close();
	}

	public static void genStudents(String id){
		
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
		
			if(isExists(id))return;
			String name="";
			for(int j=0;j<5;j++){name+=Char[ron.nextInt(46)];}
			double height=ron.nextDouble()+1;
			double weight=ron.nextDouble()*60+40;
			students.add(new CollectionBMI().new Student(id,name,height,weight));
		
		System.out.println("Success!");
	}
	
	public static Student inputStudent(Scanner in){
		
		System.out.println("please input the ID,name,height and weight:");
		
		String number=in.next();
		String name=in.next();
		double height=in.nextDouble();
		double weight=in.nextDouble();
		Student student=new CollectionBMI().new Student(number,name,height,weight);

		return student;
	}
	
	public static boolean isExists(String id){
		for(Iterator<Student> iter = students.iterator();iter.hasNext();){
			Student temp=iter.next();
			if(temp.number==null)continue;
			if(id.compareTo(temp.number)==0)return true;
		}
		return false;
	}
	
	public static double AverofBMI(){
		double sum=0;
		for(Iterator<Student> iter = students.iterator();iter.hasNext();)
			sum+=iter.next().bmi;
		
		return sum/students.size();
	}
	
	public static double ModofBMI(){
		
		int hash[]=new int[10000];
		for(Iterator<Student> iter = students.iterator();iter.hasNext();)
			hash[(int)iter.next().bmi*100-1]++;
		int max=0;
		for(int i=1;i<hash.length;i++){
			if(hash[i]>hash[max])max=i;
		}
		return max/100.0;
	}	
	
	public static double MedianofBMI(){
		comparator.SortbyBMI();
		if(students.size()%2==0)
			return students.get(students.size()/2).bmi+students.get(students.size()/2-1).bmi;
		
		return students.get(students.size()/2).bmi;
	}	
	
	
	public static double VarianceofBMI(){
		double sum=0;
		double aver=AverofBMI();
		for(Iterator<Student> iter = students.iterator();iter.hasNext();){
			Student temp=iter.next();
			sum+=(temp.bmi-aver)*(temp.bmi-aver);
		}
			
		
		return sum/students.size();
	}
	
	public static void printStatics(double aver,double mod,double median,double variance){
		
		System.out.printf("Average is:%.2f\tMode is:%.2f\tMedian is:%.2f\tVariance is:%.2f\n"
				,aver,mod,median,variance);
		System.out.printf("ID\tNAME\tHeight\tWeight\tBMI\n");
		for(Iterator<Student> iter = students.iterator();iter.hasNext();){
			Student temp=iter.next();
			System.out.printf("%s\t%s\t%.2f\t%.2f\t%.2f\n",
					temp.number,temp.name,
					temp.height,temp.weight,temp.bmi);
		}
	}
	
	public static class comparator{
		
		public static void SortbyBMI(){
			for(int i=0;i<students.size();i++){
				int min=i;
				for(int j=i+1;j<students.size();j++){
					if(students.get(j).bmi<students.get(min).bmi)min=j;
				}
				if(min!=i){
					Student temp=students.get(i);
					students.set(i, students.get(min));
					students.set(min,temp);
				}
			}
		}
		
		public static void SortbyID(){
			for(int i=0;i<students.size();i++){
				int min=i;
				for(int j=i+1;j<students.size();j++){
					if(students.get(j).number.compareTo(students.get(min).number)<0)min=j;
				}
				if(min!=i){
					Student temp=students.get(i);
					students.set(i, students.get(min));
					students.set(min,temp);
				}
			}
		}
		
		public static void SortbyNAME(){
			for(int i=0;i<students.size();i++){
				int min=i;
				for(int j=i+1;j<students.size();j++){
					if(students.get(j).name.compareTo(students.get(min).name)<0)min=j;
				}
				if(min!=i){
					Student temp=students.get(i);
					students.set(i, students.get(min));
					students.set(min,temp);
				}
			}
		}
		
		
		public static void SortbyHeight(){
			for(int i=0;i<students.size();i++){
				int min=i;
				for(int j=i+1;j<students.size();j++){
					if(students.get(j).height<students.get(min).height)min=j;
				}
				if(min!=i){
					Student temp=students.get(i);
					students.set(i, students.get(min));
					students.set(min,temp);
				}
			}
		}
		
		public static void SortbyWeight(){
			for(int i=0;i<students.size();i++){
				int min=i;
				for(int j=i+1;j<students.size();j++){
					if(students.get(j).weight<students.get(min).weight)min=j;
				}
				if(min!=i){
					Student temp=students.get(i);
					students.set(i, students.get(min));
					students.set(min,temp);
				}
			}
		}
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
