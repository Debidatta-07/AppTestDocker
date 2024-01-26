package com.cavisson.ata.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cavisson.ata.utils.LoggerMDCWork;

public class LoggerMDCServices {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private String mdcValue;
	public static int count = 0;

	public static String tt() {

		LoggerMDCWork t = new LoggerMDCWork();
		t.dynamic();

		return "logging";
	}

	public static String rx() {

		LoggerMDCWork t = new LoggerMDCWork();
		t.rx();

		return "success";

	}

}
