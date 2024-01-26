package com.cavisson.ata.utils;

import java.util.concurrent.Callable;

public class MyCallableThread extends Thread {
	Callable<String> callable = null;
	volatile int i = 2;

	public Callable getCallable() {
		return callable;
	}

	public void setCallable(Callable callable) {
		i = 0;
		this.callable = callable;
	}

	public void run() {
		while (true) {
			try {
				if (i == 0) {
					System.out.println("Running task at MyCallableThread");
					callable.call();
					i = 2;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
