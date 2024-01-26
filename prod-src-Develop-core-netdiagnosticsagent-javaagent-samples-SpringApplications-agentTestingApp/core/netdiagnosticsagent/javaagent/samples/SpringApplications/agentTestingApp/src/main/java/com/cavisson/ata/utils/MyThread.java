package com.cavisson.ata.utils;

public class MyThread extends Thread {

	Runnable runnable = null;
	volatile int i = 2;

	public Runnable getRunnable() {
		return runnable;
	}

	public void setRunnable(Runnable runnable) {
		i = 0;
		System.out.println("setRunnable");
		this.runnable = runnable;
	}

	public void run() {
		System.out.println("Thread has Started");

		while (true) {

			if (i == 0) {

				System.out.println("Running task at MyThread");
				runnable.run();

				i = 2;
			}
			/*
			 * try { Thread.sleep(400); } catch(InterruptedException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */

		}
	}
}
