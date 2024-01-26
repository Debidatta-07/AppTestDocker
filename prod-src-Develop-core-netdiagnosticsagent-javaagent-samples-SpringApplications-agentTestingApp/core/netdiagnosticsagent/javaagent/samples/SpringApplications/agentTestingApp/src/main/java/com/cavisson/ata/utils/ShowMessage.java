package com.cavisson.ata.utils;

public class ShowMessage implements Runnable {
	private String message;

	public ShowMessage(String msg) {
		message = msg;
	}

	@Override
	public void run() {
		System.out.println(message);
	}
}
