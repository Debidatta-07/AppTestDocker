package com.cavisson.ata.services;

import com.cavisson.ata.utils.dlInterface;
import com.cavisson.ata.utils.dlUtil;
import com.cavisson.ata.utils.dlfunInterface;

public class DLServices {

	static long par = 11; // capturing enable for some class fields
	static String str = "In class Field";
	static int i = 5;

	public static String lambdaReturn() {
		int c = 5; // local variables
		int d = 10; // local variables
		System.out.println("Value of c : " + c);
		System.out.println("Value of d is: " + d);
		int multi = c * d; // local variables
		System.out.println("Multiplication of Number is: " + multi);
		dlfunInterface myIn = (n) -> n + 5; // lambda
		System.out.println(myIn.incrementfive(20));
		dlUtil ob = new dlUtil();
		ob.add(8, "sampleStrFrom_WelcomController", 2000000l, true); // Method add() called from MyClass
		return "method executed";
	}

	public static String mvoid() { // capturing enable for some method arguments

		voidmethod(3, 7, 8);
		return "void call executed";
	}

	public static void voidmethod(int p, int p2, int p3) { // capturing enable for some method arguments

		int l = p + p2 + p3;
		System.out.println("Hello in void method! \nValue of l is: " + l);

	}

	public static String anonymousPCthread() {
		System.out.println("in anonymous");
		int a = 10;
		dlUtil MyTh = new dlUtil();
		MyTh.start(); // New thread created and started.
		System.out.println("Value of a: " + a);
		int b = 10;
		int r = 0;
		r = a + b;
		System.out.println("Value of b: " + b);
		System.out.println("Result is: " + r);
		dlInterface myIn = new dlInterface() { // anonymous code
			public void getvar() {
				@SuppressWarnings("unused")
				int num = 10 + a;
				String s = "Testing Anonymous";
				s = s + "!!!!!";
				System.out.println("Value of a is: " + a);
			}

		};
		myIn.getvar();
		System.out.println("static variable value i : " + i);
		return "method executed"; // view
	}

}
