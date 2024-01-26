package com.cavisson.ata.utils;

public class MyPoolCreator {
	public static ThreadPool threadPool;

	public MyPoolCreator() {
		super();
	}

	private void createThreadPool() {
		setThreadPool(new ThreadPool(3, 4));

	}

	public static ThreadPool getThreadPool() {
		return threadPool;
	}

	public static void setThreadPool(ThreadPool threadPool) {
		MyPoolCreator.threadPool = threadPool;
	}
}