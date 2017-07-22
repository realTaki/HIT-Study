package edu.hitXXXX.experimentY;

import java.util.Scanner;

public class ControlStrcutureTest {
	
	public static void main(String[] agrs){
		printLeapYears(2001,2005,true);
		System.out.println();
		printLeapYears(2001,2050,false);
		
	}
	
	public static boolean checkLeapYear(int year) {
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return true;
		else
			return false;
	}

	public static void printLeapYears(int begin, int end,boolean onlyFirst) {
		for (int year = begin; year <= end; year++)
			if (checkLeapYear(year)) {
				System.out.printf("%d ", year);
				if(onlyFirst)return;
			}

	}
	
	public static void guessNumber() {
		System.out.println("给你一个1至100之间的整数,请猜测这个数");
		int realNumber = (int) (Math.random() * 100) + 1;
		int yourGuess = 0; // 你猜的数
		Scanner in = new Scanner(System.in);
		System.out.println("输入您的猜测:");
		do {
			yourGuess = in.nextInt(); // 从键盘键入你猜的数
			if (yourGuess > realNumber) {
				System.out.println("大了,请再猜:");
			} else if (yourGuess < realNumber) {
				System.out.println("猜小了,请再猜:");
			}
		} while (yourGuess != realNumber);
		System.out.println("恭喜！猜对了!");
		
		in.close();
	}
	
	public static void ChoiceTriangular(int n){
		switch(n){
			case 1:PrintTriangular(" ","*",1);break;
			case 2:PrintTriangular(" ","* ",1);break;
			case 3:PrintTriangular("*"," ",2);break;
			case 4:PrintTriangular(" ","*",2);break;
			case 5:PrintTriangular(" ","* ",2);break;
			case 6:PrintTriangular("*"," ",1);break;
			default:System.out.println("错误参数");
		}
	}
	
	public static void PrintTriangular(String left,String right,int mode){
		
		final int N=10;
		if(mode==1){
			for(int i=0;i<N;i++){
				for(int j=0;j<N-i;j++){
					System.out.printf("%s",left);
				}
				for(int j=0;j<i;j++){
					System.out.printf("%s",right);
				}
				System.out.println();
			}
		}else{
			for(int i=0;i<N;i++){
				for(int j=0;j<i;j++){
					System.out.printf("%s",left);
				}
				for(int j=0;j<N-i;j++){
					System.out.printf("%s",right);
				}
				System.out.println();
			}
		}
	}
	
	public static void ChineseTrangle(int N){
		
		int[][] number=new int[N][N]; 
		
		for(int i=0;i<N;i++)number[i][0]=1;
		
		for(int i=1;i<N;i++){
			for(int j=1;j<=i;j++){
				number[i][j]=number[i-1][j-1]+number[i-1][j];
			}
		}
		for(int i=0;i<N;i++){
			for(int j=0;j<N-i;j++)System.out.printf("%2s","");
			for(int j=0;j<=i;j++)System.out.printf("%4d",number[i][j]);
			System.out.println();
		}
	}
}
