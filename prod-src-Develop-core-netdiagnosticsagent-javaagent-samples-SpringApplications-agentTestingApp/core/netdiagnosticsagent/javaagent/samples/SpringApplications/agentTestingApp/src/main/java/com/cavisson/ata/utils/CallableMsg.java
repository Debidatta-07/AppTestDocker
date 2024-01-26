package com.cavisson.ata.utils;

import java.util.concurrent.Callable;

public class CallableMsg implements Callable<String> {

	private String message;

	public CallableMsg(String msg) {
		message = msg;
	}

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub

		System.out.println(message);

		return "Callable completed";
	}

}
