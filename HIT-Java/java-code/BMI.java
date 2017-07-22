package edu.hitXXXX.experimentY;

import java.util.Scanner;

public class BMI {

	public static void main(String[] agrv){
		Scanner input=new Scanner(System.in);
		
		System.out.println("请输入学生人数N：");
		int N=input.nextInt();
		
		String[] ids=new String[N];
		String[] names=new String[N];
		float[] heights=new float[N];
		float[] weights=new float[N];
		float[] bmis=new float[N];
		
		System.out.println("请输入每个学生的学号、姓名、身高、体重：");
		for(int i=0;i<N;i++){
			ids[i]=input.next();
			names[i]=input.next();
			heights[i]=input.nextFloat();
			weights[i]=input.nextFloat();
			bmis[i]=weights[i]/(heights[i]*heights[i]);
		}
		
		//int sortindex[]=SortByName(names, N);
		//int sortindex[]=SortById(ids, N);
		//int sortindex[]=SortByWeight(weights, N);
		//int sortindex[]=SortByHeight(heights, N);
		int sortindex[]=SortByBMI(bmis, N);
		
		printStudents(ids, names, heights, bmis, weights, N, sortindex);
		
		input.close();
	}

	public static void printStudents(String[] ids,String[] names,
			float[] heights,float[] weights,float[] bmis,int N,int sortindex[]){
		System.out.println("排序前:");
		for(int i=0;i<N;i++)
			System.out.printf("%s\t%s\t%.2f\t%.1f\t%.2f\t\n", 
					ids[i],names[i],heights[i],weights[i],bmis[i]);
		
		System.out.println("排序后:");
			for(int i=0;i<N;i++)
				System.out.printf("%s\t%s\t%.2f\t%.1f\t%.2f\t\n", 
						ids[sortindex[i]],names[sortindex[i]],heights[sortindex[i]],
						weights[sortindex[i]],bmis[sortindex[i]]);
			System.out.println();
		}
	
	static void Input(){
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("请输入学号：");
		long numble=input.nextLong();
		
		System.out.println("请输入姓名：");
		String name=input.next();
		
		System.out.println("请输入身高(m)：");
		double height=input.nextFloat();
		System.out.println("请输入体重(Kg)：");
		double weight=input.nextFloat();
		
		double bmi=weight/(height*height);
		
		System.out.println("学号："+numble+"姓名："+name);
		System.out.printf("身高：%.0f"+"厘米，体重：%.1f"+"斤，BMI：%.1f\n",height*100,weight*2,bmi);
		System.out.println(checkHealth(bmi));
		input.close();
	}
	

	public static String checkHealth(double bmi) {
		String str[] = { "Underweight", "Normal Range",
				"Overweight-At Risk", "Overweight-Moderately Obese",
				"Overweight-Severely Obese" };
		if (bmi < 18.5)
			return str[0];
		else if (bmi < 23)
			return str[1];
		else if (bmi < 25)
			return str[2];
		else if (bmi < 30)
			return str[3];
		else
			return str[4];
	}
	
	public static int[] SortByName(String name[],int N){
		int sortindex[]=new int[N];
		for(int i=0;i<N;i++)sortindex[i]=i;
		
		for(int i=0;i<N;i++){
			int min=i;
			for(int j=i+1;j<N;j++){
				if(name[sortindex[min]].compareTo(name[sortindex[j]])>0)
					min=j;
			}
			if(min!=i){
				int temp=sortindex[min];
				sortindex[min]=sortindex[i];
				sortindex[i]=temp;
			}
		}

		return sortindex;
	} 
	
	public static int[] SortById(String id[],int N){
		int sortindex[]=new int[N];
		for(int i=0;i<N;i++)sortindex[i]=i;
		
		for(int i=0;i<N;i++){
			int min=i;
			for(int j=i+1;j<N;j++){
				if(id[sortindex[min]].compareTo(id[sortindex[j]])>0)
					min=j;
			}
			if(min!=i){
				int temp=sortindex[min];
				sortindex[min]=sortindex[i];
				sortindex[i]=temp;
			}
		}

		return sortindex;
	} 

	
	public static int[] SortByHeight(float height[],int N){
		int sortindex[]=new int[N];
		for(int i=0;i<N;i++)sortindex[i]=i;
		
		for(int i=0;i<N;i++){
			int min=i;
			for(int j=i+1;j<N;j++){
				if(height[sortindex[min]]>height[sortindex[j]])
					min=j;
			}
			if(min!=i){
				int temp=sortindex[min];
				sortindex[min]=sortindex[i];
				sortindex[i]=temp;
			}
		}
		
		return sortindex;
	} 
	
	
	public static int[] SortByWeight(float weight[],int N){
		int sortindex[]=new int[N];
		for(int i=0;i<N;i++)sortindex[i]=i;
		
		for(int i=0;i<N;i++){
			int min=i;
			for(int j=i+1;j<N;j++){
				if(weight[sortindex[min]]>weight[sortindex[j]])
					min=j;
			}
			if(min!=i){
				int temp=sortindex[min];
				sortindex[min]=sortindex[i];
				sortindex[i]=temp;
			}
		}
		
		return sortindex;
	} 
	
	public static int[] SortByBMI(float bmi[],int N){
		int sortindex[]=new int[N];
		for(int i=0;i<N;i++)sortindex[i]=i;
		
		for(int i=0;i<N;i++){
			int min=i;
			for(int j=i;j<N;j++){
				if(bmi[sortindex[min]]>bmi[sortindex[j]])
					min=j;
			}
			if(min!=i){
				int temp=sortindex[min];
				sortindex[min]=sortindex[i];
				sortindex[i]=temp;
			}
		}

		return sortindex;
	} 
}
