package edu.hitXXXX.experimentY;

import java.util.Scanner;

public class Conversion {
	
	 public static void main(String[] args) {
	     	System.out.println("Hi, I¡¯m Jack, What¡¯s your name?");
			Scanner input=new Scanner(System.in);
			String myName=input.next();
	      	System.out.printf("My name is %s, Nice to meet you!%n", myName);
	      	input.close();
	}

}