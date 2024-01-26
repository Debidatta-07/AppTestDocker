package com.cavisson.ata.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class dlUtil extends Thread {
	@Override
	public void run() {
		int thvar= 20;
		multi(thvar,10);
		System.out.println("int value is: " + thvar);
	}
	
	//Capturing different types of variables for argument, local variable, class field, return value.
	
	int g=14;  //classField   
	char ch ='s';
	String st="In Class Field";
	int[] myArr= new int[]{10, 20, 30, 40, 50};
	ArrayList <String> colour = new ArrayList <String>();
	
	enum Myenum{
	PASTA,
	BURGER,
	NOODLES
	
	}
	Myenum var= Myenum.PASTA;

	public int add(int a,String str,long l,boolean b) {
		int sum= a+g;
		byte by =3;
		Map<Integer,String> smap= new HashMap<Integer,String>();
		float f=506.12789f;
		double d=10.00002;
		System.out.println("In add method!!");
		colour.add("Yellow");   //ArrayList adding data
		colour.add("Orange");
		System.out.println("String: " + st + "value of int g: " + g + "Value of int sum: " + 
		sum +"Arrays:" + myArr[1] +"Enum value:" + var);
		return sum;
	}
	public int multi(int d, int e) {
		int m= d*e;
		dlUtil abc= new dlUtil();
		abc.add(10, "sampleStr",2000000l,false);
		return m;
	}
}